/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.util.Callback;
import kacademy.dao.MessageReclamDao;
import kacademy.dao.PromotionDao;
import kacademy.dao.ReclamationDao;
import kacademy.entity.MessageReclam;
import kacademy.entity.Promotion;
import kacademy.entity.Reclamation;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReclamationDetailsController implements Initializable {

    public static Reclamation reclamation;

    @FXML
    private Label lb_sujet;
    @FXML
    private Label lb_user;
    @FXML
    private Label lb_date;
    @FXML
    private Label lb_type;
    @FXML
    private Text lb_contenu;
    @FXML
    private TextArea txt_msg;
    @FXML
    private Button btn_send;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_annuler;
    @FXML
    private Button btn_reload;
    @FXML
    private ListView list_msg;
    private List<MessageReclam> data;
    ObservableList observ_list_data = FXCollections.observableArrayList();
    private MessageReclamDao m_dao ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        if (ReclamationDetailsController.reclamation != null) {
            lb_sujet.setText(reclamation.getSujet());
            lb_type.setText(reclamation.getType());
            lb_user.setText(reclamation.getUser().getUsername());
            lb_date.setText(reclamation.getDate());
            lb_contenu.setText(reclamation.getContenu());
            

            m_dao = MessageReclamDao.getInstance();
            data = m_dao.getAllByReclam(reclamation.getId_reclam());
            observ_list_data.setAll(data);
            list_msg.setItems(observ_list_data);
            list_msg.setCellFactory(new Callback<ListView<MessageReclam>, javafx.scene.control.ListCell<MessageReclam>>() {
                @Override
                public ListCell<MessageReclam> call(ListView<MessageReclam> listView) {
                    return new ListMessageReclamItem();
                }

            });
            
            btn_send.setOnAction(e->{
                if(txt_msg.getText().trim().length() > 0){
                    MessageReclam m = new MessageReclam();
                    m.setId_reclam(reclamation.getId_reclam());
                    m.setId_send(Session.user.getId());
                    m.setMessage(txt_msg.getText());
                    m_dao.insert(m);
                    HomeShopController.getInstance().goReclamDetails(reclamation);
                }
            });
        }
        btn_back.setOnAction(e->{
            HomeShopController.getInstance().goMesReclam();
        });
        
        btn_annuler.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Annulation de réclamation");
            alert.setHeaderText("Confirmation d'annulation");
            alert.setContentText("Voulez-vous annuler la réclamation ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
               
                ReclamationDao p_dao = ReclamationDao.getInstance();
                p_dao.delete(reclamation);
                HomeShopController.getInstance().goMesReclam();

            } else {
                // ... user chose CANCEL or closed the dialog
            }
        });
        btn_reload.setOnAction(e->{
            HomeShopController.getInstance().goReclamDetails(reclamation);
        });
        
        if (!Session.user.getType().equals("etud_reclam")) {
            btn_annuler.setVisible(false);
        }
    }

}
