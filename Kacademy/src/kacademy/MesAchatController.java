/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import kacademy.dao.AchatDao;
import kacademy.entity.Achat;
import kacademy.entity.Formation;
import kacademy.utils.MailService;

/**
 *
 * @author LENOVO
 */
public class MesAchatController implements Initializable{
    
    @FXML
    private ListView list_achat;
 
    private List<Achat> data;
    ObservableList observ_list_data = FXCollections.observableArrayList();
    
    @FXML
    private Button btn_back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
        AchatDao dao = new AchatDao();
        data = dao.getAllByUser(Session.id_etud);
        observ_list_data.setAll(data);
        list_achat.setItems(observ_list_data);
        list_achat.setCellFactory(new Callback<ListView<Achat>, javafx.scene.control.ListCell<Achat>>() {
            @Override
            public ListCell<Achat> call(ListView<Achat> listView) {
                return new ListAchatItem();
            }

        });
        
        list_achat.setPrefHeight(100 * data.size() +2 );
        
        btn_back.setOnAction(e->{
            HomeShopController.getInstance().goHomeSHop();
        });
        
        
        
    }
    
}
