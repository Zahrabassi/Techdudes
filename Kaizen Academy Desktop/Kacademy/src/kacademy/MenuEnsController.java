/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21655
 */
public class MenuEnsController implements Initializable {

    @FXML
    private Button btn_deco;
    @FXML
    private Pane COUR;
    @FXML
    private Button navigatetoProfile;
    @FXML
    private ImageView img_logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       navigatetoProfile.setOnAction(e->{
            HomeShopController.getInstance().goProfileEns();
        });
        btn_deco.setOnAction(e->{
            Session.user = null;
            HomeShopController.getInstance().goLogin() ;});
    }    

    @FXML
    private void goToCour(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("views/CourMain.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void goToDevoir(ActionEvent event) throws IOException {
               Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("views/EtudiantDevoir.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

    @FXML
    private void goToMeet(ActionEvent event) throws IOException {
         Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("views/MeetMain.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
    
}
