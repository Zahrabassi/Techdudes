/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import kacademy.dao.FormationDao;
import kacademy.dao.PromotionDao;
import kacademy.entity.Achat;
import kacademy.entity.Formation;
import kacademy.entity.Promotion;
import kacademy.utils.MailService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PromotionFormController implements Initializable {

    @FXML
    private ComboBox cbb_formation;
    @FXML
    private Label lb_titre;
    @FXML
    private Label lb_date;
    @FXML
    private Label lb_prix;
    @FXML
    private TextField txt_promo;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_back;

    private List<Formation> data_f;
    private List<String> data_f_titres;
    ObservableList observ_list_data_f = FXCollections.observableArrayList();

    public static boolean mode_edit = false;
    public static Promotion promotion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        FormationDao dao_f = new FormationDao();
        data_f = dao_f.displayAll();
        data_f_titres = new ArrayList<>();

        for (Formation f : data_f) {
            data_f_titres.add(f.getTitre() + "    ( " + f.getNom_form() + " " + f.getPrenom_form() + ") ");
        }
        cbb_formation.getItems().addAll(data_f_titres);

        txt_promo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                lb_error.setText("");
                if (!newValue.matches("\\d*")) {
                    lb_error.setText("Valeur de la promotion invalide !");
                } else if (Integer.parseInt(newValue) > 100 || Integer.parseInt(newValue) < 0) {
                    lb_error.setText("La valeur de la promotion doit etre entre 0 et 100 % !");
                }
            }
        });
        
         cbb_formation.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    lb_titre.setText(data_f.get(cbb_formation.getSelectionModel().getSelectedIndex()).getTitre());
                    lb_date.setText(data_f.get(cbb_formation.getSelectionModel().getSelectedIndex()).getDate_deb() + " / " + data_f.get(cbb_formation.getSelectionModel().getSelectedIndex()).getDate_fin());
                    lb_prix.setText("" + data_f.get(cbb_formation.getSelectionModel().getSelectedIndex()).getPrix());
                }

            });

        if (!PromotionFormController.mode_edit) {

           

            btn_ok.setOnAction(e -> {
                if (cbb_formation.getSelectionModel().getSelectedIndex() < 0) {
                    lb_error.setText("Vous devez choisir une formation !");
                } else if (txt_promo.getText().trim().length() == 0) {
                    lb_error.setText("Vous devez saisir la valeur de la promotion !");
                } else {

                    PromotionDao p_dao = PromotionDao.getInstance();
                    Promotion p = new Promotion(data_f.get(cbb_formation.getSelectionModel().getSelectedIndex()).getId(), Float.parseFloat(txt_promo.getText()));
                    p_dao.insert(p);
                    p.setFormation(data_f.get(cbb_formation.getSelectionModel().getSelectedIndex()));
                    try {
                        MailService.sendMail(p_dao.getMailList(), p);
                    } catch (Exception ex) {
                        Logger.getLogger(PromotionFormController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    HomeShopController.getInstance().goAdminPromo();

                }
            });
        } else {
            if (promotion != null) {
                for ( int i= 0 ; i< data_f.size() ; i++) {
                    if(data_f.get(i).getId() == promotion.getFormation().getId()){
                        cbb_formation.getSelectionModel().select(i);
                    }
                }
                cbb_formation.setDisable(true);          
                txt_promo.setText((int)promotion.getPromo()+"");
                btn_ok.setText("Enregistrer");
                
                btn_ok.setOnAction(e -> {
                if (cbb_formation.getSelectionModel().getSelectedIndex() < 0) {
                    lb_error.setText("Vous devez choisir une formation !");
                } else if (txt_promo.getText().trim().length() == 0) {
                    lb_error.setText("Vous devez saisir la valeur de la promotion !");
                } else {

                    PromotionDao p_dao = PromotionDao.getInstance();
                    promotion.setPromo(Float.parseFloat(txt_promo.getText()));
                    p_dao.update(promotion);
                    HomeShopController.getInstance().goAdminPromo();

                }
            });
                

            } else {
                HomeShopController.getInstance().goAdminPromo();
            }
        }

        btn_back.setOnAction(e -> {
            HomeShopController.getInstance().goAdminPromo();
        });

    }

}
