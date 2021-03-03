/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.service;

import java.sql.SQLException;
import tn.kaizen.entity.Teacher;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public interface TeacherDatabaseOperation {
    Teacher getTeacher(String teacherId) throws SQLException;
}
