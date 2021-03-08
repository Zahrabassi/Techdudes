/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.service;

import java.sql.SQLException;
import tn.kaizen.entity.User;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public interface Service {
    // Inserting User in database
    boolean insertUser(User user);
    // Check if User exists in database

    /**
     *
     * @param logins
     * @return
     */
    boolean verifyUserLogin(User logins);
    String getUserType(User logins) throws SQLException;
    boolean exists(User user) throws SQLException;
    public boolean Mailexists(User user) throws SQLException ;
    public User getUser(String username) throws SQLException;
    void UpdatePwd(String email,String pwd);
    public void deleteButton(User user);
    public boolean isValid(String email);
}
