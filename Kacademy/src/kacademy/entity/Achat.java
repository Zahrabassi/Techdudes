/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy.entity;

/**
 *
 * @author LENOVO
 */
public class Achat {
    
    private int id ;
    private int id_f;
    private int id_etud ;
    private String date ;
    private float prix ;
    
    private Formation formation; 

    
    public Achat() {
    }

    public Achat(int id, int id_f, int id_etud, String date, float prix) {
        this.id = id;
        this.id_f = id_f;
        this.id_etud = id_etud;
        this.date = date;
        this.prix = prix;
    }

    public Achat(int id_f, int id_etud, float prix) {
        this.id_f = id_f;
        this.id_etud = id_etud;
        this.prix = prix;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_f() {
        return id_f;
    }

    public void setId_f(int id_f) {
        this.id_f = id_f;
    }

    public int getId_etud() {
        return id_etud;
    }

    public void setId_etud(int id_etud) {
        this.id_etud = id_etud;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Achat{" + "id=" + id + ", id_f=" + id_f + ", id_etud=" + id_etud + ", date=" + date + ", prix=" + prix + '}';
    }
    
    
    
    
    
    
    
}
