/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courcour;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author 21655
 */
public class mainController implements Initializable {
    
   
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNomCour;
    @FXML
    private TextField tfIdEnseignant;
    @FXML
    private TextField tfDescription;
    @FXML
    private TableView<Cour> tvCour;
    @FXML
    private TableColumn<Cour, Integer> colIdC;
    @FXML
    private TableColumn<Cour, String> colNomCour;
    @FXML
    private TableColumn<Cour, Integer> colIdEnseignant;
    @FXML
    private TableColumn<Cour, String>colDescription;
    
    
    
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsuppr;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnajout){
            AjouterCour();
        }else if (event.getSource() == btnmodif){
            ModifierCour();
        }else if(event.getSource() == btnsuppr){
            supprCour();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCour();
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
    
   public ObservableList<Cour> getCourList(){
        ObservableList<Cour> courList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM cour";
        Statement st;
        ResultSet rs;
       try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Cour cour;
            while(rs.next()){
                cour = new Cour(rs.getInt("idC"), rs.getString("nomCour"), rs.getInt("idEnseignant"), rs.getString("description"));
               courList.add(cour);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return courList;
    }
    
    public void showCour(){
        ObservableList<Cour> list = getCourList();
        
        colIdC.setCellValueFactory(new PropertyValueFactory<Cour, Integer>("idC"));
        colNomCour.setCellValueFactory(new PropertyValueFactory<Cour, String>("nomCour"));
        colIdEnseignant.setCellValueFactory(new PropertyValueFactory<Cour, Integer>("idEnseignant"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Cour, String>("description"));
       
        
        tvCour.setItems(list);
    }
                                                    
    
     private void AjouterCour(){
      String query = "INSERT INTO cour(idC ,nomCour , idEnseignant , description ) VALUES (" + tfId.getText() + ",'" + tfNomCour.getText() + "','" + tfIdEnseignant.getText() + "'," + tfDescription.getText() + ")";
        executeQuery(query);
        showCour();
    }

     
     
     private void ModifierCour(){
        String query = "UPDATE  cour SET nomCour  = '" + tfNomCour.getText() + "', idEnseignant = '" + tfIdEnseignant.getText() + "', description = " +
                tfDescription.getText() +  " WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showCour();
    }
    private void supprCour(){
        String query = "DELETE FROM cour WHERE id =" + tfId.getText() + "";
        executeQuery(query);
        showCour();
    }

     
     
     
     
    private void executeQuery(String query) {
          Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
    
}
