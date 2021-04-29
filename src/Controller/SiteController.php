<?php

namespace App\Controller;


use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class SiteController extends AbstractController
{
    /**
     * @Route("/", name="homepage")
     * @return Response
     */
    function homepage()
    {
        return $this->render('front.html.twig');
    }

    /**
     * @Route("/dash", name="dash")
     * @return Response
     */
    function dash()
    {
        return $this->render('dash.html.twig');
    }
}