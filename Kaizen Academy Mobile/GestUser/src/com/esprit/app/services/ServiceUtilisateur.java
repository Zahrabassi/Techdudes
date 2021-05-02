/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.services;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.esprit.app.MyApplication;
import com.esprit.app.entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class ServiceUtilisateur {
    public static List<Utilisateur> maListe = new ArrayList();
    
    private Database bd ;

    public ServiceUtilisateur() {
        bd = MyApplication.myDataBase;
    }
    
    public void SignUp(Utilisateur u) throws Exception{
        try {
            bd.execute("INSERT INTO utilisateur (nom, email, login, password, age,role) "
                    + "VALUES ( '"+ u.getNom()+"', '"+ u.getEmail()+"', '"+ u.getLogin()+"', '"+ u.getPassword()+"', '"+ u.getAge()+"','"+ u.getRole()+"');");
            System.out.println("INSERTITION OK");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Utilisateur SignIn(String login, String pwd) {
        Utilisateur user = null;
        try {
            Cursor cr = bd.executeQuery("SELECT * FROM utilisateur WHERE login = '"+login+"' AND password = '"+pwd+"' ;");
            
            while (cr.next()) {
                Row r = cr.getRow();
                user = new Utilisateur();
                user.setId(r.getInteger(0));
                user.setNom(r.getString(1));
                user.setEmail(r.getString(2));
                user.setLogin(r.getString(3));
                user.setPassword(r.getString(4));
                user.setAge(r.getString(5));
                user.setRole(r.getString(6));
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return user;
    }
    
    public List<Utilisateur> AfficherListUtlisateurs(){
        
        
        try {
            Cursor cr = bd.executeQuery("SELECT * FROM utilisateur");
            
            while (cr.next()) {
                Row r = cr.getRow();
                int id = r.getInteger(0);
                String nom=r.getString(1);
                String email=r.getString(2);
                String login=r.getString(3);
                String pwd=r.getString(4);
                String age=r.getString(5);
                String role=r.getString(6);
                maListe.add(new Utilisateur(id, login, pwd, nom, email, age, role));                      
                
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return maListe;
    }
    
     public void delete(String nom, String email)throws Exception {
        try {
            bd.executeQuery("DELETE FROM utilisateur WHERE nom = '"+nom+"' AND email = '"+email+"' ;");
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
