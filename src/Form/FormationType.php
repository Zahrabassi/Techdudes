<?php

namespace App\Form;

use App\Entity\Formation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class FormationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('intitule')
            ->add('description')
            ->add('date_debut')
            ->add('evaluation')
            ->add('date_fin')
            ->add('type')
            ->add('prix')
            ->add('img',FileType::class, array(
        'label' => 'Ajouter une image',
        'mapped' => false,
        )
    )
            ->add('formateur')
            ->add('pdf_file',FileType::class, array(
                'label' => 'Ajouter un fichier pdf',
                'mapped' => false,
            ))
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Formation::class,
        ]);
    }
}
