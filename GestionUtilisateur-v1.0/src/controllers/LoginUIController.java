/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import tn.kaizen.entity.LoginInfo;
import tn.kaizen.entity.User;
import tn.kaizen.service.UserDatabaseOperation;
import tn.kaizen.service.UserDatabaseOperationImplementation;

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
    private static String passableUsername; // it has getter method

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userTypes = FXCollections.observableArrayList("Admin", "Enseignant", "Etudiant");
        signupUserTypeComboBox.setItems(userTypes);
        signupUserTypeComboBox.getSelectionModel().selectLast();
    }    

    @FXML
    private void handleLoginButton(ActionEvent event) throws SQLException {
         String username = loginUsernameField.getText();
        String password = loginPasswordField.getText();

        if (!username.isEmpty() && !password.isEmpty()){
            loginStatusLabel.setText(null);
            //User user = new User(name, email, username, password, "temp");
            LoginInfo logins = new LoginInfo(username, password);
            UserDatabaseOperation userops = new UserDatabaseOperationImplementation();
            /*
            * If login credentials are valid, we change scene to corresponding user's dashboard
            * */
            if (userops.verifyUserLogin(logins)){
                System.out.println("Verified");
                passableUsername = logins.getUsername();
                String userType = userops.getUserType(logins);
                try {
                    Parent DashboardParent;

                    switch (userType) {
                    // user is "Student"
                        case "Student":
                            DashboardParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/StudentDashboard.fxml"));
                            break;
                    // user is "Teacher"
                        case "Teacher":
                            DashboardParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/TeacherDashboard.fxml"));
                            break;
                        default:
                            // user is "Chairman"
                            DashboardParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/ChairmanDashboard.fxml"));
                            break;
                    }

                    Scene StudentDashboardScene = new Scene(DashboardParent);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(StudentDashboardScene);
                    window.show();

                } catch (IOException e) {
                }
            }
            else{
                System.out.println("User insertion error!");
                loginStatusLabel.setText("Invalid credentials!");
            }
        }
        else{
            System.out.println("Empty fields!");
            loginStatusLabel.setText("Required fields can not be empty!");
        }
    }

    @FXML
    private void handleLoginResetButton(ActionEvent event) {
        loginUsernameField.clear();
        loginPasswordField.clear();
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
        String password = signupPasswordField.getText();
        String userType = signupUserTypeComboBox.getValue();
        String name = signupNameField.getText();
        String email = signupEmailField.getText();

        // If the required fields are not empty, then we insert into database
        if (!name.isEmpty() && !username.isEmpty()  && !password.isEmpty() && userType != null){
            signupStatusLabel.setText(null); // remove any previous error message
            User user = new User(name, email, username, password, userType);
            UserDatabaseOperation userops = new UserDatabaseOperationImplementation();

            if (userops.exists(user)){
                signupStatusLabel.setText("Username already exists!");
                return;
            }

            if (!userops.insertUser(user)){
                signupStatusLabel.setText("Signed Up successfully!");
            }
            else{
                signupStatusLabel.setText("Sign Up failed!");
            }
        }
        else{
            //System.out.println("User insertion error!");
            signupStatusLabel.setText("Invalid Credentials/Required Field(s) are empty.");
        }
    }
    public static String getUsername(){
        return passableUsername;
    }
}
