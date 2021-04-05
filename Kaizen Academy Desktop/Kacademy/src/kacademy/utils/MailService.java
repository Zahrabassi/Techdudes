/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy.utils;

import java.util.List;
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

/**
 *
 * @author LENOVO
 */
public class MailService {

    public static void sendMail(List<String> recepients , Promotion p) throws Exception {
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
        Message message = prepareMessage(session, myAccountEmail, recepients,p);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, List<String> recepients , Promotion p) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            for(String s : recepients){
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(s)); 
            }
            
            message.setSubject("Nouvelle promotion formation "+p.getFormation().getTitre());
            String htmlCode = "<h1> Offre spécial </h1>  <h2><b>"+p.getFormation().getTitre()+" </b></h2> "
                    + "<div style='width: 100%; background: #4285f4; text-align: center; padding-top: 15px; padding-bottom: 15px;'>"
                    + "<h2 style='color:#fff'> Promotion formation  de <span style='display: inline-block; background: #ec5085; padding: 8px; border-radius: 50%;'>"+p.getPromo()+"%</span></h2>"
                    + "</div> <br/>"
                    + "<b>A propos de la formation </b> <br/>"
                    + "<b>Prix "+( (p.getFormation().getPrix() * p.getPromo())/100)+" TND  </b> </br>"
                    + "<p>"+p.getFormation().getDescription()+"</p> <br/> <br/>"
                    + "<div style='width: 100%; background: #4285f4; text-align: center; padding-top: 15px; padding-bottom: 15px;'>"
                    + "<h2 style='color:#fff'> Kacademy  </h2>"
                    + "</div>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
