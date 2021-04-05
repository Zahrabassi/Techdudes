/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kacademy.entity.User;
import kacademy.utils.ConnexionSingleton;

/**
 *
 * @author LENOVO
 */
public class UserDao implements Idao<User>{
    
     private static UserDao instance;
    private Statement st;
    private ResultSet rs;
    
    private UserDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static UserDao getInstance(){
        if(instance==null) 
            instance=new UserDao();
        return instance;
    }

    @Override
    public void insert(User o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(User os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public User doLogin(String username , String password ){
        
         String req="select * from user where username = '"+username+"' and password = '"+password+"'";
           int i = 0 ;
        try {
            rs=st.executeQuery(req);
            
            
           // while(rs.next()){
            rs.next();
            i = rs.getRow();
            if(i > 0 ){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));  
                u.setEmail(rs.getString("email"));  
                u.setPassword(rs.getString("password"));  
                u.setType(rs.getString("USERTYPE")); 
                
                return u ;
                
            }else{
                return null;
            }
            
            //}  
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null ;
        }
        
    }
    
}
