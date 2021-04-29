<?php

namespace App\Controller;

use App\Entity\Choix;
use App\Entity\Formation;
use App\Entity\Questions;
use App\Entity\Quiz;
use App\Form\QuizType;
use App\Repository\QuizRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\Flash\FlashBagInterface;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/quiz")
 */
class QuizController extends AbstractController
{
    /**
     * @Route("/", name="quiz_index", methods={"GET"})
     * @param QuizRepository $quizRepository
     * @return Response
     */
    public function index(QuizRepository $quizRepository): Response
    {
        return $this->render('quiz/index.html.twig', [
            'quizzes' => $quizRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="quiz_new", methods={"GET","POST"})
     * @param Request $request
     * @return Response
     */
    public function new(Request $request): Response
    {
        $quiz = new Quiz();
        $form = $this->createForm(QuizType::class, $quiz);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($quiz);
            $entityManager->flush();

            return $this->redirectToRoute('quiz_index');
        }

        return $this->render('quiz/new.html.twig', [
            'quiz' => $quiz,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/pass-test/{id}", name="pass_test", methods="POST")
     * @Route("/pass-test/{id}", name="pass_test-value", methods="GET")
     * @param EntityManagerInterface $entityManager
     * @param Request $request
     * @param Quiz $quiz
     * @return Response
     * @throws \Exception
     */
    public function passTest(
        EntityManagerInterface $entityManager,
        Request $request,
        ?Quiz $quiz
    ){

        $quizReponses = $request->request->all();
        $nbrQuestion = count($entityManager->getRepository(Questions::class)->findBy([
            'quiz' => $quiz
        ]));
        $note = 0;

        foreach ($quizReponses as $reponse){
            $parms = explode('-', $reponse);
            $choix = $entityManager->getRepository(Choix::class)->find($parms[1]);
            if($choix->getIsRight())
            {
                $note = $note + 1;
            }
        }

        $note = round(( $note / $nbrQuestion ) * 100) ;

        $res= $note > 50 ? ["type" => "success", "message" => "Yeah ! Good job    Votre score "]
            :["type" => "danger", "message" => "Please try again !     Votre score "];

        if  (!empty($quizReponses) )
        {
            return  $this->render('quiz/result.html.twig', [
                "type" => $res["type"] ,
                "message" => $res["message"] . $note
            ]);
        }

        return $this->render('quiz/pass_test.html.twig', ['quiz' => $quiz,'nbrQuestion'=> $nbrQuestion]);
    }

    /**
     * @Route("result", name="result")
     * @return Response
     */
    public function inFailer(){
        return $this->render("quiz/fail.html.twig");
        }

    /**
     * @Route("expired/{id}", name="expired")
     */
    public function tempsEexp(Formation $formation){
        return $this->render("quiz/tempsExp.twig",['form' => $formation]);
    }


    /**
     * @Route("/{id}", name="quiz_show", methods={"GET"})
     */
    public function show(Quiz $quiz): Response
    {
        return
            $this->render('quiz/show.html.twig', [
            'quiz' => $quiz,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="quiz_edit", methods={"GET","POST"})
     * @param Request $request
     * @param Quiz $quiz
     * @return Response
     */
    public function edit(Request $request, Quiz $quiz): Response
    {
        $form = $this->createForm(QuizType::class, $quiz);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('quiz_index');
        }

        return $this->render('quiz/edit.html.twig', [
            'quiz' => $quiz,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="quiz_delete", methods={"POST"})
     * @param Request $request
     * @param Quiz $quiz
     * @return Response
     */
    public function delete(Request $request, Quiz $quiz): Response
    {
        if ($this->isCsrfTokenValid('delete'.$quiz->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($quiz);
            $entityManager->flush();
        }

        return $this->redirectToRoute('quiz_index');
    }
}
