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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kacademy.entity.MessageReclam;
import kacademy.entity.Reclamation;
import kacademy.entity.User;
import kacademy.utils.ConnexionSingleton;

/**
 *
 * @author LENOVO
 */
public class MessageReclamDao implements Idao<MessageReclam>{

    private static MessageReclamDao instance;
    private Statement st;
    private ResultSet rs;
    
    public MessageReclamDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static MessageReclamDao getInstance(){
        if(instance==null) 
            instance=new MessageReclamDao();
        return instance;
    }
     
    @Override
    public void insert(MessageReclam o) {
        try {
            String req="insert into message_reclam (id_reclam,id_send,message) values ("+o.getId_reclam()+","+o.getId_send()+", ? )";
            
            PreparedStatement ps = st.getConnection().prepareStatement(req);
            ps.setString(1, o.getMessage());
            
            
            
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
    
    public List<MessageReclam> getAllByReclam(int id) {
        String req = "SELECT message_reclam.* , logins.* FROM message_reclam LEFT JOIN user ON message_reclam.id_send=logins.id where message_reclam.id_reclam = "+id ;
        List<MessageReclam> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("USERNAME"));
                u.setEmail(rs.getString("Email"));
                u.setType(rs.getString("USERTYPE"));
                
                MessageReclam r = new MessageReclam();
                r.setId(rs.getInt("id"));
                r.setId_reclam(rs.getInt("id_reclam"));
                r.setId_send(rs.getInt("id_send"));
                r.setMessage(rs.getString("message"));
                r.setDate(rs.getString("date"));

                
                r.setUser_send(u);
                list.add(r);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list ;
    }

    @Override
    public void delete(MessageReclam o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MessageReclam> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MessageReclam displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(MessageReclam os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
