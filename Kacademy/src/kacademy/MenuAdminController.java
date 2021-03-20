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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
public class MenuAdminController implements Initializable {
    @FXML
    private ImageView img_logo;
    @FXML
    private Button btn_promo;
    @FXML
    private Button btn_reclam;
    @FXML
    private Button btn_deco;
    @FXML
    private Button btn_formation;
    @FXML
    private Button btn_evaluation;
    @FXML
    private Button navigatetoProfile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        img_logo.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString()+"/icons/ka_logo.png"));
        navigatetoProfile.setOnAction(e->{
            HomeShopController.getInstance().goProfileAdmin();
        });
        btn_promo.setOnAction(e->{
            HomeShopController.getInstance().goAdminPromo();
        });
        btn_reclam.setOnAction(e->{
            HomeShopController.getInstance().goMesReclam();
        });
        
        btn_deco.setOnAction(e->{
            Session.user = null;
            HomeShopController.getInstance().goLogin();
        });
         btn_reclam.setOnAction(e->{
            HomeShopController.getInstance().goMesReclam();
        });
         btn_formation.setOnAction(e->{
             try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/kacademy/views/home.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btn_evaluation.setOnAction(e->{
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/kacademy/views/evaluation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MenuAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }    
    
}
