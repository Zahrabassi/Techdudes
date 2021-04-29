<?php

namespace App\Entity;

use App\Repository\CourRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=CourRepository::class)
 */
class Cour
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /** @Assert\Length(
     *     min=3,
     *     minMessage = "Le nom du cour doit au minimum {{ limit }} characters long",
     *
     *     )
     * @ORM\Column(type="string", length=50)
     */
    private $nomCour;

    /** @Assert\Length(
     *     min=3,
     *     minMessage = "Le nom de l Enseignant doit au minimum {{ limit }} characters long",
     *
     *     )
     * @ORM\Column(type="string", length=50)
     */
    private $nomEnseignant;

    /** @Assert\Length(
     *     min=3,
     *     minMessage = "La description doit au minimum {{ limit }} characters long",
     *
     *     )
     * @ORM\Column(type="string", length=50)
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=50, nullable=true)
     */
    private $img;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $idD;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomCour(): ?string
    {
        return $this->nomCour;
    }

    public function setNomCour(string $nomCour): self
    {
        $this->nomCour = $nomCour;

        return $this;
    }

    public function getNomEnseignant(): ?string
    {
        return $this->nomEnseignant;
    }

    public function setNomEnseignant(string $nomEnseignant): self
    {
        $this->nomEnseignant = $nomEnseignant;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getImg(): ?string
    {
        return $this->img;
    }

    public function setImg(string $img): self
    {
        $this->img = $img;

        return $this;
    }

    public function getIdD(): ?int
    {
        return $this->idD;
    }

    public function setIdD(?int $idD): self
    {
        $this->idD = $idD;

        return $this;
    }
}
