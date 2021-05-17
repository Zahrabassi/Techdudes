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
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Devoir;
import com.mycompany.myapp.services.DevoirService;
import com.mycompany.myapp.utils.Mail;
import com.pidevv.MyApplication;
import java.util.ArrayList;

/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class NewsfeedForm extends BaseForm {

    private Resources theme;
    private Resources Resources;

    public NewsfeedForm(Resources res) {
        super("MES DEVOIRS", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
       
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
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
//        
//        ButtonGroup barGroup = new ButtonGroup();
//        RadioButton partage = RadioButton.createToggle("Mes Devoirs", barGroup);
//        partage.setUIID("SelectBar");
//        RadioButton mesListes = RadioButton.createToggle("a faire", barGroup);
//        mesListes.setUIID("SelectBar");
//        RadioButton liste = RadioButton.createToggle("encours", barGroup);
//        liste.setUIID("SelectBar");
//        RadioButton myFavorite = RadioButton.createToggle("fait", barGroup);
//        myFavorite.setUIID("SelectBar");
//        Label arrow = new Label(res.getImage("bluedown.png"), "Container");
//        
//        add(LayeredLayout.encloseIn(
//                GridLayout.encloseIn(4, partage, mesListes, liste, myFavorite),
//                FlowLayout.encloseBottom(arrow)
//        ));
//        
//        partage.setSelected(true);
//        arrow.setVisible(false);
//        addShowListener(e -> {
//            arrow.setVisible(true);
//            updateArrowPosition(partage, arrow);
//        });
//        bindButtonSelection(partage, arrow);
//        bindButtonSelection(mesListes, arrow);
//        bindButtonSelection(liste, arrow);
//     
//        
//        // special case for rotation
//        addOrientationListener(e -> {
//            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
//        });
      ArrayList<Devoir>List = DevoirService.getInstance().getAllDevoir();
      
      for(Devoir rec : List){
         String urlImage = "blue.png";
         
         Image placeHolder = Image.createImage(120, 90);
         
          EncodedImage enc = EncodedImage.createFromImage(placeHolder,false);
          URLImage urlim = URLImage.createToStorage(enc, urlImage,urlImage, URLImage.RESIZE_SCALE);
         
          
          addButton(urlim,rec.getNomDevoir(), rec.getDescription(),rec);
     
                ScaleImageLabel image = new ScaleImageLabel(urlim);
                
                Container containerImg = new Container();
                
                image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);


      }              FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);

     
fab.bindFabToContainer(getContentPane());

fab.createSubFAB(FontImage.MATERIAL_IMPORT_CONTACTS, "envoie mail").addActionListener(e->{
Form hi=new Form();

 Mail m=new Mail();
   hi.add(m.createDemo());
   hi.show();
 
                 
});
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
    
   private void addButton(Image img, String nomDevoir, String description , Devoir devoir) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);

       
       Button btn= new Button("nomDevoir : "+nomDevoir,"NewsTopLine2");
       Label descr = new Label("description :"+description,"NewsTopLine2");
  cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(btn),BoxLayout.encloseX(descr) ));
     
         add(cnt);
            Form f2=new Form("EVALUER CE COUR",BoxLayout.x());
            Form hi = new Form("envoyer un mail", BoxLayout.y());
  btn.addActionListener(e->{
    FileSystemStorage fs = FileSystemStorage.getInstance();
    String fileName = fs.getAppHomePath() + "pdf.pdf";
    if(!fs.exists(fileName)) {
        Util.downloadUrlToFile("https://classroom.google.com/u/0/c/MjYzMTQ0NDAwNjE0", fileName, true);
    }
    Display.getInstance().execute(fileName);
}); 
//              FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
//
//     
//fab.bindFabToContainer(getContentPane());
//
//fab.createSubFAB(FontImage.MATERIAL_IMPORT_CONTACTS, "envoie mail").addActionListener(e->{
//Form hi=new Form();
//
// Mail m=new Mail();
//   hi.add(m.createDemo());
//   hi.show();
// 
//                 
//});
//   


     
   }
   
   
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
}
