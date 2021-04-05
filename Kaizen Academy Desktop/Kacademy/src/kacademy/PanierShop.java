/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.util.ArrayList;
import java.util.List;
import kacademy.entity.Formation;

/**
 *
 * @author LENOVO
 */
public class PanierShop {

    public static List<Formation> lst_shop = new ArrayList<>();

    public static void addItem(Formation f) {
        if (lst_shop == null) {
            lst_shop = new ArrayList<>();
        }
        lst_shop.add(f);
    }

    public static boolean checkItemPanier(int id) {

        for (Formation f : lst_shop) {
            if(f.getId() == id)
                return true ;
        }

        return false;
    }
    
    public static void deleteItemFromPanier(int id) {

        for (int i = 0 ; i < lst_shop.size() ; i++) {
            if(lst_shop.get(i).getId() == id){
                lst_shop.remove(i);
                
            }

        }
        System.out.println("after del "+lst_shop.size());

    }

}
