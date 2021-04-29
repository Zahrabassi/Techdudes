<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Enseignant
 *
 * @ORM\Table(name="enseignant")
 * @ORM\Entity(repositoryClass="App\Repository\EnseignantRepository")
 */
class Enseignant extends User implements \JsonSerializable
{

    /**
     * @var int
     * @ORM\Column(name="id_ens", type="integer")
     */
    protected $id;

    /**
     * @var string
     * @ORM\Column(name="nom", type="string")
     */
    protected $nom;

    /**
     * @var string
     * @ORM\Column(name="prenom", type="string")
     */
    protected $prenom;

    public function __construct()
    {
        parent::__construct();
        $this->addRole(User::ROLE_ENSEIGNANT);
    }

    /**
     * @return int
     */
    public function getIdEns()
    {
        return $this->id;
    }

    /**
     * @param int $id_ens
     */
    public function setIdEns($id_ens)
    {
        $this->id = $id_ens;
    }

    /**
     * @return string
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return string
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * @param string $prenom
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }

    /**
     * @inheritDoc
     */
    public function jsonSerialize()
    {
        return [
            "id" => $this->id,
            "nom" => $this->getUsername(),
            "email" => $this->getEmail(),
        ];
    }

    public function __toString()
    {
        return $this->getName() . $this->getPrenom();
    }
}

