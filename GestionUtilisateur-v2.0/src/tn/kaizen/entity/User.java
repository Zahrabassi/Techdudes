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
public class User {
    private String name;
    private String email;
    private String username;
    private String password;
    private String type;

    public User(String name, String email, String username, String password, String type) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(String email) {
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

   

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", username=" + username + ", type=" + type + '}';
    }
    
}
