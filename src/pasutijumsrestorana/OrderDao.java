package pasutijumsrestorana;

import java.sql.*;
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
public class OrderDao {
    
    
    static final String DB_URL = "jdbc:derby://localhost:1527/Restoranapasutijuma;create=true";
    static final String DB_USER = "dbuser";
    static final String DB_PASSWD = "dbuser";

//Saglabā jaunu pasūtījumu datu bāzē.
// @param order pasūtījuma objekts
   public void saveOrder(Order order) {
        String query = "INSERT INTO APP.orders (date, status, total_price, payment_status, user_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, order.getDate().toString()); 
            pstmt.setBoolean(2, order.isStatus());
            pstmt.setDouble(3, order.getTotalPrice());
            pstmt.setBoolean(4, false); 
            pstmt.setInt(5, 1);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }
//Iegūst konkrētā lietotāja pasūtījumus    
//@param username lietotājvārds
    public String getOrdersByUser(String username) {
        return null;
    }

public List<Dishes> getDishesForOrder(int orderId) {
    List<Dishes> list = new ArrayList<>();
    String query = "SELECT d.dish_id, d.name, d.description, od.price, od.quantity " +
                   "FROM APP.order_dishes od " +
                   "JOIN APP.dishes d ON od.dish_id = d.dish_id " +
                   "WHERE od.order_id = ?";
                   
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setInt(1, orderId);
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("dish_id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                double price = rs.getDouble("price");
                
                Dishes dish = new Dishes(name, price, desc, "");
                dish.setId(id);
                list.add(dish);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}}