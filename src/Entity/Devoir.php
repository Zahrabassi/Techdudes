<?php

namespace App\Entity;

use App\Repository\DevoirRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=DevoirRepository::class)
 */
class Devoir
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $nomDevoir;

    /**
     * @ORM\Column(type="string", length=50)
     */
    private $description;


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNomDevoir()
    {
        return $this->nomDevoir;
    }

    public function setNomDevoir($nomDevoir)
    {
        $this->nomDevoir = $nomDevoir;

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


}
