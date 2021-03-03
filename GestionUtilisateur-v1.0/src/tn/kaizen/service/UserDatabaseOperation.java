/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.service;
import java.sql.SQLException;
import tn.kaizen.entity.LoginInfo;
import tn.kaizen.entity.User;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public interface UserDatabaseOperation {
    // Inserting User in database
    boolean insertUser(User user);
    // Check if User exists in database
    boolean verifyUserLogin(LoginInfo logins);
    String getUserType(LoginInfo logins) throws SQLException;
    boolean exists(User user) throws SQLException;
    
}



