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
import kacademy.entity.Promotion;
import kacademy.utils.ConnexionSingleton;

/**
 *
 * @author LENOVO
 */
public class PromotionDao implements Idao<Promotion>{
    
    private Statement st;
    private ResultSet rs;
    private static PromotionDao instance ;
    
    public PromotionDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static PromotionDao getInstance(){
        if(instance==null) 
            instance=new PromotionDao();
        return instance;
    }

    @Override
    public void insert(Promotion o) {
       String req="insert into promotion (id_f,promo) values ("+o.getId_f()+","+o.getPromo()+")";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AchatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Promotion o) {
        String req="delete from promotion where id = "+o.getId() ;
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AchatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Promotion> displayAll() {
        String req="SELECT p.* ,f.* ,e.* from promotion p INNER JOIN formation f ON p.id_f = f.id_f INNER JOIN enseignant e ON f.id_formateur = e.id_ens";
        List<Promotion> list=new ArrayList<>();
        
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
                f.setNom_form(rs.getString("nom"));
                f.setPrenom_form(rs.getString("prenom"));
                Promotion p = new Promotion();
                p.setId(rs.getInt("id"));
                p.setId_f(rs.getInt("id_f"));
                p.setDate(rs.getString("date"));
                p.setPromo(rs.getFloat("promo"));
                p.setFormation(f);
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("data :"+list.size());
        return list;
    }

    @Override
    public Promotion displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Promotion p) {
        String qry = "UPDATE promotion SET id_f = "+p.getId_f()+", promo = "+p.getPromo()+" WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
   
    public List<String> getMailList() {
        String req="SELECT email from user where role ='etud_vente' or role = 'etud_reclam'";
        List<String> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                list.add(rs.getString("email"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("data :"+list.size());
        return list;
    }

    
}
