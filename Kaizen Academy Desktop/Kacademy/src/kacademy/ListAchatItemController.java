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
import kacademy.entity.Achat;
import kacademy.entity.Formation;

/**
 *
 * @author LENOVO
 */
public class ListAchatItemController {

    @FXML
    private AnchorPane container;
    @FXML
    private Label lb_titre;
    @FXML
    private Label lb_prix;
    @FXML
    private Label lb_date;

    @FXML
    private ImageView img;

    public ListAchatItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/MesAchatItem.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Achat data) {
        
        

        
        if (data != null) {
            System.out.println(data.toString());
            lb_titre.setText(data.getFormation().getTitre());
            lb_prix.setText(""+data.getPrix()+" TND");
            img.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString()+"/icons/"+data.getFormation().getImg()));
            lb_date.setText(data.getDate());
            
  
            
          
        } else {
            System.out.println("data empty !");
        }

    }

    public AnchorPane getBox() {
        return container;
    }
}
