/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Form;
import com.mycompany.myapp.entities.Cour;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Devoir;
import com.mycompany.myapp.services.CourService;
import com.mycompany.myapp.services.DevoirService;
import com.mycompany.myapp.utils.Mail;
import java.util.ArrayList;

/**
 *
 * @author 21655
 */
public class NewsCour extends BaseForm {
    

       Form current;

    public Resources theme;
    
      public NewsCour(Resources res){
        
        current = this;
       
              setTitle("List des cours");
        setLayout(BoxLayout.y());
     //    super("MES DEVOIRS", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      TextField searchField;
        searchField = new TextField("", "' "); 
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        getToolbar().setTitleComponent(searchField);
        getContentPane().setScrollVisible(false);
        
        
        
        
        
        
        super.addSideMenu(res);
        //tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
    
        
           addTab(swipe,spacer1, res.getImage("blue.png"),"","",res);
        
        
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
       
        Label arrow = new Label(res.getImage("bluedown.png"), "Container");
        
     
        
        // special case for rotation
       
      ArrayList<Devoir>List = DevoirService.getInstance().getAllDevoir();
      
      for(Devoir rec : List){
         String urlImage = "blue.png";
         
         Image placeHolder = Image.createImage(120, 90);
         
          EncodedImage enc = EncodedImage.createFromImage(placeHolder,false);
          URLImage urlim = URLImage.createToStorage(enc, urlImage,urlImage, URLImage.RESIZE_SCALE);
         
          
     
     
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
      
      
      
      
      
      }    
      for (Cour w : new CourService().getAllCour())
        {
           
            this.add(affichage(w));
        }
        this.show();
      

    }
    public Container affichage(Cour c){
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
       Button btn=new Button(" "+c.getNomCour());
        Label lab2=new Label("NomEnseignant :"+c.getNomEnseignant());
        Label lab=new Label("Description :"+c.getDescription());

      
      
          
  cn2.add(btn).add(lab2).add(lab);
        cn1.add(BorderLayout.WEST, cn2);
        
           btn.addActionListener(e->{
            Form f2=new Form("EVALUER CE COUR",BoxLayout.x());

     
          
              FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);

fab.bindFabToContainer(getContentPane());

fab.createSubFAB(FontImage.MATERIAL_IMPORT_CONTACTS, "envoie mail").addActionListener(dd->{
Form hi=new Form();

 Mail m=new Mail();
   hi.add(m.createDemo());
   hi.show();
});  
    
                   f2.show();
        });
        
           return cn1;

    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Label spacer, Image img, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
       
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
       
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(image,
                overlay,
                BorderLayout.south(BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                           
                            spacer
                        )
                )
            );

        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);
    }

}