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
import tn.kaizen.entity.Chairman;
import tn.kaizen.utils.DBConnection;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class ChairmanDatabaseOperationImplementation implements ChairmanDatabaseOperation {
    @Override
    public Chairman getChairman(String chairmanId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = String.format("SELECT * FROM CHAIRMAN WHERE Chairman_Id='%s'", chairmanId);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        if (!resultSet.next())
            return null;

        String chairmanName = resultSet.getString("Chairman_Name");
        String chairmanEmail = resultSet.getString("Chairman_Email");

        return new Chairman(chairmanId, chairmanName, chairmanEmail);
    }
  
}
