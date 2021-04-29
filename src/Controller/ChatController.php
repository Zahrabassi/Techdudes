<?php

namespace App\Controller;

use App\Entity\MessageReclam;
use App\Entity\Reclamation;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ChatController extends AbstractController
{
    /**
     * @Route("/chat", name="chat")
     */
    public function index(): Response
    {
        $adminId=2;
        $em=$this->getDoctrine()->getManager();
        $conversations=$em->getRepository(Reclamation::class)->getReclamationDistinct();

        foreach ($conversations as $conversation){
            $messages=$em->getRepository(MessageReclam::class)->findBy(
                ['reclamation' => $conversation->getId()]
            );
            $conversation->setMessages($messages);
        }

        return $this->render('chat/index.html.twig', [
            'conversations' => $conversations,
            'adminId' => $adminId

        ]);
    }


}
