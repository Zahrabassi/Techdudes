/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestioncour;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entity.Cour;
import entity.Meet;
import entity.RateMeet;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private RadioButton one;
    @FXML
    private RadioButton two;
    @FXML
    private RadioButton three;
    @FXML
    private RadioButton four;
    @FXML
    private RadioButton five;
    @FXML
    private TableColumn<Meet,String> colLien;
    @FXML
    private TextField tfMeet;
    @FXML
    private Button rate;

   
   

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
       }
double value;

    @FXML
    private void handleButtonAction(ActionEvent event) {
      if(event.getSource() == rate){
            ajoutRate();}  
      
    }
    private void ajoutRate()
    { 
        if(one.isSelected())
        {
            value = 1;
        }
        else if (two.isSelected())
        {
            value = 2;
        }
        else if (three.isSelected())
        {
            value = 3;
        }
        else if (four.isSelected())
        {
            value = 4;
        }
        else if (five.isSelected())
        {
            value =5 ;
        }
        String idM = rate.getText();
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

  

}
