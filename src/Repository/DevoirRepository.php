<?php

namespace App\Repository;

use App\Entity\Devoir;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Devoir|null find($id, $lockMode = null, $lockVersion = null)
 * @method Devoir|null findOneBy(array $criteria, array $orderBy = null)
 * @method Devoir[]    findAll()
 * @method Devoir[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class DevoirRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Devoir::class);
    }

    /**
     * @return Devoir[] Returns an array of Devoir objects
      */

    /*
       public function findById()
       {
           $Query=$this->getEntityManager()
               ->createQuery("SELECT l FROM Devoir WHERE l.id=:id ");

           return $Query->getResult();

       }

       public function findOneBySomeField($value): ?Devoir
       {
           return $this->createQueryBuilder('d')
               ->andWhere('d.exampleField = :val')
               ->setParameter('val', $value)
               ->getQuery()
               ->getOneOrNullResult()
           ;
       }
       */


}
