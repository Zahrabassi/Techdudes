/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import kacademy.dao.ReclamationDao;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class StatistiqueReclamationController implements Initializable {

    @FXML
    private PieChart chart;
    @FXML
    private Button btn_back;
    
    private Map<String , Integer> data ;
    ObservableList<PieChart.Data> list=FXCollections.observableArrayList();
    private ReclamationDao r_dao = ReclamationDao.getInstance() ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        data = r_dao.getStatistique();
        
        for(String key : data.keySet()){
            list.add(new PieChart.Data(key, data.get(key)));
        }
        chart.setAnimated(true);
        chart.setData(list);
        
        btn_back.setOnAction(e->{
            HomeShopController.getInstance().goMesReclam(); 
        });
    }    
    
}
