<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

/**
 * class BackController
 * @Route("/back")
 */
class BackController extends AbstractController
{

    /**
     * @Route("/", name="dashboard")
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $promotion = $em->getRepository('App:Promotion')->findTopPromotion();
        if ($promotion) {
            $promotion = $promotion[0];
        }

        //debug_zval_dump($promotion);

        return $this->render('back/home/index.html.twig', [
            'base_dir' => realpath($this->getParameter('kernel.project_dir')) . DIRECTORY_SEPARATOR,
            'promotion' => $promotion
        ]);
    }
}
