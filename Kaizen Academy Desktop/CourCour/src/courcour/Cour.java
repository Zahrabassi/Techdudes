/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courcour;

import java.util.logging.Logger;

/**
 *
 * @author 21655
 */
public class Cour {
    
    private int idC;
    private String nomCour;
    private int idEnseignant;
    private String description;

    public Cour(int idC, String nomCour, int idEnseignant, String description) {
        this.idC = idC;
        this.nomCour = nomCour;
        this.idEnseignant = idEnseignant;
        this.description = description;
    }


    public int getIdC() {
        return idC;
    }

    public String getnomCour() {
        return nomCour;
    }

    public int getIdEnseignant() {
        return idEnseignant;
    }

    public String getDescription() {
        return description;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setnomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    public void setIdEnseignant(int idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Cour{" + "idC=" + idC + ", nomCour=" + nomCour + ", idEnseignant=" + idEnseignant + ", description=" + description + '}';
    }
    private static final Logger LOG = Logger.getLogger(Cour.class.getName());

  
    
    
}
