/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncour;

import entity.Cour;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 21655
 */
public class EtudiantCourController implements Initializable {

    @FXML
    private TableColumn<Cour,String> colNomC;
    @FXML
    private TableColumn<Cour,String> ColNomEn;
    @FXML
    private TableColumn<Cour,String>Coldesc;
    @FXML
    private TableView<Cour> tcCourEtu;
    @FXML
    private TextField Rech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
             courList.add(new  Cour(
             rs.getInt("idC"),
             rs.getString("nomCour"),
             rs.getString("nomEnseignant"),
             rs.getString("description")));
                       
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return courList;
    }
    
       public void showCour(){
        ObservableList<Cour> list = getCourList(); 
        colNomC.setCellValueFactory(new PropertyValueFactory<Cour, String>("nomCour"));
        ColNomEn.setCellValueFactory(new PropertyValueFactory<Cour, String>("nomEnseignant"));
        Coldesc.setCellValueFactory(new PropertyValueFactory<Cour, String>("description"));
       
        
        tcCourEtu.setItems(list);
          FilteredList<Cour> filteredData = new FilteredList<>(list, b -> true);

		Rech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(cr -> {
											
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (cr.getNomCour().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (cr.getNomEnseignant().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				     else  
				    	 return false; // Does not match.
			});
		});
		
		
		SortedList<Cour> sortedData = new SortedList<>(filteredData);
		
		sortedData.comparatorProperty().bind(tcCourEtu.comparatorProperty());
		
		tcCourEtu.setItems(sortedData);
       
       }
}
