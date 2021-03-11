/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncour;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entity.Cour;
import entity.Devoir;
import entity.Meet;
import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;


/**
 * FXML Controller class
 *
 * @author 21655
 */
public class DevoirMainController implements Initializable {

   
    @FXML
    private TextField tfNomD;
    @FXML
    private TextField tfIdD;
    @FXML
    private TableView<Devoir> tvDev;
    @FXML
    private TableColumn<Devoir,Integer> colIdD;
    @FXML
    private TableColumn<Devoir,String> colNomD;
    @FXML
    private TableColumn<Devoir,String> colDD;

    @FXML
    private ComboBox<String> tfDes;
    
    ObservableList<String> list = FXCollections.observableArrayList("A FAIRE", "ENCOURS", "FAIT " );
    
   
    @FXML
    private Button btnajoutdev;
    @FXML
    private Button btnsuppdev;
    @FXML
    private Button btnmodifdev;
    @FXML
    private TextField tfRech;
    @FXML
    private Button searchDev;

   

    /**
     * Initializes the controller class.
     */
    
    private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
        
    }
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        // TODO
      
      tfDes.setItems(list);
      showDevoir();
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


    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if(event.getSource() == btnajoutdev){
            btnajoutdev();
             }else if (event.getSource() == btnsuppdev){
           supprimerDevoir();
           } else if(event.getSource() == btnmodifdev){
            modifDev();
          
             
    }}
    
    
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
         
        colIdD.setCellValueFactory(new PropertyValueFactory<Devoir, Integer>("idD"));
        colNomD.setCellValueFactory(new PropertyValueFactory<Devoir, String>("nomD"));
        colDD.setCellValueFactory(new PropertyValueFactory<Devoir, String>("description"));
   
       
        
        tvDev.setItems(list);
          // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Devoir> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		tfRech.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(dv -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (dv.getNomD().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (dv.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Devoir> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvDev.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvDev.setItems(sortedData);
		
    }

    private void btnajoutdev() {
        
		
     
        String query = "INSERT INTO devoir VALUES ('" + tfIdD.getText()  + "','" + tfNomD.getText()+ "',' " + tfDes.getValue()+ "')";
       System.out.println("Devoir ajoutée !");
        executeQuery(query);
        showDevoir();
        
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

    private void supprimerDevoir() {
         String query = "DELETE FROM devoir WHERE idD ='" + tfIdD.getText()+ " '";
        executeQuery(query);
        showDevoir();
    }

     
    

    private void modifDev() {
        String query = "UPDATE devoir SET nomD  = '" + tfNomD.getText() + "', description = '" + tfDes.getValue()+  "' WHERE idD = " + tfIdD.getText() + "";
        executeQuery(query);
        showDevoir();
    }
    
   

    private void trieAsc() {
          String query = "select * from devoir  ORDER  BY nomD ASC"  ;
     executeQuery(query);
        showDevoir();
       
    }

    private void trieDesc() {
        String query = "select  idD from devoir  ORDER  BY idD DESC"  ;
      executeQuery(query);
        showDevoir();
    }

    /**
     *
     * @param nomD
     * @return
     */
private Devoir recherche (int idD) {
        String query = "SELECT * from devoir WHERE idD=" + tfRech.getText();
       Devoir d = null;
       ObservableList<Devoir> devoirList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
           while (rs.next()) {
           d = new Devoir();
           d.setIdD(rs.getInt(1));
           d.setNomD(rs.getString(2));
           d.setDescription(rs.getString(3));
             
           }
        } catch (SQLException ex) {
            System.err.println("erreur de recherche");
        }
     return d;

    }



    
   }
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
