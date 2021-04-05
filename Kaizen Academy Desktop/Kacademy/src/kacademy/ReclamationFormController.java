/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import kacademy.dao.ReclamationDao;
import kacademy.entity.Reclamation;
import kacademy.utils.MailReclamation;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReclamationFormController implements Initializable {

    @FXML
    private Label lb_titre;
    @FXML
    private TextField txt_sujet;
    @FXML
    private ComboBox cbb_type;
    @FXML
    private TextArea txt_contenu;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbb_type.getItems().add("vente");
        cbb_type.getItems().add("probléme technique");
        cbb_type.getItems().add("autre");

        btn_ok.setOnAction(e -> {
            lb_error.setText("");
            if (txt_sujet.getText().trim().length() == 0) {
                lb_error.setText("vous devez saisir le sujet de la réclamation !");
            } else if (cbb_type.getSelectionModel().getSelectedIndex() < 0) {
                lb_error.setText("vous devez choisir un type !");
            } else if (txt_contenu.getText().trim().length() == 0) {
                lb_error.setText("vous devez saisir le contenu de la réclamation !");
            } else {
                ReclamationDao r_pdo = ReclamationDao.getInstance();
                Reclamation r = new Reclamation();
                r.setId_user(Session.user.getId());
                r.setSujet(txt_sujet.getText());
                r.setType((String) cbb_type.getValue());
                r.setContenu(txt_contenu.getText());

                r_pdo.insert(r);
                try {
                    MailReclamation.sendMail(Session.user.getEmail(), r);
                } catch (Exception ex) {
                    Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
                HomeShopController.getInstance().goMesReclam();
            }

        });
        btn_back.setOnAction(e -> {
            HomeShopController.getInstance().goMesReclam();
        });
    }

}
