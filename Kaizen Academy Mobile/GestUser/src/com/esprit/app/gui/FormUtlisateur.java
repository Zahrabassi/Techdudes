/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.app.MyApplication;
import com.esprit.app.entities.Utilisateur;
import com.esprit.app.services.ServiceUtilisateur;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class FormUtlisateur extends Form{

    private ServiceUtilisateur sv;
    
    public FormUtlisateur(Form previous) {
        System.out.println(""+ServiceUtilisateur.maListe.toString());
        
        sv = new ServiceUtilisateur();
        
        setTitle("Liste des utilisateurs");
        setLayout(BoxLayout.y());
        
        
        for (int i = 0; i < ServiceUtilisateur.maListe.size(); i++) {
            add(addusers(ServiceUtilisateur.maListe.get(i)));
        }
        
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            previous.showBack();
        });
    }
        
    
    public Container addusers(Utilisateur user){
        
        Container holder = new Container(BoxLayout.x());
        Container details = new Container(BoxLayout.y());

        Label lbnom = new Label(user.getNom());
        Label lbrole = new Label(user.getRole());
        Label lbmail = new Label(user.getEmail());
        
        details.addAll(lbnom,lbrole, lbmail);
                        
       EncodedImage enc = EncodedImage.createFromImage(MyApplication.theme.getImage("load.png"), false);
        
       Image img = URLImage.createToStorage(enc, "USER Name"+user.getNom(),"profile.png", URLImage.RESIZE_SCALE);
        
       ImageViewer image = new ImageViewer(img);
        
        holder.addAll(image, details);
        
        lbnom.addPointerReleasedListener(ev->{
           if(Dialog.show("Confirmation", "Delete " + user.getNom()+ " " +user.getEmail()+ " from database?", "Oui", "non")){
               try {
                   sv.delete(user.getNom(), user.getEmail());
                   System.out.println("Delete OK !");
               } catch (Exception ex) {
                   Dialog.show("Error", "Erreur de suppression!", "OK", null);
               }
               
           } 
        });
        
        holder.setLeadComponent(lbnom);
        
        return holder;
    }
}
