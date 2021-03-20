/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kacademy.entity.Formations;
import kacademy.utils.ConnexionSingleton;

/**
 *
 * @author user
 */
public class FormationCrud{
    private static FormationCrud instance;
      private Statement st;
    private ResultSet rs;
    
    private FormationCrud() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FormationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static FormationCrud getInstance(){
        if(instance==null) 
            instance=new FormationCrud();
        return instance;
    }

    public void insert(Formations o) {
          String req="INSERT INTO `formation`(`intitule`, `description`, `date_debut`, `id_eval`, `id_formateur`, `date_fin`, `type`,`prix`) VALUES ('"+o.getIntitule()+"','"+o.getDescription()+"','"+o.getDate_debut()+"','"+o.getIdEval()+"','"+o.getId_formateur()+"','"+o.getDate_fin()+"','"+o.getType()+"','"+o.getPrix()+"')";
        try {
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Formations o) {
             String req="DELETE FROM `formation` WHERE `id_f`=" + o.getId_formations()+ "";
        try {
            st.executeUpdate(req);
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean update(Formations o) {
        String req="UPDATE `formation` SET `intitule`='"+o.getIntitule()+"',`description`='"+o.getDescription()+"',`date_debut`='"+o.getDate_debut()+"',`id_eval`='"+o.getIdEval()+"',`id_formateur`='"+o.getId_formateur()+"',`date_fin`='"+o.getDate_fin()+"',`type`='"+o.getType()+"',`prix`='"+o.getPrix()+"' WHERE `id_f`="+o.getId_formations()+"";
        try {
              st.executeUpdate(req); 
        } catch (SQLException ex) {
             Logger.getLogger(FormationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
       return true; 
    }

    public ObservableList<Formations> afficherLesFormations() {
                String req="SELECT `id_f`, `intitule`, `description`, `date_debut`, `id_eval`, `id_formateur`, `date_fin`, `type`, `prix` FROM `formation`";
           ObservableList<Formations> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Formations f=new Formations();
                f.setId_formations(Integer.parseInt(rs.getString("id_f")));
                f.setIntitule(rs.getString("intitule"));
                f.setDescription(rs.getString("description"));
                f.setDate_debut(rs.getDate(4));
                f.setIdEval(rs.getInt(5));
                f.setId_formateur(rs.getInt(6));
                f.setDate_fin(rs.getDate(7));
                f.setType(rs.getString(8));
                f.setPrix(rs.getFloat(9));
                list.add(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }  
}
