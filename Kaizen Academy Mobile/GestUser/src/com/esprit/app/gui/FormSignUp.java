/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.TextArea;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.app.MyApplication;
import com.esprit.app.entities.Utilisateur;
import com.esprit.app.services.ControleSaisie;
import com.esprit.app.services.ServiceUtilisateur;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class FormSignUp extends Form {
    
    private ServiceUtilisateur sv ;
    

    public FormSignUp(Form previous) {
        
        sv = new ServiceUtilisateur();
        
        setTitle("Sign Up");
        ImageViewer imgv = new ImageViewer(MyApplication.theme.getImage("ka_logo.png"));
        Dimension d = new Dimension(500,500);
        imgv.setPreferredSize(d);
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom",20,TextArea.ANY);
        TextField tfEmail = new TextField("","Email",20,TextArea.EMAILADDR);
        TextField tfLogin = new TextField("","Login",10,TextArea.USERNAME);
        TextField tfPassword = new TextField("","Password",20,TextArea.PASSWORD);
        tfPassword.setConstraint(TextField.PASSWORD);
        Picker tfDateNaissance = new Picker();
        String[] characters = { "Enseignant ", " Etudiant", " Admin"};

Picker p = new Picker();
p.setStrings(characters);
p.setSelectedString(characters[0]);
p.addActionListener(e -> ToastBar.showMessage("You picked " + p.getSelectedString(), FontImage.MATERIAL_INFO));


        Button btnSignIn = new Button("Go To Sign In");
        Button btnSignUp = new Button("Register !");
        
        btnSignIn.addActionListener( ev -> {
            previous.showBack();            
        });
        
        btnSignUp.addActionListener( ev -> {
            
            if(tfNom.getText().length() > 0 
                    && tfEmail.getText().length() > 0
                    && tfLogin.getText().length() > 0
                    && tfPassword.getText().length() > 0
                    && tfDateNaissance.getText().length() > 0 )
            {
                if(ControleSaisie.isString(tfNom.getText()))
                {
                 if(ControleSaisie.valiemail(tfEmail.getText()))
                 {
                     if(ControleSaisie.isUsername(tfLogin.getText()))
                     {
                         if(ControleSaisie.validPasswor(tfPassword.getText()))
                         {
                             
                                  try {
                                        sv.SignUp(new Utilisateur(tfLogin.getText(), tfPassword.getText(),
                                        tfNom.getText(), tfEmail.getText(), tfDateNaissance.getText(),p.getSelectedString()));
                                        previous.showBack();
                                       } 
                                  catch (Exception ex) 
                                       {
                                         System.out.println(ex.getMessage());
                                         Dialog.show("Error", "Erreur : " +ex.getMessage(), "OK", null);
                
                                        }
                         }
                         else
                         {
                             Dialog.show("Error", "Password must contains numbers and caracters", "OK", null);
                         }
                     }
                     else 
                     {
                      Dialog.show("Error", "Username must contains numbers and caracters", "OK", null);
                     }

                  }
                 else
                 {
                   Dialog.show("Error", "Write a valid email !", "OK", null);
                 }
                  
                }
                else 
                {
                     
                 Dialog.show("Error", "Name must be string !", "OK", null);
                }
                              
                
                
            }else
            {
                Dialog.show("Error", "Champs vide !", "OK", null);
            }
            
        });
        
        addAll(imgv,tfNom, tfEmail, tfLogin, tfPassword, tfDateNaissance,p, btnSignUp, btnSignIn);
    }
}
