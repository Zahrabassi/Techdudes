<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Etudiant
 *
 * @ORM\Table(name="etudiant")
 * @ORM\Entity(repositoryClass="App\Repository\EtudiantRepository")
 */
class Etudiant extends User implements \JsonSerializable
{
    /**
     * @var int
     * @ORM\Column(name="id_etud", type="integer")
     */
    protected $id;

    public function __construct()
    {
        parent::__construct();
        $this->addRole(User::ROLE_ETUDIANT);
    }


    protected $reclamations;

    /**
     * @return ArrayCollection
     */
    public function getReclamations()
    {
        return $this->reclamations;
    }

    /**
     * @param ArrayCollection $reclamations
     */
    public function setReclamations($reclamations)
    {
        $this->reclamations = $reclamations;
    }

    /**
     * @return int
     */
    public function getIdEtud()
    {
        return $this->id;
    }

    /**
     * @param int $id_etud
     */
    public function setIdEtud($id_etud)
    {
        $this->id = $id_etud;
    }


    /**
     * @inheritDoc
     */
    public function jsonSerialize()
    {
        return [
            "id_etud" => $this->id,
            "email" => $this->getEmail(),
            "nom" => $this->getUsername(),
        ];
    }

    public function __toString()
    {
        return $this->getName(). $this->getUsername();
    }
}

