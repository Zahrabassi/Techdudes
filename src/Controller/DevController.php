<?php

namespace App\Controller;

use App\Entity\Devoir;
use App\Form\CategorySearchType;
use App\Form\DevoirType;
use App\Form\PropertySearchType;
use App\Form\UploadType;
use App\Repository\DevoirRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Symfony\Component\HttpFoundation\RedirectResponse;


/**
 * @Route("/dev")
 */
class DevController extends AbstractController
{

    /**
     * @Route("/", name="dev_index", methods={"GET"})
     */
    public function index(DevoirRepository $devoirRepository): Response
    {


        return $this->render('dev/index.html.twig', [
            'devoirs' => $devoirRepository->findAll(),
        ]);
    }
    /**
     * @Route("/newpdf", name="newpdf" )
     *
     */
    public function ajoutdev(Request $request)
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

            return $this->redirectToRoute('dev_index');
        }
        return $this->render('devoir/ajoutdv.html.twig' , array('form'=> $form->createView(),));
    }



    /**
     * @Route("/new", name="dev_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $devoir = new Devoir();
        $form = $this->createForm(DevoirType::class, $devoir);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($devoir);
            $entityManager->flush();

            return $this->redirectToRoute('dev_index');
        }

        return $this->render('dev/new.html.twig', [
            'devoir' => $devoir,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="dev_show", methods={"GET"})
     */
    public function show(Devoir $devoir): Response
    {
        return $this->render('dev/show.html.twig', [
            'devoir' => $devoir,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="dev_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Devoir $devoir): Response
    {
        $form = $this->createForm(DevoirType::class, $devoir);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('dev_index');
        }

        return $this->render('dev/edit.html.twig', [
            'devoir' => $devoir,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="dev_delete", methods={"POST"})
     */
    public function delete(Request $request, Devoir $devoir): Response
    {
        if ($this->isCsrfTokenValid('delete'.$devoir->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($devoir);
            $entityManager->flush();
        }

        return $this->redirectToRoute('dev_index');
    }








}
