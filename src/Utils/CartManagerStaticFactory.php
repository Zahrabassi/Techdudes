<?php


namespace App\Utils;


class CartManagerStaticFactory
{

    public static function createCartManager()
    {
        return new CartManager();
    }


}