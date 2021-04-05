/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kacademy.entity;

/**
 *
 * @author LENOVO
 */
public class MessageReclam {
    private int id ;
    private int id_reclam ;
    private int id_send ;
    private String message ;
    private String date ;
    private User user_send ;

    public MessageReclam() {
    }

    public MessageReclam(int id, int id_reclam, int id_send, String message, String date) {
        this.id = id;
        this.id_reclam = id_reclam;
        this.id_send = id_send;
        this.message = message;
        this.date = date;
    }

    public User getUser_send() {
        return user_send;
    }

    public void setUser_send(User user_send) {
        this.user_send = user_send;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_reclam() {
        return id_reclam;
    }

    public void setId_reclam(int id_reclam) {
        this.id_reclam = id_reclam;
    }

    public int getId_send() {
        return id_send;
    }

    public void setId_send(int id_send) {
        this.id_send = id_send;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageReclam{" + "id=" + id + ", id_reclam=" + id_reclam + ", id_send=" + id_send + ", message=" + message + ", date=" + date + '}';
    }
    
    

    
}
