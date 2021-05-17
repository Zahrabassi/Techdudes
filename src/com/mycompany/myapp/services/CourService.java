/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Cour;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author 21655
 */
public class CourService {
    
    public ArrayList<Cour> cours= new ArrayList<>();
    
   
    public static CourService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public CourService() {
         req = new ConnectionRequest();
    }

    public static CourService getInstance() {
        if (instance == null) {
            instance = new CourService();
        }
        return instance;
    }

   
    

    public ArrayList<Cour> parseCours(String jsonText){
        try {
            cours=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String,Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             
            List<Map<String,Object>> list = (List<Map<String,Object>>)usersListJson.get("root");
            
            //Parcourir la liste des tâches Json
                      for(Map<String,Object> obj : list){
                    Cour t = new Cour();
                    
                    float id=Float.parseFloat(obj.get("id").toString());
                    String nomCour = obj.get("nomCour").toString();
                    String nomEnseignant = obj.get("nomEnseignant").toString();
                    String description = obj.get("description").toString();
               String img = obj.get("img").toString();
//                     float idD=Float.parseFloat(obj.get("idD").toString());
                   
                    try{ 
                   t.setId((int)id);
                   t.setNomCour(nomCour);
                   t.setNomEnseignant(nomEnseignant);
                   t.setDescription(description);
                   t.setImg(img);
//                   t.setIdD((int)idD);
}catch(Exception nl){
                        System.out.println("erreeeur 1");
}
cours.add(t);
            }
        } catch (Exception nl) {
        }
        return cours;
    }
//    
    public ArrayList<Cour> getAllCour(){
        String url = Statics.BASE_URL+"/displayCours";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 try {
                     cours = parseCours(new String(req.getResponseData()));
                 } catch (Exception ex) {
  System.out.println("erreeeur2");

                 }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cours;
    }

 
    
}
