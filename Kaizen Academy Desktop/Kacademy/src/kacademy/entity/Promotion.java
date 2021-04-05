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
public class Promotion implements Comparable<Promotion>{
    private int id ;
    private int id_f ;
    private float promo ;
    private String date ;
    
    private Formation formation ;

    public Promotion() {
    }

    
    public Promotion(int id, int id_f, float promo, String date) {
        this.id = id;
        this.id_f = id_f;
        this.promo = promo;
        this.date = date;
    }

    public Promotion(int id_f, float promo) {
        this.id_f = id_f;
        this.promo = promo;
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

    public float getPromo() {
        return promo;
    }

    public void setPromo(float promo) {
        this.promo = promo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", id_f=" + id_f + ", promo=" + promo + ", date=" + date + '}';
    }

    @Override
    public int compareTo(Promotion o) {
        return this.getDate().compareTo(o.getDate());
    }
    
   
    
    
}
