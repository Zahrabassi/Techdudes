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
public class Paiement {
    
    private int id ;
    private String num;
    private String date_exp ;
    private String code ;
    private float solde ;

    public Paiement() {
    }

    public Paiement(int id, String num, String date_exp, String code, float solde) {
        this.id = id;
        this.num = num;
        this.date_exp = date_exp;
        this.code = code;
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(String date_exp) {
        this.date_exp = date_exp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Paiement{" + "id=" + id + ", num=" + num + ", date_exp=" + date_exp + ", code=" + code + ", solde=" + solde + '}';
    }
    
    
    
}
