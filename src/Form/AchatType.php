<?php

namespace App\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class AchatType extends AbstractType
{
    /**
     * {@inheritdoc}
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            /*->add('quantity', IntegerType::class, [
                'attr' => ['min' => 1]
            ])*/
            ->add('add', SubmitType::class, [
                'label' => 'Add to cart'
            ]);
    }

    /**
     * {@inheritdoc}
     */
    public
    function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => 'App\Entity\Achat'
        ));
    }

    /**
     * {@inheritdoc}
     */
    public
    function getBlockPrefix()
    {
        return 'shopbundle_achat';
    }


}
