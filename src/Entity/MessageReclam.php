<?php

namespace App\Entity;

use App\Repository\MessageReclamRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=MessageReclamRepository::class)
 * @ORM\Table(name="message_reclam")
 */
class MessageReclam
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;


    /**
     * @ORM\Column(name="id_reclam",type="integer")
     */
    private $reclamation;

    /**
     * @ORM\Column(name="id_send",type="integer")
     */
    private $id_send;

    /**
     * @ORM\Column(name="id_receiver",type="integer",nullable=true)
     */
    private $id_receiver;

    /**
     * @ORM\Column(name="message",type="text")
     */
    private $message;

    /**
     * @ORM\Column(name="date",type="datetime")
     */
    private $date;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getReclamation(): ?int
    {
        return $this->reclamation;
    }
    public function setReclamation(int $reclamation): self
    {
        $this->reclamation = $reclamation;

        return $this;
    }


    public function getIdSend(): ?int
    {
        return $this->id_send;
    }
    public function setIdSend(int $id_send): self
    {
        $this->id_send = $id_send;

        return $this;
    }


    public function getIdReceiver():?int
    {
        return $this->id_receiver;
    }

    public function setIdReceiver($id_receiver): void
    {
        $this->id_receiver = $id_receiver;
    }


    public function getMessage(): ?string
    {
        return $this->message;
    }

    public function setMessage(string $message): self
    {
        $this->message = $message;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }
}
