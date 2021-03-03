/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kaizen.entity;

/**
 *
 * @author Iheb HAMDI <iheb.hamdi.1@esprit.tn>
 */
public class Teacher {
    private String teacherInitial;
    private String teacherName;
    private String teacherEmail;

    public Teacher(String teacherInitial, String teacherName, String teacherEmail) {
        this.teacherInitial = teacherInitial;
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherInitial() {
        return teacherInitial;
    }

    public void setTeacherInitial(String teacherInitial) {
        this.teacherInitial = teacherInitial;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
     @Override
    public String toString() {
        return String.format("%s (%s)",
                            this.teacherName,
                            this.teacherInitial);
    }

   
    
}
