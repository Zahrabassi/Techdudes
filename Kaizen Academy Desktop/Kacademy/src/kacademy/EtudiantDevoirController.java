/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kacademy.entity.Devoir;

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
    private PieChart pane;
    @FXML
    private Button btnfile;
    @FXML
    private ListView liste;

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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kadb", "root","");
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
    private void goToHome(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("views/MenuEns.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }



    @FXML
private void ButtonAction(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        
        if (selectedFile != null) {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(selectedFile);
            liste.getItems().add(selectedFile.getName());
    }else {
            System.out.println("fichier non valider!");
        }
    }
    
   

    
}
