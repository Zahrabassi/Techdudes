/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

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
import kacademy.entity.Meet;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author 21655
 */
public class EtudiantMeetController implements Initializable {

 
    @FXML
    private TableView<Meet> tvMeetEt;
    @FXML
    private TableColumn<Meet,Integer> colMeet;
    @FXML
    private TableColumn<Meet,Integer> colRate;
    @FXML
    private TableColumn<Meet,String> colLien;
    @FXML
    private TextField tfMeet;
    @FXML
    private Button rate;
    @FXML
    private Rating rateMeet;

   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showMeet();


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
     
      public ObservableList<Meet> getMeetList(){
        ObservableList<Meet> meetList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM meet";
    
        Statement st;
        ResultSet rs;
        
       try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Meet meet;
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
    
        colMeet.setCellValueFactory(new PropertyValueFactory<Meet, Integer>("idM"));
        colLien.setCellValueFactory(new PropertyValueFactory<Meet, String>("lienM"));
        colRate.setCellValueFactory(new PropertyValueFactory<Meet,Integer>("ratee"));
        tvMeetEt.setItems(list);
        FilteredList<Meet> filteredData = new FilteredList<>(list, b -> true);
		tfMeet.textProperty().addListener((observable, oldValue, newValue) -> {
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
		sortedData.comparatorProperty().bind(tvMeetEt.comparatorProperty());
		
		tvMeetEt.setItems(sortedData);
       }
       


    @FXML
    private void handleButtonAction(ActionEvent event) {
      if(event.getSource() == rate){
            ajoutRate();}  
      
    }
    private void ajoutRate()
    { 
       
        String idM = tfMeet.getText();
             double value = rateMeet.getRating();;
             System.out.println("meet: " + tfMeet.getText() + "  rating: "+ rateMeet.getRating());
             String query = "UPDATE  meet SET ratee = '" + value  + "' WHERE idM = " + tfMeet.getText() + "";
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

  Scene scene = new Scene(FXMLLoader.load(getClass().getResource("views/MenuEtu.fxml")));
                    stage.setScene(scene);
                    stage.show();
    }

  
}
