package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Viktorija.Balalajeva
 */

import db.DatabaseManager;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishesDao {
    
    // Ielādē visus ēdienus no datubāzes
    public List<Object[]> ieladetEdienkarti() throws SQLException {

        List<Object[]> edieni = new ArrayList<>();

        String sql =
            "SELECT DISH_ID, NAME, DESCRIPTION, PRICE "
            + "FROM DISHES ORDER BY DISH_ID";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                edieni.add(new Object[]{
                    rs.getInt("DISH_ID"),
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    rs.getBigDecimal("PRICE")
                });
            }
        }catch (SQLException e) {
        
        System.err.println("SQL Error: " + e.getMessage());
        throw e; 
        }
        return edieni;
    }
    
    public List<Object[]> mekletEdienus(String nosaukums) throws SQLException {
        List<Object[]> edieni = new ArrayList<>();

        String sql = "SELECT DISH_ID, NAME, DESCRIPTION, PRICE "
                   + "FROM DISHES "
                   + "WHERE LOWER(NAME) LIKE ? "
                   + "ORDER BY DISH_ID";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nosaukums.trim().toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    edieni.add(new Object[]{
                        rs.getInt("DISH_ID"),
                        rs.getString("NAME"),
                        rs.getString("DESCRIPTION"),
                        rs.getBigDecimal("PRICE")
                    });
                }
            }
        }

        return edieni;
    }
    public void redigetEdienu(
            int dishId,
            String name,
            String description,
            BigDecimal price) throws SQLException {

        String sql = "UPDATE DISHES "
                   + "SET NAME = ?, DESCRIPTION = ?, PRICE = ? "
                   + "WHERE DISH_ID = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setBigDecimal(3, price);
            ps.setInt(4, dishId);

            int updated = ps.executeUpdate();

            if (updated == 0) {
                throw new SQLException(
                    "Ēdiens ar šo ID nav atrasts!"
                );
            }
        }
    }
}