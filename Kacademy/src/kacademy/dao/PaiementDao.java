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
import kacademy.entity.Paiement;
import kacademy.entity.User;
import kacademy.utils.ConnexionSingleton;

/**
 *
 * @author LENOVO
 */
public class PaiementDao implements Idao<Paiement> {

    private static PaiementDao instance;
    private Statement st;
    private ResultSet rs;

    private PaiementDao() {
        ConnexionSingleton cs = ConnexionSingleton.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PaiementDao getInstance() {
        if (instance == null) {
            instance = new PaiementDao();
        }
        return instance;
    }

    @Override
    public void insert(Paiement o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Paiement o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Paiement> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Paiement displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Paiement os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Paiement getPayCarte(String num, String date, String code) {
        String req = "select * from paiement where num = '" + num + "' and code = '" + code + "' and DATE(date_exp) = '"+date+"'";
        int i = 0;
        try {
            rs = st.executeQuery(req);

            // while(rs.next()){
            rs.next();
            i = rs.getRow();
            if (i > 0) {
                Paiement p = new Paiement();
                p.setId(rs.getInt("id"));
                p.setNum(rs.getString("num"));
                p.setDate_exp(rs.getString("date_exp"));
                p.setCode(rs.getString("code"));
                p.setSolde(rs.getFloat("solde"));
               
                return p;

            } else {
                return null;
            }

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean doPay(int id , float montant) {
        String qry = "UPDATE paiement SET solde = (solde - "+montant+" )  WHERE id = "+id ;
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
