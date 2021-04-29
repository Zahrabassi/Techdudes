<?php

namespace App\Controller;

use App\Entity\Choix;
use App\Form\ChoixType;
use App\Repository\ChoixRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/choix")
 */
class ChoixController extends AbstractController
{
    /**
     * @Route("/", name="choix_index", methods={"GET"})
     * @param ChoixRepository $choixRepository
     * @return Response
     */
    public function index(ChoixRepository $choixRepository): Response
    {
        return $this->render('choix/index.html.twig', [
            'choixes' => $choixRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new", name="choix_new", methods={"GET","POST"})
     * @param Request $request
     * @return Response
     */
    public function new(Request $request): Response
    {
        $choix = new Choix();
        $form = $this->createForm(ChoixType::class, $choix);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($choix);
            $entityManager->flush();

            return $this->redirectToRoute('choix_index');
        }

        return $this->render('choix/new.html.twig', [
            'choix' => $choix,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="choix_show", methods={"GET"})
     * @param Choix $choix
     * @return Response
     */
    public function show(Choix $choix): Response
    {
        return $this->render('choix/show.html.twig', [
            'choix' => $choix,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="choix_edit", methods={"GET","POST"})
     * @param Request $request
     * @param Choix $choix
     * @return Response
     */
    public function edit(Request $request, Choix $choix): Response
    {
        $form = $this->createForm(ChoixType::class, $choix);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('choix_index');
        }

        return $this->render('choix/edit.html.twig', [
            'choix' => $choix,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="choix_delete", methods={"POST"})
     * @param Request $request
     * @param Choix $choix
     * @return Response
     */
    public function delete(Request $request, Choix $choix): Response
    {
        if ($this->isCsrfTokenValid('delete'.$choix->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($choix);
            $entityManager->flush();
        }

        return $this->redirectToRoute('choix_index');
    }
}
