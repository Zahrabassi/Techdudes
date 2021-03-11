/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author 21655
 */
public class Devoir {
    private int idD;
    private String nomD;
    private String description;
    

    public Devoir(int idD, String nomD, String description) {
        this.idD = idD;
        this.nomD = nomD;
        this.description = description;
       
    }

    public Devoir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdD() {
        return idD;
    }

    public void setIdD(int idD) {
        this.idD = idD;
    }

    public String getNomD() {
        return nomD;
    }

    public void setNomD(String nomD) {
        this.nomD = nomD;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Devoir{" + "idD=" + idD + ", nomD=" + nomD + ", description=" + description + '}';
    }

    

  
    
    
}
