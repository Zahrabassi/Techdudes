/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;


import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kacademy.dao.EvaluationServices;
import kacademy.entity.TestEvaluation;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EvaluationController implements Initializable {

    @FXML
    private TextField id_eval;
    @FXML
    private TextField nom_eval;
    @FXML
    private TextField lien_eval;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private TableView<TestEvaluation> table;
    @FXML
    private TableColumn<TestEvaluation, Integer> id_tab;
    @FXML
    private TableColumn<TestEvaluation, String> nom_tab;
    @FXML
    private TableColumn<TestEvaluation, String> lien_tab;
    @FXML
    private Button retour;
    @FXML
    private TextField chercher;
    @FXML
    private Label label_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
             afficher();
        } catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
        }
    }  
    public void afficher(){
        try {
         EvaluationServices fdao = EvaluationServices.getInstance();
        ObservableList<TestEvaluation> list = fdao.afficherEvaluations();
        id_tab.setCellValueFactory(new PropertyValueFactory<TestEvaluation, Integer>("id_evaluation"));
        nom_tab.setCellValueFactory(new PropertyValueFactory<TestEvaluation, String>("nom_evaluation"));
        lien_tab.setCellValueFactory(new PropertyValueFactory<TestEvaluation, String>("lien_evaluation")); 
        table.setItems(list);
        FilteredList<TestEvaluation> filteredData = new FilteredList<>(list, b -> true);
		chercher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(usr -> {
				
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (usr.getNom_evaluation().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; }
				     else  
				    	 return false; 
			});
                        SortedList<TestEvaluation> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);
		}); 
		
    }
        
    catch (Exception e) {
             e.printStackTrace();
        }
    }
    public void inserer(){
            if (!StringUtils.isNullOrEmpty(nom_eval.getText())&& !StringUtils.isNullOrEmpty(lien_eval.getText()) &&  (!id_eval.getText().matches("[A-Za-z]") || !id_eval.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+"))){
                try { 
                     label_id.setText(" ");
                TestEvaluation t=new TestEvaluation( nom_eval.getText(), lien_eval.getText());
                EvaluationServices ts= EvaluationServices.getInstance();
                ts.insert(t);
                afficher();
               }
               catch (Exception e) {
            e.printStackTrace();
                }
            }
else {
                label_id.setText("un champ vide !!!");
            }}
    public void modifier(){
        try {
        label_id.setText("");
        TestEvaluation t1=new TestEvaluation(Integer.parseInt(id_eval.getText()), nom_eval.getText(), lien_eval.getText());
        EvaluationServices ts= EvaluationServices.getInstance();
        ts.update(t1);
        afficher();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
    }

    public void supprimer(){
        try {
        label_id.setText("");
        TestEvaluation t=new TestEvaluation(Integer.parseInt(id_eval.getText()));
        EvaluationServices ts= EvaluationServices.getInstance();
        ts.delete(t);
        afficher();                
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }
 
    }
   

    @FXML
        private void onaction(ActionEvent event) {
        if (event.getSource()==btn_ajouter){
            inserer();
    }else if (event.getSource()==btn_modifier){
            modifier();
    }else if(event.getSource()==btn_supprimer){
        supprimer();
    }
        }

    @FXML
    private void onclick(MouseEvent event) {
        try {
         TestEvaluation evaluation=table.getSelectionModel().getSelectedItem();
         id_eval.setText(""+evaluation.getId_evaluation());
         nom_eval.setText(evaluation.getNom_evaluation());
         lien_eval.setText(evaluation.getLien_evaluation());
        } catch (Exception e) {
             e.printStackTrace();
        }
        
    }

    @FXML
    private void actionRetour(ActionEvent event) {
        retour.setOnAction((ActionEvent event1) -> {
            try {
                Parent page1 = FXMLLoader.load(EvaluationController.this.getClass().getResource("/kacademy/views/MenuAdmin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }catch (IOException ex) {
                Logger.getLogger(EvaluationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
}
