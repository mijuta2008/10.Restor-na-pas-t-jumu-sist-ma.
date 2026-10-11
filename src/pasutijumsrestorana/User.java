package pasutijumsrestorana;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */


public class User {
    public String name;
    public String login;
    private String password;
      String role;
    
 //@param name lietotāja vārds
 // @param login lietotāja e-pasts
 // @param password lietotāja parole
  // @param role loma(lietotājs vai admins)
    
   public User(String name, String login, String password, String role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }
    
//@param login ievadītais e-pasts
// @param password ievadītā parole
// @return true, ja dati sakrīt, pretējā gadījumā false
    
    public boolean enter(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    public String getName() { return name; }
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}