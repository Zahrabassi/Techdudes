/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.DefaultTableModel;
import com.esprit.app.MyApplication;
import com.esprit.app.entities.Utilisateur;
import com.esprit.app.services.ServiceUtilisateur;
import java.util.ArrayList;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class FormProfile extends Form {
     private ServiceUtilisateur sv ;
    public FormProfile(Form previous, Utilisateur user) {
        setTitle("Profile");
        setLayout(BoxLayout.y());
        sv = new ServiceUtilisateur();
        
        ImageViewer profilePic = new ImageViewer(MyApplication.theme.getImage("Profile.png"));
        Label lbNom = new Label("Nom: "+user.getNom());
        Label lbEmail = new Label("Email: "+user.getEmail());
        Label lbLogin = new Label("Login: "+user.getLogin());
        Label lbRole = new Label("Role: "+user.getRole());
        Label lbDateNaissance = new Label("Date de Naissance: "+user.getAge());

        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            previous.showBack();
        });
        
        addAll(profilePic, lbEmail, lbNom, lbLogin, lbRole, lbDateNaissance);
    }
   
}
