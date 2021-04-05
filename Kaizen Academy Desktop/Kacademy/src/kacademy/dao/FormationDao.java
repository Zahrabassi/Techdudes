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
import kacademy.entity.Formation;
import kacademy.utils.ConnexionSingleton;

/**
 *
 * @author LENOVO
 */
public class FormationDao implements Idao<Formation>{
    
    private static FormationDao instance;
    private Statement st;
    private ResultSet rs;

    public FormationDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static FormationDao getInstance(){
        if(instance==null) 
            instance=new FormationDao();
        return instance;
    }
    
    

    @Override
    public void insert(Formation o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Formation o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Formation> displayAll() {
        String req="SELECT formation.*, enseignant.nom AS nom_f , enseignant.prenom AS prenom_f FROM formation LEFT JOIN enseignant ON formation.id_formateur=enseignant.id_ens";
        List<Formation> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Formation f=new Formation();
                f.setId(rs.getInt("id_f"));
                f.setTitre(rs.getString("intitule"));
                f.setDescription(rs.getString("description"));
                f.setDate_deb(rs.getString("date_debut"));
                f.setDate_fin(rs.getString("date_fin"));
                f.setPrix(rs.getFloat("prix"));
                f.setImg(rs.getString("img"));
                f.setNom_form(rs.getString("nom_f"));
                f.setPrenom_form(rs.getString("prenom_f"));
                list.add(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("data :"+list.size());
        return list;
    }

    @Override
    public Formation displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Formation os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
