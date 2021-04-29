<?php

namespace App\Form;

use App\Entity\Devoir;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;


class UploadType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nomDevoir')
            ->add('nomDevoir', FileType::class)
            ->add('description',ChoiceType::class,['choices' => ['a faire' =>'a faire','encours' => 'encours','fait' => 'fait']])
            ->add('Ajouter', SubmitType::class,
                ['attr'=>['formnovalidate'=>'formnovalidate']]
            )

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Devoir::class,
        ]);
    }
}
