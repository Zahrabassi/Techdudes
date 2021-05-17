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
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Devoir;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author 21655
 */
public class DevoirService {
       public ArrayList<Devoir>devoirs= new ArrayList<>();
    
   
    public static DevoirService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public DevoirService() {
         req = new ConnectionRequest();
    }

    public static DevoirService getInstance() {
        if (instance == null) {
            instance = new DevoirService();
        }
        return instance;
    }

    public DevoirService(Form current, Resources res) {
    }
   
    

    public ArrayList<Devoir> parseDevoirs(String jsonText){
        try {
            devoirs=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

           
            Map<String,Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
             
            List<Map<String,Object>> list = (List<Map<String,Object>>)usersListJson.get("root");
            
            //Parcourir la liste des tâches Json
                      for(Map<String,Object> obj : list){
                    Devoir t = new Devoir();
                    
                    float id=Float.parseFloat(obj.get("id").toString());
                    String nomDevoir = obj.get("nomDevoir").toString();
                    String description = obj.get("description").toString();
                     
                    try{ 
                 t.setId((int)id);
                 t.setNomDevoir(nomDevoir);
                 t.setDescription(description);
                }catch(Exception nl){
                        System.out.println("erreeeur 1");
   
}
devoirs.add(t);
            }
            
            
        } catch (Exception nl) {
            
        }
       
        return devoirs;
    }
//    
    public ArrayList<Devoir> getAllDevoir(){
        String url = Statics.BASE_URL+"/displayDevoirs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 try {
                     devoirs = parseDevoirs(new String(req.getResponseData()));
                 } catch (Exception ex) {
                                            System.out.println("erreeeur2");

                 }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return devoirs;
    }

 
    
    
}
