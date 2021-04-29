<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/front")
 */
class FrontController extends AbstractController
{
    /**
     * @Route("/", name="app.front")
     */
    public function indexAction()
    {

        $em = $this->getDoctrine()->getManager();
        $formations = $em->getRepository('App:Formation')->findAll();

        return $this->render('front/home/index.html.twig', [
            'formations' => $formations
        ]);
    }
}
