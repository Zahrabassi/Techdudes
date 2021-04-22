<?php

namespace App\Form\User;


use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints\ValidCaptcha;
use Symfony\Component\Validator\Constraints\Choice;

class RegistrationType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     */
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder

            ->add('name')
            ->add('email')
            ->add('password', RepeatedType::class, [
                'type' => PasswordType::class,
                'first_options' => ['label' => 'user.password.first'],
                'second_options' => ['label' => 'user.password.second'],
            ])
            ->add('role',ChoiceType::class,
                [
                    'placeholder' => 'Choose an option',
                    'choices' => ['Enseignant'=> 'ROLE_USER',
                        'Etudiant' => 'ROLE_USER',
                        'Admin' => 'ROLE_ADMIN']

                    ])
            ->add('captchaCode', CaptchaType::class, array(
                'captchaConfig' => 'ExampleCaptchaUserRegistration',
                'constraints' => [
                    new ValidCaptcha([
                        'message' => 'Invalid captcha, please try again',
                    ]),
                ],
            ));
        parent::buildForm($builder, $options);
    }

    /**
     * @param OptionsResolver $resolver
     */
    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
                'data_class' => User::class,
                'validation_groups' => ['Default', 'Registration']
            ]
        );
    }
}