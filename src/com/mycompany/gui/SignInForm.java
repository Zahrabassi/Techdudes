/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Devoir;
import com.mycompany.myapp.services.DevoirService;
import com.mycompany.myapp.utils.Mail;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

      Form current;
    
      public SignInForm(Form previous){
        
        current = this;
       
              setTitle("List des réfugiés");
        setLayout(BoxLayout.y());
       
        for (Devoir w : new DevoirService().getAllDevoir())
        {
           
            this.add(affichage(w));
        }
        this.show();
    
              FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);

fab.bindFabToContainer(getContentPane());

fab.createSubFAB(FontImage.MATERIAL_IMPORT_CONTACTS, "envoie mail").addActionListener(e->{
Form hi=new Form();

 Mail m=new Mail();
   hi.add(m.createDemo());
   hi.show();
});  
          
    }
    
        
    public Container affichage(Devoir c){
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
    //    Button btn=new Button("Nom : "+c.getNoun());
         Label lab=new Label("Description :"+c.getDescription());
        
    //    cn2.add(btn).add(lab);
        cn1.add(BorderLayout.WEST, cn2);
        //cn1.add(BorderLayout.CENTER,labnbr);
     //supprimer   
     //   btn.addActionListener(e->{
            Form f2=new Form("Details",BoxLayout.y());
         // TextField  tfNom=new TextField(c.getNoun());
          TextField  tfPrenom=new TextField(c.getDescription());


          Button btn_modif=new Button("modifier");
           Button btn_sup=new Button("supprimer");
        // f2.add("Event").add("Nom : ").add(tfNom).add("Description : ").add(tfPrenom).add(btn_sup).add(btn_modif);
     btn_sup.addActionListener(ww ->
     
     {
      //   new eventService().suppEvent(c.getId());
       //  new afficherEvent(this).showBack();
     }
     
     );
          btn_modif.addActionListener(aa ->
     
     {                    

    
         
      //   c.setNoun(tfNom.getText());
       //  c.setDescription(tfPrenom.getText());
      
       //  new eventService().modifierEvent(c);
       //  new afficherEvent(this).showBack();
     }
     
     );
            f2.getToolbar().addCommandToLeftBar("back", null, ev->{
            this.show();
        });
                   f2.show();
      //  });
        
     //   cn1.setLeadComponent(btn);
        return cn1;
                
    }
    
    
}
