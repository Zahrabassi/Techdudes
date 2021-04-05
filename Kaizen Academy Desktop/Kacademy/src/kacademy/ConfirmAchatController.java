/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Callback;
import kacademy.dao.AchatDao;
import kacademy.entity.Achat;
import kacademy.entity.Formation;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ConfirmAchatController implements Initializable {

    @FXML
    private Label lb_titre;
    @FXML
    private Text lb_description;
    @FXML
    private Label lb_formateur;
    @FXML
    private Label lb_prix;
    @FXML
    private Button btn_confirm;
    @FXML
    private ImageView img;
    @FXML
    private Button btn_back;

    @FXML
    private ListView list_panier;
    private List<Formation> data;
    ObservableList observ_list_data = FXCollections.observableArrayList();

    //public static Formation data ;
    private AchatDao achat_dao = AchatDao.getInstance();

    private float prix_total = 0;

    private static ConfirmAchatController instance;

    public ConfirmAchatController() {
        instance = this;
    }

    public static ConfirmAchatController getInstance() {
        return instance;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        data = PanierShop.lst_shop;

        for (Formation f : data) {
            prix_total += f.getPrix();
        }

        lb_prix.setText("Total : " + prix_total + " TND");

        observ_list_data.setAll(PanierShop.lst_shop);
        list_panier.setItems(observ_list_data);
        list_panier.setCellFactory(new Callback<ListView<Formation>, javafx.scene.control.ListCell<Formation>>() {
            @Override
            public ListCell<Formation> call(ListView<Formation> listView) {
                return new ListPanierItem();
            }

        });

        // TODO
//        if (data != null) {
//            System.out.println(data.toString());
//            lb_titre.setText(data.getTitre());
//            lb_description.setText(data.getDescription());
//            lb_formateur.setText(data.getNom_form() + " " + data.getPrenom_form());
//            lb_prix.setText(""+data.getPrix()+" TND");
//            img.setImage(new Image("/icons/"+data.getImg()));
//            
//            btn_confirm.setOnAction(e->{
//                Achat o = new Achat(data.getId() , Session.id_etud , data.getPrix());
//                achat_dao.insert(o);
//                HomeShopController.getInstance().goHomeSHop();
//            });
//
//        }
        btn_confirm.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation des achat");
            alert.setHeaderText("Confirmation des achat");
            alert.setContentText("Voulez-vous acheter ces formation ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                List<Achat> lst_achat = new ArrayList<>();
                for (Formation f : data) {
                    lst_achat.add(new Achat(f.getId(), Session.id_etud, f.getPrix()));
                    
                }
                HomeShopController.getInstance().goPay(lst_achat);
             

            } else {
                // ... user chose CANCEL or closed the dialog
            }

        });

        btn_back.setOnAction(e -> {
            HomeShopController.getInstance().goHomeSHop();
        });
    }



} 