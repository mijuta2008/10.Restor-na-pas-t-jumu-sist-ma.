/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Viktorija.Balalajeva
 */
public class User {
    private int user_id;
    private String name;
    private String login;
    private String password;
    private String role;
    
    public int getUserId() {
    return this.user_id;
    }
    
    public void setUserId(int user_id) {
    this.user_id = user_id;
    }
    
    public String getName() {
    return this.name;
    }
    
    public void setName(String name) {
    this.name = name;
    }
    
    public String getLogin() {
    return this.login;
    }
    
    public void setLogin(String login) {
    this.login = login;
    }
    
    public String getPassword() {
    return this.password;
    }
    
    public void setPassword(String password) {
    this.password = password;
    }
    
    public String getRole() {
    return this.role;
    }
    
    public void setRole(String role) {
    this.role = role;
    }
}
