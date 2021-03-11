/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncour;

import entity.Devoir;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21655
 */
public class EtudiantDevoirController implements Initializable {

    @FXML
    private TableView<Devoir> tvCourDev;
    @FXML
    private TableColumn<Devoir,String> ColNomD;
    @FXML
    private TableColumn<Devoir,String> ColdescD;
    @FXML
    private PieChart pane;
    @FXML
    private Button chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showDevoir();
    }    
     
     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kaizenbd", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
  
    }
     
        public ObservableList<Devoir> getDevoirList(){
        ObservableList<Devoir> devoirList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM devoir";
        Statement st;
        ResultSet rs;
        
       try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Devoir devoir;
            while(rs.next()){
             devoirList.add(new  Devoir(
             rs.getInt("idD"),
             rs.getString("nomD"),
             rs.getString("description")));
                       
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return devoirList;
    }

   
     public void showDevoir(){
         
       
        ObservableList<Devoir> list = getDevoirList();
         
        ColNomD.setCellValueFactory(new PropertyValueFactory<Devoir, String>("nomD"));
        ColdescD.setCellValueFactory(new PropertyValueFactory<Devoir, String>("description"));
   
       
        
        tvCourDev.setItems(list);
         
		
		
		
    }



    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
         ObservableList<Data> list = FXCollections.observableArrayList(
                 
            new PieChart.Data("A FAIRE", 10),
             new PieChart.Data("ENCOURS",35),
              new PieChart.Data("FAIT", 14)
     
              ); 
    
      
            pane.setData(list);
       
    
    }
    
    
}
