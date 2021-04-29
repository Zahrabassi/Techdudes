<?php

namespace App\Entity;
use App\Repository\EventRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\String\Slugger\SluggerInterface;

/**
 * @ORM\Entity(repositoryClass=EventRepository::class)
 */
class Event
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Length(
     *     min = 3,
     *     max = 5,
     * minMessage ="Le nom doit comporter au moins {{ limit }} caractères",
     * maxMessage ="Le nom doit comporter au plus {{ limit }} caractères"
     *  )
     */
    private $nom;

    /**
     * @ORM\Column(type="integer")
     */
    private $nbr_place;


    /**
     * @ORM\Column(type="datetime")
     * @Assert\GreaterThan("today")
     */

    private $date_allee;

    /**
     * @ORM\Column(type="datetime")
     * @Assert\Expression(
     *     "this.getDateAllee() < this.getDateRetour()",
     *     message="La date fin ne doit pas être antérieur à la date début")
     */
    private $date_retour;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $description;

    /**
     * @ORM\ManyToMany(targetEntity=Participant::class, mappedBy="id_event")
     */
    private $inscription;



    /**
     * @Assert\Type("string")
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="please upload image")

     */
    private $image;

    public function __construct()
    {
        $this->inscription = new ArrayCollection();
    }


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getNbrPlace(): ?int
    {
        return $this->nbr_place;
    }

    public function setNbrPlace(int $nbr_place): self
    {
        $this->nbr_place = $nbr_place;

        return $this;
    }

    public function getDateAllee(): ?\DateTimeInterface
    {
        return $this->date_allee;
    }

    public function setDateAllee(\DateTimeInterface $date_allee): self
    {
        $this->date_allee = $date_allee;

        return $this;
    }

    public function getDateRetour(): ?\DateTimeInterface
    {
        return $this->date_retour;
    }

    public function setDateRetour(\DateTimeInterface $date_retour): self
    {
        $this->date_retour = $date_retour;

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

    /**
     * @return Collection|Participant[]
     */
    public function getInscription(): Collection
    {
        return $this->inscription;
    }

    public function addInscription(Participant $inscription): self
    {
        if (!$this->inscription->contains($inscription)) {
            $this->inscription[] = $inscription;
            $inscription->addIdEvent($this);
        }

        return $this;
    }

    public function removeInscription(Participant $inscription): self
    {
        if ($this->inscription->removeElement($inscription)) {
            $inscription->removeIdEvent($this);
        }

        return $this;
    }

    public function getImage(): ?string
    {
        return $this->image;
    }

    public function setImage(?string $image): self
    {
        $this->image = $image;

        return $this;
    }


}
