/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import kacademy.entity.Achat;
import kacademy.entity.Promotion;
import kacademy.entity.Reclamation;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class HomeShopController implements Initializable {

    
    @FXML
    private AnchorPane container;
    
    private static HomeShopController instance ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        goLogin();
 
    } 

    public HomeShopController() {
        instance = this ;
    }
    
    public static HomeShopController getInstance(){
        return instance ;
    }
    
    public void goProfileAdmin(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/ChairmanDashboard.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void goProfileEns(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/TeacherDashboard.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void goProfileEtu(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/StudentDashboard.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goLogin(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/LoginUI.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goMenuEtudant(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/MenuEtu.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void goMenuAdmin(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/MenuAdmin.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goHomeSHop(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/HomeFormationShop.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goConfirmAchat(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/ConfirmAchat.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goMesAchat(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/MesAchat.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goAdminPromo(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/PromotionList.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goAdminPromoAdd(){
        try {
            // TODO
            PromotionFormController.mode_edit = false ;
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/PromotionForm.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goAdminPromoEdit(Promotion p){
        try {
            // TODO
            PromotionFormController.mode_edit = true ;
            PromotionFormController.promotion = p ;
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/PromotionForm.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goAdminReclam(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/PromotionList.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goReclamAdd(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/ReclamationForm.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goMesReclam(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/MesReclamation.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goReclamDetails(Reclamation r){
        try {
            // TODO
            ReclamationDetailsController.reclamation = r ;
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/ReclamationDetails.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goPay(List<Achat> achats){
        try {
            // TODO
            PaiementController.achats = achats ;
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/Paiement.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void goAdminTopVente(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/TopVente.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void goAdminStatistiqueReclamation(){
        try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/StatistiqueReclamation.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void goMenuEns() {
           try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/MenuEns.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void goEtuCour() {
              try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/EtudiantCour.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void goEtuDev() {
             try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/DevoirMain.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void goEtuMeet() {
      try {
            // TODO
            Pane newLoadedPane  =  FXMLLoader.load(getClass().getResource("views/EtudiantMeet.fxml"));
            container.getChildren().clear();
            container.getChildren().add(newLoadedPane);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
