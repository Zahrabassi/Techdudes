/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.io.IOException;
import static java.lang.ProcessBuilder.Redirect.to;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EmailController implements Initializable {

    @FXML
    private TextField lekher;
    @FXML
    private TextField mdp;
    @FXML
    private TextField ena;
    @FXML
    private TextField objet;
    @FXML
    private TextArea msg;
    private Label sentBoolValue;
    @FXML
    private Button envoyer;
    @FXML
    private Label setBoolValue;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerOnaction(ActionEvent event) {
        try {
             if (event.getSource()==envoyer){
            sendEmail();
        }
        } catch (Exception e) {
              Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, e);
        }
       
    }
    public void sendEmail() throws Exception{
        String toto = lekher.getText();
        String host= "smtp.gmail.com";
         String myAcount= ena.getText();
         String password = mdp.getText();
        String from = myAcount;
        
        Properties props= System.getProperties();
      props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.prot", "587");
        Session session;
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        session = Session.getInstance(props,new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAcount, password);
            }
        });
        Message m=prepareMessage(session , from , toto );
        Transport.send(m);
    }

    public Message prepareMessage(Session session, String from, String toto ) {
        try {
            Message  m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(toto ));
            m.setSubject("valider");
            m.setText("bonjour cv ?");
           // m.setText("Votre code est "+randomCode);
            return m;
        } catch (Exception e) {
            Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    @FXML
    private void fontionRetour(ActionEvent event) {
        retour.setOnAction(new EventHandler<ActionEvent>() {
                         @Override
                         public void handle(ActionEvent event) {
                             try {
                                 Parent page1 = FXMLLoader.load(EmailController.this.getClass().getResource("/kacademy/views/MenuAdmin.fxml"));
                                 Scene scene = new Scene(page1);
                                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                 stage.setScene(scene);
                                 stage.show();
                             }catch (IOException ex) {
                                 Logger.getLogger(EmailController.class.getName()).log(Level.SEVERE, null, ex);
                             }            }
                     });
    }
    
}

