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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.kaizen.entity.Chairman;
import tn.kaizen.service.ChairmanDatabaseOperation;
import tn.kaizen.service.ChairmanDatabaseOperationImplementation;

/**
 * FXML Controller class
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class ChairmanDashboardController implements Initializable {

    @FXML
    private Label chairmanNameLabel;
    @FXML
    private Label chairmanIdLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try{
            fetchChairmanInformationFromDatabase();
           

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }    

    @FXML
    private void navigateToTeacherDashboard(ActionEvent event) {
    }

    @FXML
    private void handleDashboardSignout(ActionEvent event) throws IOException {
        /*
         * Signing out will take user to login page
         * */
        Parent LoginUIParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/LoginUI.fxml"));
        Scene LoginUIScene = new Scene(LoginUIParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(LoginUIScene);
        window.show();
    }

    private void fetchChairmanInformationFromDatabase() throws SQLException {
        String chairmanId = LoginUIController.getUsername();
        ChairmanDatabaseOperation chairmanOp = new ChairmanDatabaseOperationImplementation();
        Chairman chairman = chairmanOp.getChairman(chairmanId);
        System.out.println(chairman);
        chairmanIdLabel.setText(chairman.getChairmanInitial());
        chairmanNameLabel.setText(chairman.getChairmanName());//To change body of generated methods, choose Tools | Templates.
    }
    
}
