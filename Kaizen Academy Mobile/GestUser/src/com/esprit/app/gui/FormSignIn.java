/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.esprit.app.entities.Utilisateur;
import com.esprit.app.services.ServiceUtilisateur;
import com.esprit.app.MyApplication;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class FormSignIn extends Form {
    
    private ServiceUtilisateur sv ;
    public FormSignIn() {
        
        
        sv = new ServiceUtilisateur();
        
        setTitle("Sign In");
       ImageViewer imgv = new ImageViewer(MyApplication.theme.getImage("ka_logo.png"));
        Dimension d = new Dimension(500,500);
        imgv.setPreferredSize(d);
        setLayout(new FlowLayout(CENTER,CENTER));
        TextField tfLogin = new TextField("","Login");
        TextField tfPwd = new TextField("","Password");
        tfPwd.setConstraint(TextField.PASSWORD);
        
        Container cnt = new Container(BoxLayout.y());

        Button btnSignIn = new Button("Sign In");
        Button btnSignUp = new Button("Create Account");
        cnt.addAll(imgv,tfLogin, tfPwd, btnSignIn, btnSignUp);
        
        btnSignIn.addActionListener( ev -> {
            
            if(tfLogin.getText().length() > 0 && tfPwd.getText().length() > 0  ){
                Utilisateur user = sv.SignIn(tfLogin.getText(), tfPwd.getText());               
                if(user != null){
                    new FormHome(this, user).show();
                }else{
                    Dialog.show("Erreur", "Erreur de login ou de mot de passe !", "OK", null);
                }
            }else{
                Dialog.show("Erreur", "Champs vide !", "OK", null);
            }
            
        });
        
        btnSignUp.addActionListener( ev -> {
            
//            TwilioSMS smsAPI = TwilioSMS.create("ACa75102693d5d169ae25c028423fe5dbe","55866e3a5ed8cfbbc97eaaeb4093536c","+12512700651");
//            ActivationForm.create("Signup").
//            show(s -> Log.p(s), smsAPI);
                
            new FormSignUp(this).show(); 
            
            
        });
        
        addAll(cnt);
    }
}
