<?php


namespace App\Manager;


use App\Utils\Cart;
use Symfony\Component\HttpFoundation\Session\Attribute\NamespacedAttributeBag;
use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\HttpFoundation\Session\Storage\NativeSessionStorage;

class CartManager
{

    /**
     * @var Session
     */
    protected $session;

    protected $cart;

    public function __construct()
    {
        $storage = new NativeSessionStorage();
        $attributes = new NamespacedAttributeBag();
        $this->session = new Session($storage, $attributes);
        $this->cart = new Cart($this->session);
    }

    /**
     * @return Cart
     */
    public function getCart()
    {
        return $this->cart;
    }

}