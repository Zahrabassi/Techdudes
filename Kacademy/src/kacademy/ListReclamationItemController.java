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
import kacademy.dao.MessageReclamDao;
import kacademy.dao.PromotionDao;
import kacademy.dao.ReclamationDao;
import kacademy.entity.Achat;
import kacademy.entity.Formation;
import kacademy.entity.Promotion;
import kacademy.entity.Reclamation;

/**
 *
 * @author LENOVO
 */
public class ListReclamationItemController {

    @FXML
    private AnchorPane container;
    @FXML
    private Label lb_sujet;
    @FXML
    private Label lb_type;
    @FXML
    private Label lb_date;
    @FXML
    private Label lb_nbr_msg;
    @FXML
    private Label lb_user;
    @FXML
    private HBox hb_user;

    @FXML
    private ImageView img;

    public ListReclamationItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/ReclamationItem.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(Reclamation data) {

        if (data != null) {
            System.out.println(data.toString());
            lb_sujet.setText(data.getSujet());
            lb_type.setText(data.getType());
            lb_date.setText(data.getDate());
            
            
            MessageReclamDao r_dao = MessageReclamDao.getInstance();
            lb_nbr_msg.setText(""+r_dao.getAllByReclam(data.getId_reclam()).size());
            
            if(Session.user.getType().equals("etud_reclam")){
                hb_user.setVisible(false);
            }else{
                hb_user.setVisible(true);
                lb_user.setText(data.getUser().getUsername());
            }

        } else {
            System.out.println("data empty !");
        }

    }

    public AnchorPane getBox() {
        return container;
    }
}
