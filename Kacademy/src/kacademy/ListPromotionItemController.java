/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import kacademy.dao.PromotionDao;
import kacademy.entity.Achat;
import kacademy.entity.Formation;
import kacademy.entity.Promotion;

/**
 *
 * @author LENOVO
 */
public class ListPromotionItemController {

    @FXML
    private AnchorPane container;
    @FXML
    private Label lb_titre;
    @FXML
    private Label lb_formateur;
    @FXML
    private Label lb_promo;
    @FXML
    private Label lb_date;
    @FXML
    private Button btn_annuler;
    @FXML
    private Button btn_edit;
    @FXML
    private ImageView img;

    public ListPromotionItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/PromotionItem.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Promotion data) {
        
        

        
        if (data != null) {
            System.out.println(data.toString());
          lb_titre.setText(data.getFormation().getTitre());
            lb_date.setText(data.getDate());
            lb_promo.setText(data.getPromo()+" %");
           lb_formateur.setText(data.getFormation().getNom_form()+" "+data.getFormation().getPrenom_form());
            img.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString()+"/icons/"+data.getFormation().getImg()));
            
            btn_annuler.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Annulation de promotion");
            alert.setHeaderText("Confirmation d'annulation");
            alert.setContentText("Voulez-vous annuler la promotion ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
               
                PromotionDao p_dao = PromotionDao.getInstance();
                p_dao.delete(data);
                HomeShopController.getInstance().goAdminPromo();

            } else {
                // ... user chose CANCEL or closed the dialog
            }

        });
            btn_edit.setOnAction(e->{
                HomeShopController.getInstance().goAdminPromoEdit(data);
            });
            
          
        } else {
            System.out.println("data empty !");
        }

    }

    public AnchorPane getBox() {
        return container;
    }
}
