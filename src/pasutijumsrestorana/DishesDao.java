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
    
    private static final String DB_URL = "jdbc:derby:database/appdb;create=true";
    private static final String DB_USER = "dbuser";
    private static final String DB_PASSWD = "dbuser";

public boolean addDish(String name, double price, String description, String category) throws SQLException {
    String sql =
        "INSERT INTO APP.DISHES (name, price, description, category) "
      + "VALUES (?, ?, ?, ?)";

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {      
        
        pstmt.setString(1, name);
        pstmt.setDouble(2, price);
        pstmt.setString(3, description);
        pstmt.setString(4, category);
        
        
        int rowsAffected = pstmt.executeUpdate();

        return rowsAffected > 0;
    }
}
public boolean updateDish(int dishId, String name, double price, String description, String category) throws SQLException {
        String sql = "UPDATE APP.DISHES SET name = ?, description = ?, price = ?, category = ? WHERE dish_id = ?"; 

        try (Connection conn = DatabaseManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) { 

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setString(4, category);
            ps.setInt(5, dishId); 

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; 
        }
    }

public List<Dishes> getAllDishes() {
    List<Dishes> list = new ArrayList<>();
    String query = "SELECT dish_id, name, description, price, category FROM APP.dishes";

    try (Connection conn = DriverManager.getConnection(DB_URL, "app", "app");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            int id = rs.getInt("dish_id");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            String description = rs.getString("description");
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

}