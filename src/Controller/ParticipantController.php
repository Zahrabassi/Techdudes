<?php

namespace App\Controller;

use App\Entity\Event;
use App\Entity\Participant;
use App\Form\ParticipantType;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;


class ParticipantController extends AbstractController
{
    /**
     * @Route("/participant{id}", name="participant")
     * @param $id
     * @param Request $request
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|\Symfony\Component\HttpFoundation\Response
     */
    public function ajout_participant($id, Request $request)
    {
        $part = new Participant();
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        $form = $this->createForm(ParticipantType::class, $part);
        $form->handleRequest($request);
        $part->addIdEvent($event);
        $part->setEtat('non acceptee');
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($part);
            $p = $event->getNbrPlace();
            $event->setNbrPlace($p - 1);
            $em->flush();
            return $this->redirectToRoute("evenements");
        }

        return $this->render("participant/participant.html.twig", array("form" => $form->createView()));

    }

    /**
     * @Route("/affichepart", name="affichepart")
     */
    public function affiche_part()
    {
        $p = $this->getDoctrine()->getRepository(Participant::class)->findAll();
        return $this->render('participant/afficher_participantback.html.twig', ['part' => $p]);
    }

    /**
     * @Route("supprimerpart{id}", name="supprimerpart")
     */
    public function supprimer_part($id)
    {
        $part = $this->getDoctrine()->getRepository(Participant::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($part);
        $em->flush();
        return $this->redirectToRoute("affichepart");
    }

    /**
     * @Route("modifierpart{id}", name="modifierpart")
     * @param $id
     * @param \Swift_Mailer $mailer
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     */

    public function modifier_part($id, \Swift_Mailer $mailer): \Symfony\Component\HttpFoundation\RedirectResponse
    {
        $part = $this->getDoctrine()->getRepository(Participant::class)->find($id);
        $part->setEtat('acceptee');
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        $mail = $part->getEmail();
        if ($part->getEtat() == 'acceptee') {
            $message = (new \Swift_Message('Confirmation'))
                ->setFrom('ammouna.oukassi@gmail.com')
                ->setTo($mail)
                ->setBody('Participation confirmée');

            $mailer->send($message);
        }
        return $this->redirectToRoute("affichepart");

    }



    /**
     * @Route("/lister", name="lister")
     */
    public function affiche_listr(){
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $part=$this->getDoctrine()->getRepository(participant::class)->findAll();

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('participant/listr.html.twig', ['part'=>$part]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("participant.pdf", [
            "Attachment" => true
        ]);
    }
}