/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.kaizen.entity.User;
import tn.kaizen.service.Service;
import tn.kaizen.service.UserService;
import static tn.kaizen.utils.DBConnection.getConnection;

/**
 * FXML Controller class
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class ChairmanDashboardController implements Initializable {

    @FXML
    private Label chairmanNameLabel;
    @FXML
    private Label chairmanIdLabel;
    @FXML
    private TableView<User> user;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField filterfield;
    @FXML
    private AnchorPane AdminPane;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private AnchorPane HomePane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AdminPane.setVisible(false);
        HomePane.setVisible(false);
        
        try {
            fetchChairmanInformationFromDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(ChairmanDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
        private void fetchChairmanInformationFromDatabase() throws SQLException {
        String chairmanId = LoginUIController.getUsername();
        Service chairmanOp = new UserService();
        User chairman = chairmanOp.getUser(chairmanId);
        System.out.println(chairman);
        chairmanIdLabel.setText(chairman.getUsername());
        chairmanNameLabel.setText(chairman.getName());
        //To change body of generated methods, choose Tools | Templates.
    }
        public ObservableList<User> getUserList(){
        ObservableList<User> userlist = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM LOGINS";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            User usr;
            while(rs.next()){
                usr = new User(rs.getString("NAME"), rs.getString("EMAIL"),rs.getString("USERNAME"));
                userlist.add(usr);
            }
                
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return userlist;
    }
    public void showUsers(){
        ObservableList<User> list = getUserList();
        
        name.setCellValueFactory(new PropertyValueFactory<User , String>("name"));
        email.setCellValueFactory(new PropertyValueFactory<User , String>("email"));
        username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        user.setItems(list);
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(usr -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (usr.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (usr.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (usr.getEmail().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(user.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		user.setItems(sortedData);
    }
    @FXML
    private void handleDashboardSignout(ActionEvent actionEvent) throws IOException {
        /*
         * Signing out will take user to login page
         * */
        Parent LoginUIParent = FXMLLoader.load(getClass().getResource("/tn/kaizen/view/LoginUI.fxml"));
        Scene LoginUIScene = new Scene(LoginUIParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(LoginUIScene);
        window.show();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        User wtf=user.getSelectionModel().getSelectedItem();
        Service userops = new UserService();
        if(event.getSource() == btnDelete){
            userops.deleteButton(wtf);
            showUsers();
        }
    }

    @FXML
    private void navigatetoHome(ActionEvent event) {
        AdminPane.setVisible(true);
        HomePane.setVisible(true);
        showUsers();
    }

    @FXML
    private void handlehome(ActionEvent event) {
        HomePane.setVisible(false);
         AdminPane.setVisible(false);
    }
    
}
