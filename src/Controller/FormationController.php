<?php

namespace App\Controller;

use App\Entity\Formation;
use App\Form\AchatType;
use App\Manager\CartManager;
use App\Repository\FormationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * Class FormationController
 * @package App\Controller
 * @Route("/formation")
 */
class FormationController extends AbstractController
{

    /**
     * @Route("/detail/{id}", name="formation.detail", methods={"GET", "POST"})
     * @param Formation $formation
     * @return \Symfony\Component\HttpFoundation\Response|null
     */
    public function listAction(Formation $formation, Request $request, CartManager $cartManager)
    {
        $form = $this->createForm(AchatType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $item = $form->getData();
            $cartManager->getCart()->addItem($formation);
            $this->addFlash('success', 'Formation added to cart successfully.');

            //return $this->redirectToRoute('formation.detail', ['id' => $formation->getId()]);
        }

        return $this->render('front/formation/detail.html.twig', [
            'formation' => $formation,
            'form' => $form->createView()
        ]);
    }

    /**
     * @Route("/", name="formation")
     */
    public function index(): Response
    {
        return $this->render('formation/index.html.twig', [
            'controller_name' => 'FormationController',
        ]);
    }

    /**
     * @Route("/listformation", name="listformation")
     */
    public function list()
    {
        $formation = $this->getDoctrine()->getRepository(Formation::class)->findAll();
        return $this->render('formation/listform.html.twig', ["tab" => $formation]);
    }

    /**
     * @Route("/infoformationn/{id}", name="infoformationn")
     */
    public function reserve(Formation $formation)
    {
        return $this->render('formation/infoformation.html.twig', ['tab' => $formation]);
    }

    /**
     * @param Request $req
     * @param FormationRepository $rep
     * @return  Response
     * @Route("/rechercheByNameF", name="rechercheByNameF")
     */
    public function recherche(FormationRepository $rep, Request $req)
    {
        $data = $req->get('nom');
        $formations = $rep->findBy(['type' => $data]);
        return $this->render('formation/listform.html.twig', ["tab" => $formations]);
    }
}
