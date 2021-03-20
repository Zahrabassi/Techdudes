/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import kacademy.entity.Formation;

/**
 *
 * @author LENOVO
 */
public class ListFormationItem extends ListCell<Formation>{

    ListFormationItem() {
        super();
    }
    
    
    @Override
    public void updateItem(Formation data, boolean empty)
    {
        super.updateItem(data,empty);
        if(data != null)
        {
            ListFormationItemController data_controller = new ListFormationItemController();
            data_controller.setInfo(data);
            setGraphic(data_controller.getBox());
        }
    }
    
    

}




