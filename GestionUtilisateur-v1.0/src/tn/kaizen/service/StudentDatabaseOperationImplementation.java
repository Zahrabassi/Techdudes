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
import tn.kaizen.entity.Student;
import tn.kaizen.utils.DBConnection;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class StudentDatabaseOperationImplementation implements StudentDatabaseOperation{

    @Override
    public Student getStudent(String studentId) throws SQLException {

        Connection connection = DBConnection.getConnection();
        String query = String.format("SELECT * FROM STUDENT WHERE Student_ID='%s'", studentId);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        if (!resultSet.next())
            return null;

        String studentName = resultSet.getString("Student_Name");
        String studentEmail = resultSet.getString("Student_Email");
        String studentBloodGroup = resultSet.getString("Student_Blood_Group");
        String studentContactNumber = resultSet.getString("Student_Contact_Number");
        String studentAddress = resultSet.getString("Student_Address");

        return new Student(studentId, studentName, studentEmail);
    }
    }
    
