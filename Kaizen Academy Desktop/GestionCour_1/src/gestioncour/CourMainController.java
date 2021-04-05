/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncour;

import entity.Cour;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 21655
 */
public class CourMainController implements Initializable {

    @FXML
    private Label label;
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
    private Button searchCour;
    @FXML
    private TextField tfRech;
    @FXML
    private Button asc;
    @FXML
    private Button desc;

    /**
     * Initializes the controller class.
     */
   
    
     private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
       
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
         if(event.getSource() == btnajout){
            AjouterCour();
        }else if (event.getSource() == btnmodif){
            ModifierCour();
        }else if(event.getSource() == btnsuppr){
            supprCour();
        
        } else if(event.getSource() == searchCour){
            findCourByID();
        } else if(event.getSource() == desc){
            trieDesc();
    }}
    
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
        
        colIdC.setCellValueFactory(new PropertyValueFactory<Cour, Integer>("idC"));
        colNomCour.setCellValueFactory(new PropertyValueFactory<Cour, String>("nomCour"));
        colIdEnseignant.setCellValueFactory(new PropertyValueFactory<Cour, Integer>("nomEnseignant"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Cour, String>("description"));
       
        
        tvCour.setItems(list);
        
        FilteredList<Cour> filteredData = new FilteredList<>(list, b -> true);

		tfRech.textProperty().addListener((observable, oldValue, newValue) -> {
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
		
		sortedData.comparatorProperty().bind(tvCour.comparatorProperty());
		
		tvCour.setItems(sortedData);
		
    }
                   
        private void AjouterCour(){
      String query = "INSERT INTO cour VALUES ('" + tfId.getText() + "','" + tfNomCour.getText() + "',' " + tfIdEnseignant.getText() + "','" + tfDescription.getText() + "')";
      
        executeQuery(query);
        showCour();
    }
 
     
     
     private void ModifierCour(){                    
        String query = "UPDATE  cour SET nomCour  = '" + tfNomCour.getText() + "', nomEnseignant = '" + tfIdEnseignant.getText() + "' , description = '" + tfDescription.getText() + "' WHERE idC = " + tfId.getText() + "";
        executeQuery(query);
        showCour();
    }
    public void supprCour(){
        String query = "DELETE FROM cour WHERE idC ='" + tfId.getText()+ " '";
        executeQuery(query);
        showCour();
           Statement st;
           executeQuery(query);
           System.out.println("Suppression effectuée");
            showCour();
    }

  
     
       private ObservableList<Cour> findCourByID () {
        String query = "SELECT * from cour WHERE idC=" + tfRech.getText()+ "'";
        Cour c = null;
        ObservableList<Cour> courList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                c = new Cour();
                c.setIdC(rs.getInt(1));
                c.setNomCour(rs.getString(2));
                c.setNomEnseignant(rs.getString(2));
                c.setDescription(rs.getString(3));
                System.out.println("recherche effectué");
                courList.add(c);
            }
        } catch (SQLException ex) {
            System.err.println("erreur de recherche");
        }
      return courList;

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

  
    private ObservableList<Cour> trieAsc() {
        ObservableList<Cour> courList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM cour order by ASC";
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

    private void trieDesc() {
        

    }

    
    
    
}
