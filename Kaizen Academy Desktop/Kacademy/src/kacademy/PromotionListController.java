/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import kacademy.dao.PromotionDao;
import kacademy.entity.Formation;
import kacademy.entity.Promotion;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PromotionListController implements Initializable {

    @FXML
    private ListView list_promo;
    @FXML
    private ImageView img_logo;
    @FXML
    private Button btn_add;
    @FXML
    private TextField txt_search;
    @FXML
    private Button btn_tri;
    @FXML
    private Button btn_deco;
    @FXML
    private ImageView img_chart;
    @FXML
    private Button btn_chart;

    private List<Promotion> data;
    ObservableList observ_list_data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        img_logo.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString() + "/icons/ka_logo.png"));
        img_chart.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString() + "/icons/chart.png"));
        PromotionDao p_dao = PromotionDao.getInstance();
        data = p_dao.displayAll();
        observ_list_data.setAll(data);
        list_promo.setItems(observ_list_data);
        list_promo.setCellFactory(new Callback<ListView<Promotion>, javafx.scene.control.ListCell<Promotion>>() {
            @Override
            public ListCell<Promotion> call(ListView<Promotion> listView) {
                return new ListPromotionItem();
            }

        });

        btn_add.setOnAction(e -> {
            HomeShopController.getInstance().goAdminPromoAdd();
        });
        
        btn_tri.setOnAction(e -> {
            Collections.sort(observ_list_data);
        });
        
        btn_chart.setOnAction(e -> {
            HomeShopController.getInstance().goAdminTopVente();
        });

        list_promo.setPrefHeight(126 * data.size() + 2);

        list_promo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        txt_search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("serach process " + newValue);
                list_promo.getSelectionModel().clearSelection();
                if (newValue.trim().length() > 0) {
                    for (int i = 0; i < data.size(); i++) {
                        if (String.valueOf(data.get(i).getPromo()).contains(newValue) || data.get(i).getFormation().getTitre().contains(newValue)) {
                            list_promo.getSelectionModel().select(i);
                        }

                    }
                }
            }
        });
        
        btn_deco.setOnAction(e ->{
            if(Session.user.getType().equals("Etudiant"))
                HomeShopController.getInstance().goMenuEtudant();
            else
                HomeShopController.getInstance().goMenuAdmin();
        });

    }

}
