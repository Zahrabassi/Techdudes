/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncour;

import entity.Cour;
import entity.Meet;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21655
 */
public class MeetMainController implements Initializable {

    @FXML
    private TextField tfIdM;
    @FXML
    private TextField tfLienM;
    @FXML
    private TableView<Meet> tvMeet;
    @FXML
    private TableColumn<Meet,Integer> colIdM;
    @FXML
    private TableColumn<Meet,String> colLienM;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupprime;
    @FXML
    private TextField tfRech;
    @FXML
    private TableColumn<Meet,String> colRate;
    @FXML
    private TextField tfRate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMeet();
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
         if(event.getSource() == btnajout){
            AjouterMeet();
        }else if (event.getSource() == btnmodif){
            ModifierMeet();
        }else if(event.getSource() == btnsupprime){
            supprMeet();
    }
    
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
     
      public ObservableList<Meet> getMeetList(){
        ObservableList<Meet> meetList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM meet";
        Statement st;
        ResultSet rs;
        
       try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Cour cour;
            while(rs.next()){
             meetList.add(new  Meet(
             rs.getInt("idM"),
             rs.getString("lienM"),
             rs.getInt("ratee")));       
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return meetList;
    }
    
       public void showMeet(){
        ObservableList<Meet> list = getMeetList();
       
        colIdM.setCellValueFactory(new PropertyValueFactory<Meet, Integer>("idM"));
        colLienM.setCellValueFactory(new PropertyValueFactory<Meet, String>("lienM"));
        colRate.setCellValueFactory(new PropertyValueFactory<Meet, String>("ratee"));
        tvMeet.setItems(list);
        FilteredList<Meet> filteredData = new FilteredList<>(list, b -> true);
		tfRech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(mt -> {
				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (mt.getLienM().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} 
				     else  
				    	 return false;
			});
		});
		
		
		SortedList<Meet> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tvMeet.comparatorProperty());
		
		tvMeet.setItems(sortedData);
    }
  
   
    private void supprMeet() {
      String query = "DELETE FROM meet WHERE idM ='" + tfIdM.getText()+ " '";
        executeQuery(query);
        showMeet();
    }

    private void ModifierMeet() {
      String query = "UPDATE  meet SET lienM = '" + tfLienM.getText()  + "' WHERE idM = " + tfIdM.getText() + "";
        executeQuery(query);    
        showMeet();
    }

    private void AjouterMeet(){
      String query = "INSERT INTO meet VALUES ('" + tfIdM.getText() + "','" + tfLienM.getText() + "','" + tfRate.getText() + "')";
      
        executeQuery(query);
        showMeet();
       
    }
private void executeQuery(String query) {
          Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    @FXML
    private void goToHome(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();

                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("hello.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }
}