<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Form\TrieNbrDeplacesType;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use App\Entity\Event;
use App\Form\AjoutEventType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;

class EvenementsController extends AbstractController
{

    /**
     * @Route("/ajoute", name="ajoute")
     */
    public function ajout_event(Request $request)
    {
        $event = new Event();
        $form = $this->createForm(AjoutEventType::class, $event);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            $newFilename = md5(uniqid()) . '.' . $file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('img_directory'),
                    $newFilename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $em = $this->getDoctrine()->getManager();
            $event->setImage($newFilename);
            $em->persist($event);
            $em->flush();
            return $this->redirectToRoute("affichee");
        }
        return $this->render("evenements/ajoute.html.twig", array("form" => $form->createView()));

    }


    /**
     * @Route("/affichee", name="affichee")
     */
    public function affiche_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->findAll();
        return $this->render('evenements/affichee.html.twig', ['event' => $event]);
    }

    /**
     * @Route("/afficheee", name="afficheee")
     */
    public function affichee_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->orderbynb();
        return $this->render('evenements/evenements.html.twig', ['event' => $event]);
    }

    /**
     * @Route("/afficheeee", name="afficheeee")
     */
    public function afficheee_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->orderbynbb();
        return $this->render('evenements/evenements.html.twig', ['event' => $event]);
    }

    /**
     * @Route("/afficheeeb", name="afficheeeb")
     */
    public function afficheeb_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->orderbynb();
        return $this->render('evenements/affichee.html.twig', ['event' => $event]);
    }

    /**
     * @Route("/afficheeeeb", name="afficheeeeb")
     */
    public function afficheeeb_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->orderbynbb();
        return $this->render('evenements/affichee.html.twig', ['event' => $event]);
    }


    /**
     * @Route("/evenements", name="evenements")
     */
    public function affiche_event_front()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->findAll();
        return $this->render('evenements/evenements.html.twig', ['event' => $event]);
    }

    /**
     * @Route("/Quickview{id}", name="Quickview")
     */
    public function affiche_Quickview($id)
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        return $this->render('evenements/Quickview.html.twig', ['i' => $event]);
    }


    /**
     * @Route("/listcom", name="listcom")
     */
    public function listcom()
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        $eve = $this->getDoctrine()->getRepository(Event::class)->findAll();
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('evenements/listcom.html.twig', ['eve' => $eve]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("evenement.pdf", [
            "Attachment" => true
        ]);
    }

    /**
     * @Route("updatee{id}", name="updatee")
     */
    public function update($id, Request $request)
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        $form = $this->createForm(AjoutEventType::class, $event);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("affichee");
        }

        return $this->render("evenements/modifiere.html.twig", array("form" => $form->createView()));
    }

    /**
     * @Route("supprimere{id}", name="supprimere")
     */
    public function supprimer_event($id)
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($event);
        $em->flush();
        return $this->redirectToRoute("affichee");
    }
}