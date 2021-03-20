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
import kacademy.entity.MessageReclam;
import kacademy.entity.Promotion;
import kacademy.entity.Reclamation;

/**
 *
 * @author LENOVO
 */
public class ListMessageReclamItem extends ListCell<MessageReclam>{

    ListMessageReclamItem() {
        super();
    }
    
    
    @Override
    public void updateItem(MessageReclam data, boolean empty)
    {
        super.updateItem(data,empty);
        if(data != null)
        {
            ListMessageReclamItemController data_controller = new ListMessageReclamItemController();
            data_controller.setInfo(data);
            setGraphic(data_controller.getBox());
        }
    }
    
    

}




