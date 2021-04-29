<?php

namespace App\Entity;

use App\Utils\CartItem;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints\Date;

/**
 * Formation
 *
 * @ORM\Table(name="formation")
 * @ORM\Entity(repositoryClass="App\Repository\FormationRepository")
 */
class Formation
{

    use CartItem;

    /**
     * @var int
     *
     * @ORM\Column(name="id_f", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;

    /**
     * @var string
     * @ORM\Column(type="string", nullable=false)
     */
    private $intitule;

    /**
     * @var string
     * @ORM\Column(type="text", nullable=false)
     */
    private $description;

    /**
     * @var Date
     * @ORM\Column(type="date", nullable=false)
     */
    private $date_debut;

    /**
     * @var Date
     * @ORM\Column(type="date", nullable=false)
     */
    private $date_fin;

    /**
     * @var string
     * @ORM\Column(type="string", nullable=false)
     */
    private $type;

    /**
     * @var string
     * @ORM\Column(type="text", nullable=false)
     */
    private $img;

    /**
     * @var float
     * @ORM\Column(type="float", nullable=false)
     */
    private $prix;

    /**
     * @var Evaluation
     *
     * @ORM\ManyToOne(targetEntity="App\Entity\Evaluation", fetch="LAZY")
     * @ORM\JoinColumn(name="id_eval", referencedColumnName="id_eval")
     *
     */
    private $evaluation;

    /**
     * @var Enseignant
     *
     * @ORM\ManyToOne(targetEntity="App\Entity\Enseignant", fetch="LAZY")
     * @ORM\JoinColumn(name="id_formateur", onDelete="SET NULL")
     */
    private $formateur;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $pdf_file;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return string
     */
    public function getIntitule()
    {
        return $this->intitule;
    }

    /**
     * @param string $intitule
     */
    public function setIntitule($intitule)
    {
        $this->intitule = $intitule;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return Date
     */
    public function getDateDebut()
    {
        return $this->date_debut;
    }

    /**
     * @param Date $date_debut
     */
    public function setDateDebut($date_debut)
    {
        $this->date_debut = $date_debut;
    }

    /**
     * @return Date
     */
    public function getDateFin()
    {
        return $this->date_fin;
    }

    /**
     * @param Date $date_fin
     */
    public function setDateFin($date_fin)
    {
        $this->date_fin = $date_fin;
    }

    /**
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * @param string $type
     */
    public function setType($type)
    {
        $this->type = $type;
    }

    /**
     * @return string
     */
    public function getImg()
    {
        return $this->img;
    }

    /**
     * @param string $img
     */
    public function setImg($img)
    {
        $this->img = $img;
    }

    /**
     * @return float
     */
    public function getPrix()
    {
        return $this->prix;
    }

    /**
     * @param float $prix
     */
    public function setPrix($prix)
    {
        $this->prix = $prix;
    }

    /**
     * @return Evaluation
     */
    public function getEvaluation()
    {
        return $this->evaluation;
    }

    /**
     * @param Evaluation $evaluation
     */
    public function setEvaluation($evaluation)
    {
        $this->evaluation = $evaluation;
    }

    /**
     * @return Enseignant
     */
    public function getFormateur()
    {
        return $this->formateur;
    }

    /**
     * @param Enseignant $formateur
     */
    public function setFormateur($formateur)
    {
        $this->formateur = $formateur;
    }

    /**
     * @return bool
     */
    public function isValid()
    {
        //return $this->date_debut >= new Date();
        return true;
    }

    /**
     * @return string
     */
    public function getName()
    {
        return $this->intitule;
    }

    /**
     * @return float
     */
    public function getPrice()
    {
        return $this->prix;
    }

    public function __toString()
    {
        return $this->intitule;
    }

    /**
     * @return mixed
     */
    public function getPdfFile()
    {
        return $this->pdf_file;
    }

    /**
     * @param mixed $pdf_file
     */
    public function setPdfFile($pdf_file): void
    {
        $this->pdf_file = $pdf_file;
    }
    
}

