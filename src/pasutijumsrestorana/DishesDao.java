package pasutijumsrestorana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */


public class DishesDao {
    
    private static final String DB_URL = "jdbc:derby://localhost:1527/Restoranapasutijuma;create=true";
    private static final String DB_USER = "dbuser";
    private static final String DB_PASSWD = "dbuser";

public boolean addDish(String name, double price, String description, String category) throws SQLException {
    int nextId = 1;
    String maxIdSql = "SELECT MAX(dish_id) FROM APP.dishes";
    
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
         PreparedStatement pstmtMax = conn.prepareStatement(maxIdSql);
         ResultSet rs = pstmtMax.executeQuery()) {
        if (rs.next()) {
            nextId = rs.getInt(1) + 1; 
        }
    }


    String sql = "INSERT INTO APP.dishes (dish_id, name, price, description, category) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, nextId);      
        pstmt.setString(2, name);
        pstmt.setDouble(3, price);
        pstmt.setString(4, description);
        pstmt.setString(5, category);
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0;
    }

 

}
public boolean updateDish(int dishId, String name, double price, String description, String category) throws SQLException {
    String sql = "UPDATE APP.dishes SET name = ?, price = ?, description = ?, category = ? WHERE dish_id = ?";
    
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, name);
        pstmt.setDouble(2, price);
        pstmt.setString(3, description);
        pstmt.setString(4, category);
        pstmt.setInt(5, dishId);
        
        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0; 
    }
}
   public List<Dishes> getAllDishes() {
    List<Dishes> list = new ArrayList<>();
    String query = "SELECT dish_id, name, description, price, category FROM APP.dishes";
    
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        
        while (rs.next()) {
            int id = rs.getInt("dish_id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            String category = rs.getString("category");
            
            Dishes dish = new Dishes(name, price, description, category);
            dish.setId(id);
            list.add(dish);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
   }

  public List<Dishes> findDish(String nosaukums) throws SQLException {
        List<Dishes> dishesList = new ArrayList<>();
        String sql = "SELECT DISH_ID, NAME, DESCRIPTION, PRICE, CATEGORY "
                   + "FROM APP.DISHES "
                   + "WHERE LOWER(NAME) LIKE ? "
                   + "ORDER BY DISH_ID";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nosaukums.trim().toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("DISH_ID");
                    String name = rs.getString("NAME");
                    String description = rs.getString("DESCRIPTION");
                    double price = rs.getDouble("PRICE");
                    String category = ""; 
                    
                    Dishes dish = new Dishes(name, price, description, category);
                    dish.setId(id);
                    
                    dishesList.add(dish);
                }
            }
        }
        return dishesList;
        }
}

