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
public class Devoir {
    private int id;
    private String nomDevoir;
    private String description;

    public Devoir(int id, String nomDevoir, String description) {
        this.id = id;
        this.nomDevoir = nomDevoir;
        this.description = description;
    }

    public Devoir() {
    }

   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomDevoir() {
        return nomDevoir;
    }

    public void setNomDevoir(String nomDevoir) {
        this.nomDevoir = nomDevoir;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Devoir{" + "id=" + id + ", nomDevoir=" + nomDevoir + ", description=" + description + '}';
    }
    


}
