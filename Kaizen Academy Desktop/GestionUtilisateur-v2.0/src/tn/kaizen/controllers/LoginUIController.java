/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import tn.kaizen.entity.User;
import tn.kaizen.service.Service;
import tn.kaizen.service.UserService;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.mindrot.jbcrypt.BCrypt;



/**
 * FXML Controller class
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class LoginUIController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private Button loginButton;
    @FXML
    private Button loginResetButton;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Label loginStatusLabel;
    @FXML
    private TextField signupUsernameField;
    @FXML
    private ComboBox<String> signupUserTypeComboBox;
    @FXML
    private Button signupButton;
    @FXML
    private Button signupResetButton;
    @FXML
    private PasswordField signupPasswordField;
    @FXML
    private Label signupStatusLabel;
    @FXML
    private TextField signupNameField;
    @FXML
    private TextField signupEmailField;
    private ObservableList < String > userTypes;
    private static String passableUsername;
    @FXML
    private Button forgetpwd;
    @FXML
    private AnchorPane forgetpassPane;
    @FXML
    private TextField fpwdmail;
    @FXML
    private Button sendcode;
    @FXML
    private Button verifycode;
    @FXML
    private TextField codefield;
    @FXML
    private TextField newpass;
    @FXML
    private Button savenewpass;
    @FXML
    private Label fgpwdstatus;
    int randomCode;
    @FXML
    private AnchorPane newpassPane;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        forgetpassPane.setVisible(false);
        newpassPane.setVisible(false);
        userTypes = FXCollections.observableArrayList("Enseignant", "Etudiant","Admin");
        signupUserTypeComboBox.setItems(userTypes);
        signupUserTypeComboBox.getSelectionModel().selectLast();
    }    

    @FXML
    private void handleSignUpResetButton(javafx.event.ActionEvent actionEvent) {
        signupNameField.clear();
        signupEmailField.clear();
        signupUsernameField.clear();
        signupPasswordField.clear();
        signupStatusLabel.setText(null);
        signupUserTypeComboBox.getSelectionModel().selectLast();
    }

    @FXML
    private void handleSignUpButton(javafx.event.ActionEvent actionEvent) throws SQLException {
        /*
        * Getting all the values from textfields and combobox if "Signup" page
        * */
        String username = signupUsernameField.getText();
        //String password =BCrypt.hashpw(signupPasswordField.getText(), BCrypt.gensalt());
        String password = signupPasswordField.getText();
        String userType = signupUserTypeComboBox.getValue();
        String name = signupNameField.getText();
        String email = signupEmailField.getText();
        

        // If the required fields are not empty, then we insert into database
        if (!name.isEmpty() && !username.isEmpty()  && !password.isEmpty() && userType != null)
        {
            signupStatusLabel.setText(null); // remove any previous error message
            User user = new User(name, email, username, password, userType);
            Service userops = new UserService();
            if(userops.isValid(email))
            {

                if (userops.exists(user))
                {
                    signupStatusLabel.setText("Utilisateur déjà existe!");
                }

                if (userops.insertUser(user))
                {
                    signupStatusLabel.setText("Inscription réussie!");
                }
                else
                {
                    signupStatusLabel.setText("Inscription échouée!");
                }
            }
            else
            {
                signupStatusLabel.setText("Email invalid");
            }
        }
        else
        {
        
            signupStatusLabel.setText("Champs vide");
        }
}

    @FXML
    private void handleLoginButton(ActionEvent actionEvent) throws SQLException {
        String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();
        //String password =BCrypt.hashpw(loginPasswordField.getText(), BCrypt.gensalt());
        
        if (!username.isEmpty() && !password.isEmpty()){
            loginStatusLabel.setText(null);
            //User user = new User(name, email, username, password, "temp");
            User logins = new User(username, password);
            Service userops = new UserService();
            /*
            * If login credentials are valid, we change scene to corresponding user's dashboard
            * */
            if (userops.verifyUserLogin(logins)){
                System.out.println("Verifié");
                passableUsername = logins.getUsername();
                String userType = userops.getUserType(logins);
                try {
                    Parent DashboardParent;

                    switch (userType) {
                    // user is "Etudiant"
                        case "Etudiant":
                            DashboardParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/StudentDashboard.fxml"));
                            break;
                    // user is "Enseignant"
                        case "Enseignant":
                            DashboardParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/TeacherDashboard.fxml"));
                            break;
                        default:
                            // user is "Admin"
                            DashboardParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/ChairmanDashboard.fxml"));
                            break;
                    }

                    Scene StudentDashboardScene = new Scene(DashboardParent);

                    Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    window.setScene(StudentDashboardScene);
                    window.show();

                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            else{
                System.out.println("Erreur d'insertion!");
                loginStatusLabel.setText("Invalide!");
            }
        }
        else{
            System.out.println("champs vide!");
            loginStatusLabel.setText("Remplir les champs vide!");
        }
    }

    @FXML
    private void handleLoginResetButton(ActionEvent actionEvent) {
        loginUsernameField.clear();
        loginPasswordField.clear();
    }

    public static String getUsername(){
        return passableUsername;
    }

    @FXML
    private void handleforgetpwd(ActionEvent event) {
       forgetpassPane.setVisible(true);
    }

    @FXML
    private void handlesendcode(ActionEvent event) throws SQLException {
        String to = fpwdmail.getText();   
        if(!to.isEmpty())
        {
            fgpwdstatus.setText(null);
            User user = new User(to);
            Service mailcheck = new UserService();
            if(mailcheck.isValid(to))
            {
                if(mailcheck.Mailexists(user))
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
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                            message.setSubject("Réinitialiser mot de passe");
                            message.setText("Votre code est "+randomCode);
                            Transport.send(message);
                        } catch (MessagingException e) 
                        {
                            e.printStackTrace();
                        }
            }
            
            }
            else{
                fgpwdstatus.setText("Mail inexistant");
            }
        }
        else{
            fgpwdstatus.setText("Champ vide.");
            }
        
                        /*********************************************************************************/
    }

    @FXML
    private void handleverifycode(ActionEvent event) {
        if (Integer.valueOf(codefield.getText())== randomCode)
        {
            fgpwdstatus.setText("Code Vérifié");
            newpassPane.setVisible(true);
            forgetpassPane.setVisible(false);
            
        }
        else{
            fgpwdstatus.setText("Code invalid");
            codefield.clear();
            }
    }

    @FXML
    private void handlesavenewpass(ActionEvent event) {
        String password=newpass.getText();
        String email=fpwdmail.getText();
        Service userops = new UserService();
        userops.UpdatePwd(email,password);
        newpassPane.setVisible(false);
    }
    
}
