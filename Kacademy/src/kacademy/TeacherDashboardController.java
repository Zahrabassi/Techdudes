/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import kacademy.dao.Service;
import kacademy.dao.UserService;
import kacademy.entity.User;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class TeacherDashboardController implements Initializable {

    @FXML
    private Label teacherIdLabel;
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
    private Label updatedatastatus;
    @FXML
    private Button btnDelete;
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
    @FXML
    private ImageView img_logo;
    private String mail;
     int randomCode;
     String n,u,e,p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         img_logo.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString()+"/icons/ka_logo.png"));
        dataPane.setVisible(true);
        CodePane.setVisible(false);
        deleteAccountPane.setVisible(false);
        teacherIdLabel.setText(LoginUIController.getUsername());
    }    
        
        private void fetchTeacherInformationFromDatabase() throws SQLException {
        String teacherId = teacherIdLabel.getText();
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
    private void handleDashboardSignout(ActionEvent actionEvent) throws IOException {
        /*
         * Signing out will take user to login page
         * */
        HomeShopController.getInstance().goLogin();
    }

    @FXML
    private void handlehome(ActionEvent event) {
        HomeShopController.getInstance().goMenuAdmin();
        CodePane.setVisible(false);
        deleteAccountPane.setVisible(false);
        updatedatastatus.setText(null);
    }

    @FXML
    private void handleupdatedata(ActionEvent event) throws SQLException, AddressException, MessagingException {
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
                            message.setSubject("Code vérification changement du compte enseignant");
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
            {       teacherops.updateUser(user,teacherIdLabel.getText());
                    teacherIdLabel.setText(u);
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
                            message.setSubject("Suppression du compte enseignant");
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
                User teacher = teacherOp.getUserdata(teacherIdLabel.getText());
                teacherOp.deleteButton(teacher);
                HomeShopController.getInstance().goLogin();
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
    
