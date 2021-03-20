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
public class Formation {
    
    private int id ;
    private String titre;
    private String description;
    private String date_deb;
    private String date_fin;
    private String nom_form;
    private String prenom_form;
    private String type;
    private float prix;
    private String img;
    

    public Formation() {
    }

    public Formation(String titre, String description, String date_deb, String date_fin, String nom_form, String prenom_form, String type, float prix) {
        this.titre = titre;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.nom_form = nom_form;
        this.prenom_form = prenom_form;
        this.type = type;
        this.prix = prix;
    }

    public Formation(int id, String titre, String description, String date_deb, String date_fin, String nom_form, String prenom_form, String type, float prix) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.nom_form = nom_form;
        this.prenom_form = prenom_form;
        this.type = type;
        this.prix = prix;
    }

    public Formation(int id, String titre, String description, String date_deb, String date_fin, String nom_form, String prenom_form, String type, float prix, String img) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.nom_form = nom_form;
        this.prenom_form = prenom_form;
        this.type = type;
        this.prix = prix;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getNom_form() {
        return nom_form;
    }

    public void setNom_form(String nom_form) {
        this.nom_form = nom_form;
    }

    public String getPrenom_form() {
        return prenom_form;
    }

    public void setPrenom_form(String prenom_form) {
        this.prenom_form = prenom_form;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", titre=" + titre  + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", nom_form=" + nom_form + ", prenom_form=" + prenom_form + ", type=" + type + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        return hash;
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
        final Formation other = (Formation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

    
    
   
    
    
}
