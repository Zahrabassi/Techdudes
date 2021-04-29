<?php

namespace App\Controller;

use App\Manager\CartManager;
use Flosch\Bundle\StripeBundle\Stripe\StripeClient;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/cart")
 */
class CartController extends AbstractController
{

    /**
     * @var StripeClient
     */
    public $stripeClient;

    /**
     * @return StripeClient
     */
    public function getStripeClient()
    {
        if ($this->stripeClient === null)
            $this->stripeClient = $this->get('flosch.stripe.client');
        return $this->stripeClient;
    }

    /**
     * @Route("/", name="cart")
     * @param Request $request
     * @return Response|null
     */
    public function indexAction(Request $request, CartManager $cartManager)
    {
        $cart = $cartManager->getCart();
        //var_dump($cart->getItems());

        return $this->render('front/cart/index.html.twig', [
            'cart' => $cart,
            //'count' => $cart->count()
        ]);
    }

    /**
     * @Route("/clear", name="cart_clear")
     * @return Response|null
     */
    public function clearAction(CartManager $cartManager)
    {
        $cart = $cartManager->getCart();
        $cart->clear();

        return $this->redirectToRoute('cart');
    }

    /**
     * Checkout process of the cart
     * @Route("/checkout", name="cart_checkout")
     * @return Response|null
     */
    public function checkoutAction(CartManager $cartManager)
    {

        $cart = $cartManager->getCart();


        $cartItems = $cart->getItems();
        $cartTotal = $cart->getDiscountTotal();
        $discount = $cart->getAppliedDiscount();

        //$this->getStripeClient()->createCharge($cartTotal, 'EUR', null);

        $this->addFlash('success', 'Checkout completed. Your order will be shipped soon.');
        $cart->clear();

        return $this->render('front/invoice/index.html.twig', [
            'cart_items' => $cartItems,
            'total' => $cartTotal,
            'discount' => $discount,
            'orderId' => 0,
        ]);
    }
}
