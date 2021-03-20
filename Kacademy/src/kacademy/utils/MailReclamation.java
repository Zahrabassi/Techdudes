/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import kacademy.entity.Formation;
import kacademy.entity.Promotion;
import kacademy.entity.Reclamation;

/**
 *
 * @author LENOVO
 */
public class MailReclamation {

    public static void sendMail(String recepient, Reclamation p) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        //Your gmail address
        String myAccountEmail = "kacademy.client@gmail.com";
        //Your gmail password
        String password = "ka1234ka";

        //Create a session with account credentials
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient, p);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, Reclamation p) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Réclamation " + p.getSujet());
            String htmlCode
                    = "<div style='width: 100%; background: #4285f4; text-align: center; padding-top: 15px; padding-bottom: 15px;'>"
                    + "<h2 style='color:#fff'> Réclamation </h2>"
                    + "</div> <br/>"
                    + "<b>Bonjour , </b> <br/>"
                    + "<b>Nous avons recus votre réclamation avec le sujet " + p.getSujet() + "</b> <br/>"
                    + "<b>vous recevrez une réponse dans 48h max </b> </br>"
                    + "<p>vérifier la boite de chat dans réclamation afin de disscuter avec les responsable</p> <br/> <br/>"
                    + "<div style='width: 100%; background: #4285f4; text-align: center; padding-top: 15px; padding-bottom: 15px;'>"
                    + "<h2 style='color:#fff'> Kacademy </h2>"
                    + "</div>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(MailReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
