/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class ControleSaisie {
    
    
    private static Matcher matcher;

    public static boolean isString(String text) {



        if (Pattern.matches("^[a-zA-Z]+$",text)) {

            return true;

        } 

            return false;

    }

     public static boolean isNull(String text){

         if(text == ""){

             return true; //null

         }

         return false ;//n'est pas vide

     }

          public static boolean isUsername(String text) {



        if (Pattern.matches("^[A-Za-z0-9]+$+",text) ) {

            return true;

        } 

            return false;

    }

          public static boolean DateNullCS(String date){

            if(date == ""){

                return true ;

            }

              return false;

          }

      public static boolean adresse(String text) {



        if (Pattern.matches(text,"^[A-Z a-z 0-9]+$")) {

            return true;

        }

            return false;

    }

          public static boolean iscin(String text) {



        if (Pattern.matches(text,"^[0-9]+$")&& text.length()== 8) {

            return true;

        } else {

            return false;

        }

    }

                  public static boolean isTel(String text) {



        if (Pattern.matches(text,"^[0-9]+$")&& text.length()==8) {

            return true;

        } else {

            return false;

        }

    }



     private static final String EMAIL_PATTERN

            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"

            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                       private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);

                          private static final String pwd=  "^[A-Za-z0-9]+$";

                                private static Pattern pattern1 = Pattern.compile(pwd);

     public static boolean valiemail(final String hex) {

        matcher = pattern.matcher(hex);

        return matcher.matches();

    }

      public static boolean validPasswor(final String hex) {

        matcher = pattern1.matcher(hex);

        return matcher.matches();

    }

}
