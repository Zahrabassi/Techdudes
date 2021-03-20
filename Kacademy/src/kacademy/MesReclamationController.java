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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import kacademy.dao.PromotionDao;
import kacademy.dao.ReclamationDao;
import kacademy.entity.Formation;
import kacademy.entity.Promotion;
import kacademy.entity.Reclamation;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class MesReclamationController implements Initializable {

    @FXML
    private ListView list_reclam;
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
    private Button btn_stat;

    private List<Reclamation> data;
    ObservableList observ_list_data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        img_logo.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString() + "/icons/ka_logo.png"));

        txt_search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("serach process " + newValue);

                if (newValue.trim().length() > 0) {
                    ObservableList observ_list_search = FXCollections.observableArrayList();
                    list_reclam.getItems().removeAll(data);
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getSujet().contains(newValue)) {
                            observ_list_search.add(data.get(i));
                        }

                    }
                    list_reclam.setItems(observ_list_search);
                    list_reclam.setCellFactory(new Callback<ListView<Reclamation>, javafx.scene.control.ListCell<Reclamation>>() {
                        @Override
                        public ListCell<Reclamation> call(ListView<Reclamation> listView) {
                            return new ListReclamationItem();
                        }

                    });
                } else {
                    observ_list_data =  FXCollections.observableArrayList();
                    observ_list_data.setAll(data);
                    list_reclam.setItems(observ_list_data);
                    list_reclam.setCellFactory(new Callback<ListView<Reclamation>, javafx.scene.control.ListCell<Reclamation>>() {
                        @Override
                        public ListCell<Reclamation> call(ListView<Reclamation> listView) {
                            return new ListReclamationItem();
                        }

                    });
                }
            }
        });

        if (Session.user.getType().equals("Etudiant")) {
            ReclamationDao r_dao = ReclamationDao.getInstance();
            data = r_dao.getAllByUser(Session.user.getId());
            observ_list_data.setAll(data);
            list_reclam.setItems(observ_list_data);
            list_reclam.setCellFactory(new Callback<ListView<Reclamation>, javafx.scene.control.ListCell<Reclamation>>() {
                @Override
                public ListCell<Reclamation> call(ListView<Reclamation> listView) {
                    return new ListReclamationItem();
                }

            });

            list_reclam.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(list_reclam.getSelectionModel().getSelectedIndex() >= 0)
                    HomeShopController.getInstance().goReclamDetails((Reclamation) list_reclam.getSelectionModel().getSelectedItem());
                }
            });
            btn_add.setVisible(true);
            btn_add.setOnAction(e -> {
                HomeShopController.getInstance().goReclamAdd();
            });
            btn_stat.setVisible(false);

            

            //list_reclam.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } else {
            ReclamationDao r_dao = ReclamationDao.getInstance();
            data = r_dao.displayAll();
            observ_list_data.setAll(data);
            list_reclam.setItems(observ_list_data);
            list_reclam.setCellFactory(new Callback<ListView<Reclamation>, javafx.scene.control.ListCell<Reclamation>>() {
                @Override
                public ListCell<Reclamation> call(ListView<Reclamation> listView) {
                    return new ListReclamationItem();
                }

            });

            list_reclam.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(list_reclam.getSelectionModel().getSelectedIndex() >= 0)
                    HomeShopController.getInstance().goReclamDetails((Reclamation) list_reclam.getSelectionModel().getSelectedItem());
                }
            });

            btn_add.setVisible(false);
            btn_stat.setVisible(true);
            
            btn_stat.setOnAction(e->{
                HomeShopController.getInstance().goAdminStatistiqueReclamation();
            });
        }
        list_reclam.setPrefHeight(126 * data.size() + 2);

        btn_deco.setOnAction(e -> {
            if(Session.user.getType().equals("Etudiant"))
                HomeShopController.getInstance().goMenuEtudant();
            else
                HomeShopController.getInstance().goMenuAdmin();
        });
        
        btn_tri.setOnAction(e -> {
                Collections.sort(observ_list_data);
            });

    }

}
