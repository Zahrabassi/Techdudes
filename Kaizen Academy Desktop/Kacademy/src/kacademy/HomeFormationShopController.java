/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import kacademy.dao.FormationDao;
import kacademy.entity.Formation;

/**
 *
 * @author LENOVO
 */
public class HomeFormationShopController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private ListView list_formation;
    
    @FXML
    private ImageView img_logo;
    
    @FXML
    private Button btn_panier;
    
    @FXML
    private Button btn_mes_achat;
    
    @FXML
    private Button btn_deco;
    
    @FXML
    private Label txt_panier;
    
    private List<Formation> data ;
    ObservableList observ_list_data = FXCollections.observableArrayList();
    
    private static HomeFormationShopController instance ;

    public HomeFormationShopController() {
        instance = this ;
    }
    
    public static HomeFormationShopController getInstance(){
        return instance ;
    }
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println("cuur path : "+Paths.get("").toAbsolutePath().toUri().toString()+"icons/ka_logo.png");
        img_logo.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString()+"/icons/ka_logo.png"));
        setListView();
        btn_panier.setOnAction(e ->{
            HomeShopController.getInstance().goConfirmAchat();
        });
        
        btn_mes_achat.setOnAction(e ->{
            HomeShopController.getInstance().goMesAchat();
        });
         btn_deco.setOnAction(e -> {
            if(Session.user.getType().equals("Etudiant"))
                HomeShopController.getInstance().goMenuEtudant();
            else if(Session.user.getType().equals("Admin"))
                HomeShopController.getInstance().goMenuAdmin();
            else if(Session.user.getType().equals("Enseingant"))
                HomeShopController.getInstance().goMenuEns();
            
        }
         
         );
    }    
    
    public void setListView (){
        
        txt_panier.setText("0");
        data = new ArrayList<>();
        FormationDao pdao=FormationDao.getInstance();
        data= pdao.displayAll();
        
        
        
        /*
        data.add(new Formation("titre 01", "description 01", "01/03/2021", "16/03/2021", "nom_form 01", "prenom_form 01", "type", 0))    ;
        data.add(new Formation("titre 02", "description 01", "01/03/2021", "16/03/2021", "nom_form 01", "prenom_form 01", "type", 0))    ;
        data.add(new Formation("titre 03", "description 01", "01/03/2021", "16/03/2021", "nom_form 01", "prenom_form 01", "type", 0))    ;
        data.add(new Formation("titre 04", "description 01", "01/03/2021", "16/03/2021", "nom_form 01", "prenom_form 01", "type", 0))    ;
*/
        
        observ_list_data.setAll(data);
        list_formation.setItems(observ_list_data);
        list_formation.setCellFactory(new Callback<ListView<Formation>, javafx.scene.control.ListCell<Formation>>()
        {
            @Override
            public ListCell<Formation> call(ListView<Formation> listView)
            {
                return new ListFormationItem();
            }
        });
     }
    
    public  void UpdatePanierText(){
        txt_panier.setText(""+PanierShop.lst_shop.size());
    }
    
    
}
