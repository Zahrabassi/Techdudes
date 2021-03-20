/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import kacademy.entity.Achat;
import kacademy.entity.Formation;
import kacademy.entity.Reclamation;
import kacademy.entity.User;
import kacademy.utils.ConnexionSingleton;

/**
 *
 * @author LENOVO
 */
public class ReclamationDao implements Idao<Reclamation>{
    
    private static ReclamationDao instance;
    private Statement st;
    private ResultSet rs;
    
    public ReclamationDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static ReclamationDao getInstance(){
        if(instance==null) 
            instance=new ReclamationDao();
        return instance;
    }

    @Override
    public void insert(Reclamation o) {
        try {
            String req="insert into reclamation (id_user,sujet,type,contenu) values ("+o.getId_user()+", ?, '"+o.getType()+"', ? )";
            
            PreparedStatement ps = st.getConnection().prepareStatement(req);
            ps.setString(1, o.getSujet());
            ps.setString(2, o.getContenu());
            
            
            
            try {
                //st.executeUpdate(req);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AchatDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Reclamation o) {
        String req="delete from reclamation where id_reclam = "+o.getId_reclam();
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AchatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    public List<Reclamation> getAllByUser(int id) {
        String req = "SELECT reclamation.* , logins.* FROM reclamation LEFT JOIN user ON reclamation.id_user=logins.id where reclamation.id_user = "+id ;
        List<Reclamation> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setType(rs.getString("USERTYPE"));
                
                Reclamation r = new Reclamation();
                r.setId_reclam(rs.getInt("id_reclam"));
                r.setId_user(rs.getInt("id_user"));
                r.setSujet(rs.getString("sujet"));
                r.setType(rs.getString("type"));
                r.setContenu(rs.getString("contenu"));
                r.setDate(rs.getString("date"));

                
                r.setUser(u);
                list.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list ;
    }

    @Override
    public Reclamation displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Reclamation os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> displayAll() {
        String req = "SELECT reclamation.* , logins.* FROM reclamation LEFT JOIN user ON reclamation.id_user=logins.id  ";
        List<Reclamation> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setType(rs.getString("USERTYPE"));
                
                Reclamation r = new Reclamation();
                r.setId_reclam(rs.getInt("id_reclam"));
                r.setId_user(rs.getInt("id_user"));
                r.setSujet(rs.getString("sujet"));
                r.setType(rs.getString("type"));
                r.setContenu(rs.getString("contenu"));
                r.setDate(rs.getString("date"));

                
                r.setUser(u);
                list.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list ;
    }
    
    public Map<String, Integer> getStatistique(){
        Map<String, Integer> list = new HashMap<>();
        
        String req = "SELECT type, COUNT(type) AS `nbr` FROM reclamation GROUP BY type ORDER BY `nbr` DESC" ;
     
        try {
            rs=st.executeQuery(req); 
            while(rs.next()){
                list.put(rs.getString("type"),rs.getInt("nbr"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return list ;
    }
    
}
