/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.logging.Logger;
import javafx.collections.transformation.SortedList;

/**
 *
 * @author 21655
 */
public class Cour {

    public static void setItems(SortedList<Cour> sortedData) {
       
    }
      private int idC;
    private String nomCour;
    private String nomEnseignant;
    private String description;

    public Cour(int idC, String nomCour, String nomEnseignant, String description) {
        this.idC = idC;
        this.nomCour = nomCour;
        this.nomEnseignant = nomEnseignant;
        this.description = description;
    }
    private static final Logger LOG = Logger.getLogger(Cour.class.getName());

    public Cour() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getNomCour() {
        return nomCour;
    }

    public void setNomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cour{" + "idC=" + idC + ", nomCour=" + nomCour + ", nomEnseignant=" + nomEnseignant + ", description=" + description + '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
