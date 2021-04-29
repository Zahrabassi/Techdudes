<?php

namespace App\Controller;

use App\Entity\Cour;
use App\Entity\Devoir;
use App\Form\CategorySearchType;
use App\Form\UploadType;
use App\Repository\DevoirRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\File;
class DevoirController extends AbstractController
{
    /**
     *@Route("/devoir", name="devoir")
     * @param DevoirRepository $devoirs
     */
    public function index(Request $request): Response
    {
        $devoirs = new Devoir();
        $form = $this->createForm(CategorySearchType::class,$devoirs);
        $form->handleRequest($request);
        $devoirs= [];
        if($form->isSubmitted() && $form->isValid()) {
            $nom = $devoirs->getDescription();
            if ($nom!="")
                $articles= $this->getDoctrine()->getRepository(Article::class)->findBy(['nom' => $nom] );
            else
                $articles= $this->getDoctrine()->getRepository(Article::class)->findAll();
        }
        return $this->render('devoir/index.html.twig',[ 'form' =>$form->createView(), 'devoir' => $devoirs]);

    }
    /**
     * @Route("/devoir", name="devoir")
     *
     * @param $id
     *  @return Devoir[] Returns an array of Devoir objects
     */

    public function listDev(): Response
    {

        $devoirs = $this->getDoctrine()->getRepository(Devoir::class)->findAll();
        return $this->render('devoir/index.html.twig', ["tab" => $devoirs]);

    }
    /**
     * @Route("/devpdf", name="devpdf")
     *
     */
    public function pdf(Request $request)

    {
        $Devoir = new Devoir();
        $form =$this->createForm(UploadType::class, $Devoir);

        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $file =$Devoir->getNomDevoir();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('upload_directory'),$fileName);
            $Devoir->setNomDevoir($fileName);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($Devoir);
            $entityManager->flush();

            return $this->redirectToRoute('devoir');

        }
        return $this->render('devoir/devPdf.html.twig' , array('form'=> $form->createView(),));
    }

    /**
     *@Route("/devoir",name="article_list")
     */
    public function home(Request $request)
    {
        $devoirs = new Devoir();
        $form = $this->createForm(CategorySearchType::class,$devoirs);
        $form->handleRequest($request);
        $articles= [];
        if($form->isSubmitted() && $form->isValid()) {
            $nom = $devoirs->getDescription();
            if ($nom!="")
                $articles= $this->getDoctrine()->getRepository(Article::class)->findBy(['nom' => $nom] );
            else
                $articles= $this->getDoctrine()->getRepository(Article::class)->findAll();
        }
        return $this->render('devoir/index.html.twig',[ 'form' =>$form->createView(), 'articles' => $articles]);
    }

}
