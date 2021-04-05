/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy.entity;

import kacademy.entity.JavaMailUtil;
import javax.mail.MessagingException;

/**
 *
 * @author 21655
 */
public class JavaMail {
    public static void main(String[] args) throws  Exception {
        JavaMailUtil.sendMail("zahra.pidev@gmail.com");
    }
    
}
