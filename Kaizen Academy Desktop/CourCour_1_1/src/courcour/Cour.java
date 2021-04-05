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
    private String nomEnseignant;
    private String description;

    public Cour(int idC, String nomCour, String nomEnseignant, String description) {
        this.idC = idC;
        this.nomCour = nomCour;
        this.nomEnseignant = nomEnseignant;
        this.description = description;
    }
    private static final Logger LOG = Logger.getLogger(Cour.class.getName());

    public int getIdC() {
        return idC;
    }

    public String getNomCour() {
        return nomCour;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public String getDescription() {
        return description;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public void setNomCour(String nomCour) {
        this.nomCour = nomCour;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    


  
    
    
}
