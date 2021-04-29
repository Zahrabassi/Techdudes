<?php

namespace App\Controller;

use App\Entity\Cour;
use App\Repository\CourRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class MescourController extends AbstractController
{
    /**
     * @Route("/mescour", name="mescour")
     */
    public function index(): Response
    {
        return $this->render('mescour/index.html.twig', [
            'controller_name' => 'MescourController',
        ]);
    }


    /**
     * @Route("/listCour", name="listCour")
     */
    public function list()
    {
        $cours = $this->getDoctrine()->getRepository(Cour::class)->findAll();
        return $this->render('mescour/index.html.twig', ["tab" => $cours]);
    }



    /**
     * @Route("/mesCour", name="Main")
     */
    public function courMain()
    {

        return $this->render('cours/index.html.twig');
    }


    /**
     * @Route("/dev", name="dev")
     *
     */
    public function info():Response
    {
        $cours = $this->getDoctrine()->getRepository(Cour::class)->findAll();
        dump($cours);
        return $this->render('mescour/dev.html.twig', ["tab" => $cours]);

    }


}
