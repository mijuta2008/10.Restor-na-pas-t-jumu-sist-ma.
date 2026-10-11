package pasutijumsrestorana;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Milena.Kurtisa
 */
public class UserDao{
    
    private static final String DB_URL = "jdbc:derby://localhost:1527/Restoranapasutijuma;create=true";
    private static final String DB_USER = "dbuser";
    private static final String DB_PASSWD = "dbuser";

   
     //Reģistrē jaunu lietotāju datu bāzē.
     //@param name Vārds un uzvārds
     //@param login E-pasts
     //@param password Parole
     //@param role Loma(leitotājs vai admins)
    // @return true, ja reģistrācija veiksmīga
    // @throws SQLException ja rodas kļūda ar datu bāzi
     
    public boolean registerUser(String name, String login, String password, String role) throws SQLException {
        String sql = "INSERT INTO APP.users (name, login, password, role) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, login);
            pstmt.setString(3, password);
            pstmt.setString(4, role);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

     // @param login lietotāja e-pasts
     // @param password lietotāja parole
     // @throws SQLException ja rodas kļūda datu bāzes savienojuma.
    public User loginUser(String login, String password) throws SQLException {
        String sql = "SELECT * FROM APP.users WHERE login = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String dbName = rs.getString("name");
                    String dbLogin = rs.getString("login");
                    String dbPass = rs.getString("password");
                    String dbRole = rs.getString("role");
                    
                    return new User(dbName, dbLogin, dbPass, dbRole);
                }
            }
        }
        return null; 
    }
}