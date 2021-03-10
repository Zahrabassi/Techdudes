/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tn.kaizen.entity.User;
import tn.kaizen.service.Service;
import tn.kaizen.service.UserService;

/**
 * FXML Controller class
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class StudentDashboardController implements Initializable {

    @FXML
    private Label studentIdLabel;
    @FXML
    private AnchorPane dataPane;
    @FXML
    private TextField name;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField newpwd;
    @FXML
    private Button updatedata;
    @FXML
    private AnchorPane CodePane;
    @FXML
    private TextField codefield;
    @FXML
    private Button checkCode;
    @FXML
    private Button btnDelete;
    @FXML
    private Label updatedatastatus;
    @FXML
    private AnchorPane deleteAccountPane;
    @FXML
    private TextField codefield1;
    @FXML
    private Button checkCode1;
    @FXML
    private Button canceldelete;
    @FXML
    private Label deletestatus;
        private String mail;
     int randomCode;
     String n,u,e,p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       dataPane.setVisible(false);
        CodePane.setVisible(false);
        deleteAccountPane.setVisible(false);
        studentIdLabel.setText(LoginUIController.getUsername());
    }    
        
        private void fetchTeacherInformationFromDatabase() throws SQLException {
        String teacherId = studentIdLabel.getText();
        Service teacherOp = new UserService();
        User teacher = teacherOp.getUserdata(teacherId);
        name.setText(teacher.getName());
        username.setText(teacherId);
        email.setText(teacher.getEmail());
        mail=teacher.getEmail();
        newpwd.setText(teacher.getPassword());
        //To change body of generated methods, choose Tools | Templates.
    }  
        
    @FXML
    private void navigatetoHome(ActionEvent event) {
        dataPane.setVisible(true);
        deleteAccountPane.setVisible(false);
         updatedatastatus.setText(null);
            try {
            fetchTeacherInformationFromDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void handleDashboardSignout(ActionEvent actionEvent) throws IOException {
        /*
         * Signing out will take user to login page
         * */
        Parent LoginUIParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/LoginUI.fxml"));
        Scene LoginUIScene = new Scene(LoginUIParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(LoginUIScene);
        window.show();
    }

    @FXML
    private void handlehome(ActionEvent event) {
        dataPane.setVisible(false);
        CodePane.setVisible(false);
        deleteAccountPane.setVisible(false);
        updatedatastatus.setText(null);
    }

    @FXML
    private void handleupdatedata(ActionEvent event) throws SQLException {
             n= name.getText();
             u= username.getText();
             e= email.getText();
             p=newpwd.getText();
        CodePane.setVisible(true);
        String to =mail;   
        if(!to.isEmpty())
        {
            //User user = new User(to);
                 Random ran = new Random();
                    randomCode = ran.nextInt(999999);
                    System.out.println(randomCode);        
                    Properties props = new Properties();
                    props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.prot", "587");
                        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                        Session session = Session.getInstance(props,
                                new javax.mail.Authenticator() 
                                {
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() 
                                    {

                                        return new PasswordAuthentication("kaizenacademysndr@gmail.com","kaizenacademy");
                                    }   
                                }
                        );
                        try 
                        {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("kaizenacademysndr@gmail.com"));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                            message.setSubject("Code vérification changement du compte etudiant");
                            message.setText("Votre code est "+randomCode);
                            Transport.send(message);
                        } catch (MessagingException e) 
                        {
                            e.printStackTrace();
                        }
            
            
        }
        
                        /*********************************************************************************/
    
    }

    @FXML
    private void handlecheckCode(ActionEvent event) throws SQLException {
        if (Integer.valueOf(codefield.getText())== randomCode){
            
            User user = new User(n, e, u, p);
            Service teacherops = new UserService();

            if(teacherops.isValid(e))
            {       teacherops.updateUser(user,studentIdLabel.getText());
                    studentIdLabel.setText(u);
                    fetchTeacherInformationFromDatabase();
                    CodePane.setVisible(false);
                    updatedatastatus.setText("Code valide changement fait avec succès!");
                
            }
            
        }
        else{
            System.out.println("Code invalid");
            updatedatastatus.setText("Code invalid");
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
        if(event.getSource()==btnDelete)
        {
                 Random ran = new Random();
                    randomCode = ran.nextInt(999999);
                    System.out.println(randomCode);        
                    Properties props = new Properties();
                    props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.host", "smtp.gmail.com");
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.prot", "587");
                        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                        Session session = Session.getInstance(props,
                                new javax.mail.Authenticator() 
                                {
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() 
                                    {

                                        return new PasswordAuthentication("kaizenacademysndr@gmail.com","kaizenacademy");
                                    }   
                                }
                        );
                        try 
                        {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("kaizenacademysndr@gmail.com"));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
                            message.setSubject("Suppression du compte etudiant");
                            message.setText("Votre code est "+randomCode);
                            Transport.send(message);
                        } catch (MessagingException e) 
                        {
                            e.printStackTrace();
                        }
            deleteAccountPane.setVisible(true);
        }
        
                        /*********************************************************************************/
    
        }

    @FXML
    private void handledelete(ActionEvent event) throws SQLException, IOException {
        if(event.getSource()==checkCode1){
            if (Integer.valueOf(codefield1.getText())== randomCode){
                Service teacherOp = new UserService();
                User teacher = teacherOp.getUserdata(studentIdLabel.getText());
                teacherOp.deleteButton(teacher);
                Parent LoginUIParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/LoginUI.fxml"));
                Scene LoginUIScene = new Scene(LoginUIParent);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(LoginUIScene);
                window.show();
            }
            else{
                deletestatus.setText("Code invalid");
            }
            }
            else if (event.getSource()==canceldelete) {
                deletestatus.setText(null);
                codefield1.clear();
                deleteAccountPane.setVisible(false);
                
            }
    }
    
    }
    