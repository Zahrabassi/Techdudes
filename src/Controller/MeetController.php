<?php

namespace App\Controller;
use App\Entity\Mail;
use App\Form\CourType;
use App\Form\MeetContactType;
use Swift_Message;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Service\SendMailService;

class MeetController extends AbstractController
{
    /**
     * @Route("/meet", name="meet")
     * @param Request $request
     * @return Response
     */
    public function index(Request $request, \Swift_Mailer $mailer): Response
    {
    $mail = new Mail();
        $form = $this->createForm(MeetContactType::class, $mail);
        $form->handleRequest($request);
/*
        if ($form->isSubmitted() && $form->isValid()) {
            $title = $mail->getTitle();
            $email = $mail->getEmail();
            $message = $mail->getMessage();

        }
        if($form->isSubmitted() && $form->isValid()){
            $title = $mail->getTitle();
            $email = $mail->getEmail();
            $message = $mail->getMessage();
            $this->addFlash('message', 'Votre e-mail a bien été envoyé');*/
        if ($request->isMethod('POST')) {
            $nombre=$request->get("nombre");
            $email=$request->get("email");
            $numero=$request->get("numero");
            $empresa=$request->get("empresa");
            $solucion=$request->get("solucion");

            if (($nombre=="")||($email=="")||($numero=="")||($empresa=="")){
                $this->addFlash(
                    'alert alert-danger',
                    'Toda la información es obligatoria'
                );
                return $this->redirectToRoute('meet');
            }
            $email="zahra.abassi@esprit.tn";
            $title="";
            $message=$this->renderView(
                'mail/index.html.twig',
                array(
                    'nombre' => $nombre,
                    'email' => $email,

                )
            );
            $mail->sendEmail($email,$title, $message);
            $this->addFlash(
                'alert alert-success',
                'Pronto nos pondremos en contacto.'
            );
            return $this->redirectToRoute('meet');
        }

return $this->render('mail/index.html.twig',array('f'=> $form->createView()));
}
}
