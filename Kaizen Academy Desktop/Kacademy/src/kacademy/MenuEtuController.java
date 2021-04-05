/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21655
 */
public class MenuEtuController implements Initializable {

    @FXML
    private Button btn_deco;
    @FXML
    private Button btn_achat;
    @FXML
    private Button btn_reclam;
    @FXML
    private Button btn_cour;
    @FXML
    private Button btn_dev;
    @FXML
    private Button btn_meet;
    @FXML
    private Button btn_formation;
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
            HomeShopController.getInstance().goProfileEtu();
        });
        btn_achat.setOnAction(e->{
            HomeShopController.getInstance().goHomeSHop();
        });
        btn_reclam.setOnAction(e->{
            HomeShopController.getInstance().goMesReclam();
        });
        btn_deco.setOnAction(e->{
            Session.user = null;
            HomeShopController.getInstance().goLogin();
        });
         btn_cour.setOnAction(e->{
            Session.user = null;
            HomeShopController.getInstance().goEtuCour();
        });
           btn_dev.setOnAction(e->{
            Session.user = null;
            HomeShopController.getInstance().goEtuDev();
        });
              btn_meet.setOnAction(e->{
            Session.user = null;
            HomeShopController.getInstance().goEtuMeet();
        });
        btn_formation.setOnAction(e->{
            Session.user = null;
            HomeShopController.getInstance().goMesAchat();
        });
    }  
}
