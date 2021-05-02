/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.entities;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class Utilisateur {
    
    private int id;
    private String login, password, nom, email, age,role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Utilisateur(String login, String password, String nom, String email, String age, String role) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public Utilisateur(int id, String login, String password, String nom, String email, String age, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.email = email;
        this.age = age;
        this.role = role;
    }


    public Utilisateur() {
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", email=" + email + ", age=" + age + ", role=" + role + '}';
    }

    
}
