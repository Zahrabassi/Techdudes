<?php

namespace App\Controller;

use App\Entity\Promotion;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * Class PromotionController
 * @package App\Controller
 * @Route(path="/promotion")
 */
class PromotionController extends AbstractController
{

    /**
     * @Route("/", name="promotion.list")
     * @param Request $request
     * @return Response|null
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $promotionRepo = $em->getRepository('App:Promotion');
        $promotions = $promotionRepo->findAll();

        $topPromotion = $promotionRepo->findTopPromotion();
        if ($topPromotion) {
            $topPromotion = $topPromotion[0];
        }

        return $this->render('/back/promotion-list/index.html.twig', [
            'promotions' => $promotions,
            'topOne' => $topPromotion
        ]);
    }

    /**
     * Delete Promotion.
     *
     * @Route("/remove/{id}", name="promotion.delete_id", methods={"POST"})
     */
    public function deleteAction(Request $request, Promotion $promotion)
    {

        $em = $this->getDoctrine()->getManager();
        $em->remove($promotion);
        $em->flush();
        $this->addFlash('success', 'Promotion successfully deleted.');

        return $this->redirectToRoute('promotion.list');
    }

    /**
     * Create Promotion.
     *
     * @Route("/create", name="promotion.create")
     */
    public function createAction(Request $request)
    {
        $promotion = new Promotion();
        $form = $this->createForm('App\Form\PromotionType', $promotion);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($promotion);
            $em->flush();

            return $this->redirectToRoute('promotion.list');
        }

        return $this->render('/back/promotion-edit/index.html.twig', [
            'promotion' => $promotion,
            'form' => $form->createView()
        ]);
    }

    /**
     * Edit Promotion.
     *
     * @Route("/edit/{id}", name="promotion.edit")
     */
    public function editAction(Request $request, Promotion $promotion)
    {
        $form = $this->createForm('App\Form\PromotionType', $promotion);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($promotion);
            $em->flush();

            return $this->redirectToRoute('promotion.list');
        }

        return $this->render('/back/promotion-edit/index.html.twig', [
            'promotion' => $promotion,
            'form' => $form->createView()
        ]);
    }
}
