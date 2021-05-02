/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.FlowLayout;
import com.esprit.app.MyApplication;
import com.esprit.app.entities.Utilisateur;


/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class FormHome extends Form {

    public FormHome(Form previous, Utilisateur user) {
        setTitle("Home");ImageViewer imgv = new ImageViewer(MyApplication.theme.getImage("ka_logo.png"));
        Dimension d = new Dimension(500,500);
        imgv.setPreferredSize(d);
        add(imgv);
        
        setLayout(new FlowLayout(CENTER, CENTER));
        
        add(new Label(user.getRole()+" "+user.getNom()));
        
        getToolbar().addCommandToSideMenu("Home", null, (evt) -> {
            Dialog.show("Information", "Vous etes dans la form home !", "OK", null);
        });
        
        getToolbar().addCommandToSideMenu("Profile", null, (evt) -> {
            new FormProfile(this, user).show();
        });
        getToolbar().addCommandToSideMenu("Liste des utilisateurs", null, (evt) -> {
            new FormUtlisateur(this).show();
        });    
        
        getToolbar().addCommandToRightBar("Logout", null, (evt) -> {
       new FormSignIn().show(); 

            
        });
    }   
}
