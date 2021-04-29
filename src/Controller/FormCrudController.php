<?php

namespace App\Controller;


use App\Entity\Formation;
use App\Form\FormationType;
use App\Repository\FormationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/form/crud")
 */
class FormCrudController extends AbstractController
{
    /**
     * @Route("/", name="form_crud_index", methods={"GET"})
     */
    public function index(FormationRepository $formationRepository): Response
    {
        return $this->render('form_crud/index.html.twig', [
            'formations' => $formationRepository->findAll(),
        ]);

    }

    /**
     * @Route("/new", name="form_crud_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $formation = new Formation();
        $form = $this->createForm(FormationType::class, $formation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('pdf_file')->getData();
            $fileName = md5(uniqid()) . "." . $file->guessExtension();
            $file->move($this->getParameter('formation_directory'),$fileName);
            $formation->setPdfFile($fileName);
            $image =$form->get('img')->getData();
            $imgName = md5(uniqid()) . "." . $image->guessExtension();
            $image->move($this->getParameter('img_directory'),$imgName);
            $formation->setImg($imgName);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($formation);
            $entityManager->flush();

            return $this->redirectToRoute('form_crud_index');
        }

        return $this->render('form_crud/new.html.twig', [
            'formation' => $formation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="form_crud_show", methods={"GET"})
     */
    public function show(Formation $formation): Response
    {
        return $this->render('form_crud/show.html.twig', [
            'formation' => $formation,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="form_crud_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Formation $formation): Response
    {
        $form = $this->createForm(FormationType::class, $formation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('form_crud_index');
        }

        return $this->render('form_crud/edit.html.twig', [
            'formation' => $formation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="form_crud_delete", methods={"POST"})
     */
    public function delete(Request $request, Formation $formation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$formation->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($formation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('form_crud_index');
    }

}
