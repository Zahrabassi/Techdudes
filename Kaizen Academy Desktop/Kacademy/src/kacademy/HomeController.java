/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kacademy.dao.FormationCrud;
import kacademy.entity.Formations;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeController implements Initializable {

    @FXML
    private TableColumn<Formations, String> intitule;
    @FXML
    private TextArea descriptions;
    @FXML
    private TableColumn<Formations, Date> date_debut;
    @FXML
    private TableColumn<Formations, Integer> id_eval;
    @FXML
    private TableColumn<Formations, String> id_formateur;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TableColumn<Formations, String> type;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private TableView<Formations> table;
    @FXML
    private TextField nom_f;
    @FXML
    private DatePicker date_deb;
    @FXML
    private TextField eval;
    @FXML
    private TextField formateur;
    @FXML
    private ComboBox<String> type_f;
    ObservableList<String> typelist = FXCollections.observableArrayList("développement","langues","développement perso","science");
    @FXML
    private TableColumn<Formations, String> description_tab;
    @FXML
    private TableColumn<Formations, Date> date_fin_tab;
    @FXML
    private TextField id_format;
    @FXML
    private TableColumn<Formations, String> id_f_tab;
    @FXML
    private TextField prix;
    @FXML
    private TableColumn<Formations, String> prix_tab;
    @FXML
    private Button retour;
    @FXML
    private Button envoyerMail;
    
    
     private void close(MouseEvent event) {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
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
    
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         type_f.setItems(typelist);
        try {
             afficher_list();
        } catch (Exception e) {
             System.out.println("Error: " + e.getMessage());
        }
     
    }
    public void afficher_list(){
         FormationCrud fdao = FormationCrud.getInstance();
        ObservableList<Formations> list = fdao.afficherLesFormations();
         id_f_tab.setCellValueFactory(new PropertyValueFactory<Formations, String>("id_formations"));
         intitule.setCellValueFactory(new PropertyValueFactory<Formations, String>("intitule"));
         description_tab.setCellValueFactory(new PropertyValueFactory<Formations, String>("description"));
         date_debut.setCellValueFactory(new PropertyValueFactory<Formations, Date>("date_debut"));
         id_eval.setCellValueFactory(new PropertyValueFactory<Formations, Integer>("id_eval"));
         id_formateur.setCellValueFactory(new PropertyValueFactory<Formations, String>("id_formateur"));
         date_fin_tab.setCellValueFactory(new PropertyValueFactory<Formations, Date>("date_fin"));
         type.setCellValueFactory(new PropertyValueFactory<Formations, String>("type"));
         prix_tab.setCellValueFactory(new PropertyValueFactory<Formations, String>("prix"));
        table.setItems(list);
    }
            public void inserer() {
                try {
                    java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_deb.getValue());
                    java.sql.Date gettedDatePickerDatef = java.sql.Date.valueOf(date_fin.getValue());
                    float p1= Float.parseFloat(prix.getText());
                    Formations f1;
                    f1= new Formations( nom_f.getText(), descriptions.getText(), gettedDatePickerDate, gettedDatePickerDate, type_f.getValue(), Integer.parseInt(eval.getText()), Integer.parseInt(formateur.getText()),p1);
                    FormationCrud ffdao = FormationCrud.getInstance();
                    ffdao.insert(f1);
                    afficher_list();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Formation insérée avec succés!");
                    alert.show();
                }
                catch(Exception e){
                    e.printStackTrace();   
                }
            }
            public void modifier(){
                 try {
                    java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_deb.getValue());
                    java.sql.Date gettedDatePickerDatef = java.sql.Date.valueOf(date_fin.getValue());
                    Formations f1;
                    float p2= Float.parseFloat(prix.getText());
                    f1 = new Formations(Integer.parseInt(id_format.getText()),nom_f.getText(), descriptions.getText(),gettedDatePickerDate , gettedDatePickerDatef , type_f.getValue(), Integer.parseInt(eval.getText()), Integer.parseInt(formateur.getText()),p2);
                    FormationCrud ffdao = FormationCrud.getInstance();
                    ffdao.update(f1);
                    afficher_list();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Formation modifiée avec succés!");
                    alert.show();
                } catch (Exception e) {
                     e.printStackTrace(); 
                }
            }
            public void supprimer(){
                  try {
                    Formations f;
                    f = new Formations(Integer.parseInt(id_format.getText()));
                    FormationCrud ffdao = FormationCrud.getInstance();
                    ffdao.delete(f);
                    afficher_list();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Formation supprimée avec succés!");
                    alert.show();
                } catch (Exception e) {
                    e.printStackTrace(); 
                }
            }

    @FXML
    private void onClick(MouseEvent event) {
        
        try {
        Formations formation= (Formations) table.getSelectionModel().getSelectedItem();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        
        Date vard=formation.getDate_debut();
        Date vardf=formation.getDate_fin();
           LocalDate localDate = Instant.ofEpochMilli(vard.getTime())
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
           LocalDate localDatef = Instant.ofEpochMilli(vardf.getTime())
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
        id_format.setText(""+formation.getId_formations());
        nom_f.setText(formation.getIntitule());
        descriptions.setText(formation.getDescription());
        date_deb.setValue(localDate);
        eval.setText(""+formation.getIdEval());
        formateur.setText(""+formation.getId_formateur());
        date_fin.setValue(localDatef);
        prix.setText(""+formation.getPrix());
        
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
    }

    @FXML
    private void selectionnerCertifiante(MouseEvent event) {
    }

    @FXML
    private void fontionRetour(ActionEvent event) {
                     retour.setOnAction(new EventHandler<ActionEvent>() {
                         @Override
                         public void handle(ActionEvent event) {
                             try {
                                 Parent page1 = FXMLLoader.load(HomeController.this.getClass().getResource("/kacademy/views/MenuAdmin.fxml"));
                                 Scene scene = new Scene(page1);
                                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                 stage.setScene(scene);
                                 stage.show();
                             }catch (IOException ex) {
                                 Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                             }            }
                     });
    }

    @FXML
    private void envoyerMailOnAction(ActionEvent event) {
    
   envoyerMail.setOnAction(new EventHandler<ActionEvent>() {
                         @Override
                         public void handle(ActionEvent event) {
                             try {
                                 Parent page1 = FXMLLoader.load(HomeController.this.getClass().getResource("/kacademy/views/email.fxml"));
                                 Scene scene = new Scene(page1);
                                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                 stage.setScene(scene);
                                 stage.show();
                             }catch (IOException ex) {
                                 Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                             }            }
                     });

  }}




                 
       
    

