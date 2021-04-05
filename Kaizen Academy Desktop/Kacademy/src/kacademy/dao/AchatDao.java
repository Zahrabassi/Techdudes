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
import kacademy.entity.Achat;
import kacademy.entity.Formation;
import kacademy.entity.TopVente;
import kacademy.utils.ConnexionSingleton;

/**
 *
 * @author LENOVO
 */
public class AchatDao implements Idao<Achat>{

    private static AchatDao instance;
    private Statement st;
    private ResultSet rs;
    
    public AchatDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static AchatDao getInstance(){
        if(instance==null) 
            instance=new AchatDao();
        return instance;
    }

    @Override
    public void insert(Achat o) {
        String req="insert into achat (id_f,id_etud,prix) values ("+o.getId_f()+","+o.getId_etud()+","+o.getPrix()+")";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AchatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insert(List<Achat> o) {
        String values = "";
        for(Achat a : o){
            values += "("+a.getId_f()+" , "+a.getId_etud()+" , "+a.getPrix()+" ) ,";
        }
        values = values.substring(0, values.length() - 1);
        
        String req="insert into achat (id_f,id_etud,prix) values "+values;
        System.out.println(req);
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AchatDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Achat o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Achat> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Achat> getAllByUser(int id ) {
        String req = "SELECT achat.*, formation.* FROM achat LEFT JOIN formation ON achat.id_f=formation.id_f where achat.id_etud = "+id ;
        List<Achat> list=new ArrayList<>();
        
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
                
                Achat a = new Achat();
                a.setId(rs.getInt("id"));
                a.setId_f(rs.getInt("id_f"));
                a.setId_etud(rs.getInt("id_etud"));
                a.setDate(rs.getString("date"));
                a.setPrix(rs.getFloat("prix"));
                
                a.setFormation(f);
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list ;
    }

    @Override
    public Achat displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Achat os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<TopVente> getTopVente() {
        String req = "SELECT f.intitule as `formation` ,f.prix, COUNT(a.id_f) AS `nbr` FROM achat a LEFT JOIN formation f ON a.id_f=f.id_f GROUP BY a.id_f ORDER BY `nbr` DESC LIMIT 5" ;
        List<TopVente> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                TopVente t = new TopVente();
                t.setFormation(rs.getString("formation"));
                t.setPrix(rs.getFloat("prix"));
                t.setNbr_achats(rs.getInt("nbr"));
               
                list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list ;
    }
    
        
    
}
