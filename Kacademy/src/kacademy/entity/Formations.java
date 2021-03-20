package kacademy.entity;

import java.util.Date;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author user
 */
public class Formations {
    private int id_formations;
    private String intitule ;
    private String description;
    private Date date_debut;
    private Date date_fin;
    private String type;
    private int idEval;
    private int id_formateur;
    private float prix;

    public Formations() {
    }

    public Formations(String intitule) {
        this.intitule = intitule;
    }

    public Formations(int id_formations) {
        this.id_formations = id_formations;
    }

    public Formations(int id_formations, String intitule, String description, Date date_debut, Date date_fin, String type, int idEval, int id_formateur, float prix) {
        this.id_formations = id_formations;
        this.intitule = intitule;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.idEval = idEval;
        this.id_formateur = id_formateur;
        this.prix = prix;
    }
    

    public Formations( String intitule, String description, Date date_debut, Date date_fin, String type, int idEval, int id_formateur, float prix) {
        
        this.intitule = intitule;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.idEval = idEval;
        this.id_formateur = id_formateur;
        this.prix = prix;
    }
    

    public Formations(int id_formations, String intitule, String description, Date date_debut, Date date_fin, String type, int idEval, int id_formateur) {
        this.id_formations = id_formations;
        this.intitule = intitule;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.idEval = idEval;
        this.id_formateur = id_formateur;
    }
    
    public Formations(String intitule, String description, Date date_debut, Date date_fin, String type, int idEval, int id_formateur) {
        this.intitule = intitule;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        this.idEval = idEval;
        this.id_formateur = id_formateur;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPrix() {
        return prix;
    }

    public int getId_formations() {
        return id_formations;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getType() {
        return type;
    }

    public int getIdEval() {
        return idEval;
    }

    public int getId_formateur() {
        return id_formateur;
    }

    public void setId_formations(int id_formations) {
        this.id_formations = id_formations;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdEval(int idEval) {
        this.idEval = idEval;
    }

    public void setId_formateur(int id_formateur) {
        this.id_formateur = id_formateur;
    }

    @Override
    public String toString() {
        return "Formations{" + "id_formations=" + id_formations + ", intitule=" + intitule + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", type=" + type + ", idEval=" + idEval + ", id_formateur=" + id_formateur + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Formations other = (Formations) obj;
        if (this.id_formations != other.id_formations) {
            return false;
        }
        return true;
    }
    

}