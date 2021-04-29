<?php

namespace App\Repository;

use App\Entity\MessageReclam;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method MessageReclam|null find($id, $lockMode = null, $lockVersion = null)
 * @method MessageReclam|null findOneBy(array $criteria, array $orderBy = null)
 * @method MessageReclam[]    findAll()
 * @method MessageReclam[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class MessageReclamRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, MessageReclam::class);
    }

    public function getMessages($connectedId,$idReceiver,$idReclamation)
    {
        $query = $this->getEntityManager()
            ->createQuery("SELECT m FROM App\Entity\MessageReclam m WHERE m.id_send ='$connectedId' AND m.id_receiver = '$idReceiver' AND m.reclamation = '$idReclamation'  ");
        return $query->getResult();
    }

    // /**
    //  * @return MessageReclam[] Returns an array of MessageReclam objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('m.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?MessageReclam
    {
        return $this->createQueryBuilder('m')
            ->andWhere('m.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
