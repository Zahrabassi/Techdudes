/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy.dao;

import kacademy.entity.TestEvaluation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kacademy.utils.ConnexionSingleton;

/**
 *
 * @author user
 */
public class EvaluationServices {
private static EvaluationServices instance;
      private Statement st;
    private ResultSet rs;

    public EvaluationServices() {
          ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static EvaluationServices getInstance(){
          if(instance==null) 
            instance=new EvaluationServices();
        return instance;
    }
    
    
    public void insert(TestEvaluation o) {
    String req="INSERT INTO `evaluation`( `Nom_evaluation`, `lien_evaluation`) VALUES ('"+o.getNom_evaluation()+"','"+o.getLien_evaluation()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException e) {
            Logger.getLogger(EvaluationServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void delete(TestEvaluation o) {
        String req="DELETE FROM `evaluation` WHERE `id_eval`='"+o.getId_evaluation()+"'";
        try {
            st.executeUpdate(req);
        } catch (SQLException e) {
            Logger.getLogger(EvaluationServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void update(TestEvaluation o) {
         String req="UPDATE `evaluation` SET `Nom_evaluation`='"+o.getNom_evaluation()+"',`lien_evaluation`='"+o.getLien_evaluation()+"' WHERE `id_eval`='"+o.getId_evaluation()+"'";
       try {
            st.executeUpdate(req);
        } catch (SQLException e) {
            Logger.getLogger(EvaluationServices.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ObservableList<TestEvaluation> afficherEvaluations() {
         String req="SELECT * FROM `evaluation`;";
           ObservableList<TestEvaluation> list=FXCollections.observableArrayList();  
           try {
            rs=st.executeQuery(req);
               while (rs.next()) {
                    TestEvaluation ev=new TestEvaluation();
                   ev.setId_evaluation(Integer.parseInt(rs.getString(1)));
                   ev.setNom_evaluation(rs.getString(2));
                   ev.setLien_evaluation(rs.getString(3));
                   list.add(ev);
               }
                }
           catch (SQLException e) {
            Logger.getLogger(EvaluationServices.class.getName()).log(Level.SEVERE, null, e);
        }
           return list;
           
    }
    
}
