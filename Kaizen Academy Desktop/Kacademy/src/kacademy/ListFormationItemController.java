/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.io.IOException;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import kacademy.entity.Formation;

/**
 *
 * @author LENOVO
 */
public class ListFormationItemController {

    @FXML
    private AnchorPane container;
    @FXML
    private Label lb_titre;
    @FXML
    private Label lb_description;
    @FXML
    private Label lb_formateur;
    @FXML
    private Label lb_prix;
    @FXML
    private Button btn_acheter;
    @FXML
    private Button btn_confirm;
    @FXML
    private ImageView img;

    public ListFormationItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/FormationItemShop.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Formation data) {
        
        
        btn_acheter.managedProperty().bind(btn_acheter.visibleProperty());
        btn_confirm.managedProperty().bind(btn_confirm.visibleProperty());
        
        btn_acheter.setVisible(true);
        btn_confirm.setVisible(false);
        
        if (data != null) {
            System.out.println(data.toString());
            lb_titre.setText(data.getTitre());
            lb_description.setText(data.getDescription());
            lb_formateur.setText(data.getNom_form() + " " + data.getPrenom_form());
            lb_prix.setText(""+data.getPrix()+" TND");
            img.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString()+"/icons/"+data.getImg()));
            
            btn_acheter.setOnAction(e->{
                PanierShop.addItem(data);
                btn_acheter.setVisible(false);
                btn_confirm.setVisible(true);
                HomeFormationShopController.getInstance().UpdatePanierText();                //ConfirmAchatController.data = data ;
                //HomeShopController.getInstance().goConfirmAchat();
                
                
            });
            
          
        } else {
            System.out.println("data empty !");
        }

    }

    public AnchorPane getBox() {
        return container;
    }
}
