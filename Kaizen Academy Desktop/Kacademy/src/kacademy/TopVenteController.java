/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import kacademy.dao.AchatDao;
import kacademy.entity.TopVente;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class TopVenteController implements Initializable {

    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private CategoryAxis xaxis;
    @FXML
    private Button btn_back;

    private List<TopVente> data;
    private AchatDao achat_dao = AchatDao.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data = achat_dao.getTopVente();
        if (data != null) {

            // Create a XYChart.Data object for each month. Add it to the series.
            for (TopVente t : data) {
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>(t.getFormation(), (int)(t.getPrix() * t.getNbr_achats()) ));
                chart.getData().add(series);

            }

        }
        
        btn_back.setOnAction(e->{
            HomeShopController.getInstance().goAdminPromo();
        });
    }

}
