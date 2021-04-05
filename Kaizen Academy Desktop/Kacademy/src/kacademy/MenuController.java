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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MenuController implements Initializable {
    
    private ImageView img_logo;
    @FXML
    private Button btn_achat;
    @FXML
    private Button btn_reclam;
    @FXML
    private Button btn_deco;
    @FXML
    private Button btn_cour;
    @FXML
    private Button btn_dev;
    @FXML
    private Button btn_meet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
        img_logo.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString()+"/icons/ka_logo.png"));
        
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
        
        
    }    

    
}
