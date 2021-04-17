<?php

namespace App\Controller;


use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;

class SiteController extends AbstractController
{
    /**
     * @Route("/home", name="homepage")
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
        return $this->render('base.html.twig');
    }
}