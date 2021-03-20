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
public class TopVente {
    private String formation ;
    private float prix ;
    private int nbr_achats;

    public TopVente() {
    }

    public TopVente(String formation, float prix, int nbr_achats) {
        this.formation = formation;
        this.prix = prix;
        this.nbr_achats = nbr_achats;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbr_achats() {
        return nbr_achats;
    }

    public void setNbr_achats(int nbr_achats) {
        this.nbr_achats = nbr_achats;
    }

    @Override
    public String toString() {
        return "TopVente{" + "formation=" + formation + ", prix=" + prix + ", nbr_achats=" + nbr_achats + '}';
    }
    
    
    
}
