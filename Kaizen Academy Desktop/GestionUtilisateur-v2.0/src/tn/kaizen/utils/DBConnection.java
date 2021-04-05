/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class DBConnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static  final String HOSTNAME = "localhost:3306";
    private static final String DBNAME = "kaizen_academy";
    private static final String URL = "jdbc:mysql://" + HOSTNAME + "/" + DBNAME;

    private static Connection connection = null;
    private static final DBConnection INSTANCE = new DBConnection();

    // private constructor to create a connection once
    private DBConnection(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to database.");
        } catch (SQLException sqle){
            System.out.println("Error connecting to database!");
        }
    }

    // public method to get connection to database
    public static Connection getConnection(){
        return connection;
    }
}
