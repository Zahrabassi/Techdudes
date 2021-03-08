/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.print.DocFlavor;
import tn.kaizen.controllers.ChairmanDashboardController;
import tn.kaizen.entity.User;
import tn.kaizen.utils.DBConnection;
import static tn.kaizen.utils.DBConnection.getConnection;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class UserService implements Service{

    @Override
    public boolean insertUser(User user) {
/*
        * queryForLoginTable consists of the attribute the database table "LOGINS" have.
        * They are: username, password, usertype
        * username and password are later used to login, and usertype to fetch information from corresponding table
        * */
        String queryForLoginTable = String.format("INSERT INTO LOGINS VALUES('%s', '%s', '%s','%s','%s')",
                                    user.getUsername(),
                                    user.getPassword(),
                                    user.getName(),
                                    user.getEmail(),
                                    user.getType());

        // executing 2 queries
        System.out.println(queryForLoginTable);
        Connection connection = DBConnection.getConnection();
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(queryForLoginTable);
        } catch (SQLException sqle){
            System.out.println(sqle);
            System.out.println("Invalid Credentials.");
            try {
                String queryForDeletingLogin = String.format("DELETE FROM LOGINS WHERE USERNAME='%s'", user.getUsername());
                Statement statement = connection.createStatement();
                statement.executeUpdate(queryForDeletingLogin);
            } catch (SQLException e) {
            }

            return false;
        }
        return true;    }

    @Override
    public boolean verifyUserLogin(User logins) {
/*
        * This query returns count value (supposed to be 1) of username and password matching row in LOGINS table
        * */
        String queryVerification = String.format("SELECT * FROM LOGINS WHERE USERNAME='%s' AND PASSWORD='%s'",
                                            logins.getUsername(),
                                            logins.getPassword());
        
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryVerification);
            if (resultSet.next()){
                return true; // if there are elements, then the login credentials exist
            }
        } catch (SQLException e) {
        }
        return false; // login info does not exist in LOGINS table
    }

    @Override
    public String getUserType(User logins) throws SQLException {
    String query = String.format("SELECT USERTYPE FROM LOGINS WHERE USERNAME='%s'",
                logins.getUsername());
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()){
            return resultSet.getString("USERTYPE");
        }
        return null;
    }

    @Override
    public boolean exists(User user) throws SQLException {
    String query = String.format("SELECT USERNAME FROM LOGINS WHERE USERNAME='%s' AND USERTYPE='%s'",
                user.getUsername(),
                user.getType());
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return (resultSet.next());   
    }
    @Override
    public boolean Mailexists(User user) throws SQLException {
    String query = String.format("SELECT Email FROM LOGINS WHERE Email='%s'",
                user.getEmail());
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return (resultSet.next());   
    }
    
    @Override
    public User getUser(String username) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = String.format("SELECT * FROM LOGINS WHERE USERNAME='%s'", username);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        if (!resultSet.next())
            return null;

        String Name = resultSet.getString("Name");
        

        return new User(username, Name);
    }

    @Override
    public void UpdatePwd(String email, String pwd) {
        
        String query = String.format("UPDATE LOGINS SET PASSWORD='%s' WHERE Email='%s'",pwd,email);
        executeQuery(query);
    }
    @Override
    public void deleteButton(User user){
          
            String query =String.format("DELETE FROM LOGINS WHERE USERNAME='%s'",user.getUsername());
            executeQuery(query);   
            
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    @Override
    public boolean isValid(String email)
{
	if( email!=null && email.trim().length()>0 )
		return email.matches("^[a-zA-Z0-9\\.\\-\\_]+@([a-zA-Z0-9\\-\\_\\.]+\\.)+([a-zA-Z]{2,4})$");
	return false;
}
}
