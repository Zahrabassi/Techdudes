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
public class Reclamation implements Comparable<Reclamation>{
    private int id_reclam ;
    private int id_user ;
    private String type ;
    private String sujet ;
    private String contenu ;
    private String date ;
    
    private User user ;

    public Reclamation() {
    }

    public Reclamation(int id_reclam, int id_user, String type, String sujet, String contenu, String date) {
        this.id_reclam = id_reclam;
        this.id_user = id_user;
        this.type = type;
        this.sujet = sujet;
        this.contenu = contenu;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public int getId_reclam() {
        return id_reclam;
    }

    public void setId_reclam(int id_reclam) {
        this.id_reclam = id_reclam;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclam=" + id_reclam + ", id_user=" + id_user + ", type=" + type + ", sujet=" + sujet + ", contenu=" + contenu + ", date=" + date + '}';
    }

    @Override
    public int compareTo(Reclamation o) {
       return this.getDate().compareTo(o.getDate());
    }
    
    

    
}
