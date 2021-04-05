/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.net.URL;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kacademy.dao.AchatDao;
import kacademy.dao.PaiementDao;
import kacademy.entity.Achat;
import kacademy.entity.Formation;
import kacademy.entity.Paiement;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class PaiementController implements Initializable {

    @FXML
    private ImageView img_visa;
    @FXML
    private ImageView img_master;
    @FXML
    private Label lb_montant;
    @FXML
    private TextField txt_carte;
    @FXML
    private DatePicker txt_date;
    @FXML
    private PasswordField txt_code;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_cancel;

    public static List<Achat> achats;
    private float montant = (float) 0.000;
    private AchatDao achat_dao = AchatDao.getInstance();
    private PaiementDao paiement_dao = PaiementDao.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        img_master.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString() + "/icons/master.jpg"));
        img_visa.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString() + "/icons/visa.jpg"));

        btn_ok.setOnAction((ActionEvent e) -> {
            if (txt_carte.getText().length() != 12) {
                lb_error.setText("Numéro de la carte bancaire invalid (12 chiffres) !");
            } else if (txt_date.getValue() == null) {
                lb_error.setText("vous devez choisir la date d'expiration de la carte");
            } else if (txt_date.getValue().getYear() < 2020) {
                lb_error.setText("carte déja expiré");
            } else if (txt_code.getText().length() != 4) {
                lb_error.setText("code de vérification invalide (4 chiffres) !");
            } else {
                String date = txt_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Paiement p = paiement_dao.getPayCarte(txt_carte.getText(), date, txt_code.getText());

                if (p != null) {
                    if (p.getSolde() < montant) {
                        lb_error.setText("votre solde est insuffisant !");
                    } else {
                        if (paiement_dao.doPay(p.getId(), montant)) {
                            achat_dao.insert(achats);
                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Information Dialog");
                            alert2.setHeaderText(null);
                            alert2.setContentText("Achat effectué avec sucés ");
                            
                            Optional<ButtonType> result = alert2.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                HomeShopController.getInstance().goMesAchat();

                            } else {
                                 HomeShopController.getInstance().goMesAchat();
                            }
                        }
                    }
                } else {
                    lb_error.setText("Aucune carte trouvée avec ces coordonnées !");
                }

            }
        });

        txt_carte.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*") || txt_carte.getText().length() != 12) {
                    lb_error.setText("Numéro de la carte bancaire invalid (12 chiffres) !");
                } else {
                    lb_error.setText("");
                }
            }
        });

        txt_code.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*") || txt_code.getText().length() != 4) {
                    lb_error.setText("code de vérification invalide (4 chiffres) !");
                } else {
                    lb_error.setText("");
                }
            }
        });

        if (achats != null) {
            for (Achat a : achats) {
                montant += a.getPrix();
            }
            lb_montant.setText(montant + "  TND");
        }

        btn_cancel.setOnAction(e -> {
            HomeShopController.getInstance().goHomeSHop();
        });

    }

}
