<?php

namespace App\Controller;

use App\Entity\Cour;
use App\Entity\Devoir;
use App\Form\CourType;
use App\Form\UploadType;
use App\Repository\DevoirRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

use Symfony\Component\HttpFoundation\Request; // Nous avons besoin d'accéder à la requête pour obtenir le numéro de page
use Knp\Component\Pager\PaginatorInterface;
/**
 * @Route("/crud/cour")
 */
class CrudCourController extends AbstractController
{
    /**
     * @Route("/", name="crud_cour_index", methods={"GET"})
     */

    public function index(Request $request, PaginatorInterface $paginator) // Nous ajoutons les paramètres requis
    {
        // Méthode findBy qui permet de récupérer les données avec des critères de filtre et de tri
        $donnees = $this->getDoctrine()->getRepository(Cour::class)->findBy([],['nomCour'=>'ASC']);


        $cours = $paginator->paginate(
            $donnees,
            $request->query->getInt('page', 1),
            3 // Nombre de résultats par page
        );

        return $this->render('crud_cour/index.html.twig', [
            'cours' => $cours,
        ]);
    }
    /**
     * @Route("/trie",name="trie", methods={"GET"})
     */
    public function trie()
    {

        $cours = $this->getDoctrine()->getRepository(Cour::class)->findBy([],['nomCour'=>'desc']);
        return $this->render('crud_cour/trie.html.twig', ["tab" => $cours]);
    }


    /**
     * @Route("/new", name="crud_cour_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $cour = new Cour();
        $form = $this->createForm(CourType::class, $cour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($cour);
            $entityManager->flush();

            return $this->redirectToRoute('crud_cour_index');
        }

        return $this->render('crud_cour/new.html.twig', [
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="crud_cour_show", methods={"GET"})
     */
    public function show(Cour $cour): Response
    {
        return $this->render('crud_cour/show.html.twig', [
            'cour' => $cour,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="crud_cour_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Cour $cour): Response
    {
        $form = $this->createForm(CourType::class, $cour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('crud_cour_index');
        }

        return $this->render('crud_cour/edit.html.twig', [
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="crud_cour_delete", methods={"POST"})
     */
    public function delete(Request $request, Cour $cour): \Symfony\Component\HttpFoundation\RedirectResponse
    {
        if ($this->isCsrfTokenValid('delete'.$cour->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($cour);
            $entityManager->flush();
        }

        return $this->redirectToRoute('crud_cour_index');
    }

    /**
     * @Route("/listCour", name="listCour")
     */
    public function list()
    {
        $cours = $this->getDoctrine()->getRepository(Cour::class)->findAll();
        return $this->render('mescour/index.html.twig', ["tab" => $cours]);
    }

}
