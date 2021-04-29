<?php

namespace App\Form;

use App\Entity\Cour;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CourType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nomCour', TextType::class,[
                'attr' => [
                    'placeholder' => 'Entrer le nom du cour',

                ],
            ])
            ->add('nomEnseignant', TextType::class,[
                'attr' => [
                    'placeholder' => 'Entrer le nom du l enseignant',

                ],
            ])
            ->add('description', TextType::class,[
                'attr' => [
                    'placeholder' => 'Entrer la description du Cour',

                ],
            ])

            ->add('img',FileType::class)

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Cour::class,
        ]);
    }
}
