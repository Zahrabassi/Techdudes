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
import javafx.scene.text.Text;
import kacademy.dao.PromotionDao;
import kacademy.entity.Achat;
import kacademy.entity.Formation;
import kacademy.entity.MessageReclam;
import kacademy.entity.Promotion;
import kacademy.entity.Reclamation;

/**
 *
 * @author LENOVO
 */
public class ListMessageReclamItemController {

    @FXML
    private AnchorPane container;
    @FXML
    private Label lb_user;
    @FXML
    private Label lb_date;
    @FXML
    private Text lb_message;



    public ListMessageReclamItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/MessageItem.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(MessageReclam data) {

        if (data != null) {
            System.out.println(data.toString());
            lb_user.setText(data.getUser_send().getUsername());
            lb_message.setText(data.getMessage());
            lb_date.setText(data.getDate());

        } else {
            System.out.println("data empty !");
        }

    }

    public AnchorPane getBox() {
        return container;
    }
}
