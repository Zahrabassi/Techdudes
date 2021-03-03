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
import tn.kaizen.entity.Teacher;
import tn.kaizen.utils.DBConnection;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class TeacherDatabaseOperationImplementation implements TeacherDatabaseOperation{

    @Override
    public Teacher getTeacher(String teacherId) throws SQLException {
         Connection connection = DBConnection.getConnection();
        String query = String.format("SELECT * FROM TEACHER WHERE Teacher_Id='%s'", teacherId);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        if (!resultSet.next())
            return null;

        String teacherName = resultSet.getString("Teacher_Name");
        String teacherEmail = resultSet.getString("Teacher_Email");

        return new Teacher(teacherId, teacherName, teacherEmail);
    }
    
}
