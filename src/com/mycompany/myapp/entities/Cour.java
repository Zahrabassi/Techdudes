/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author 21655
 */
public class Cour {

      private int id ;
    private String nomCour;
    private String nomEnseignant;
    private String description;
    private String img;
    private int idD;

    public Cour(int id, String nomCour, String nomEnseignant, String description, String img, int idD) {
        this.id = id;
        this.nomCour = nomCour;
        this.nomEnseignant = nomEnseignant;
        this.description = description;
        this.img = img;
        this.idD = idD;
    }

    public Cour() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIdD() {
        return idD;
    }

    public void setIdD(int idD) {
        this.idD = idD;
    }

    @Override
    public String toString() {
        return "Cour{" + "id=" + id + ", nomCour=" + nomCour + ", nomEnseignant=" + nomEnseignant + ", description=" + description + ", img=" + img + ", idD=" + idD + '}';
    }

    

    
    
    
}
