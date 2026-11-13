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
import java.sql.Statement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DishesDao {
    
    // Ielādē visus ēdienus no datubāzes
    public List<Object[]> downloadDishMenu() throws SQLException {

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
    
    public List<Object[]> searchDishes(String nosaukums) throws SQLException {
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
    //Administratora funkcija: rediget ēdienu
    public void editDishs(int dishId,String name,String description,BigDecimal price) throws SQLException {

        String sql = "UPDATE DISHES SET NAME = ?, DESCRIPTION = ?, PRICE = ? WHERE DISH_ID = ?";

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
    
    //Administratora funkcija: pievienot jauno ēdienu
    public void addNewDish(String name, BigDecimal price, String description) throws SQLException {

    String sql = "INSERT INTO DISHES (NAME, PRICE, DESCRIPTION) VALUES (?, ?, ?)";

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, name);
        ps.setBigDecimal(2, price);
        ps.setString(3, description);

        int rows = ps.executeUpdate();

        if (rows > 0) {

            // Iegūstiet ģenerēto ID
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys != null && generatedKeys.next()) {
                    long newId = generatedKeys.getLong(1);
                    System.out.println("Jaunais ēdiena ID: " + newId);
                } else {
                    System.out.println(
                        "Ēdiens pievienots, bet ID neizdevās iegūt."
                    );
                }
            }
        }
    }
}
    //Administratora funkcija: delet ēdienu
    public void deletDish(int dishId) throws SQLException {
        String checkSql = "SELECT COUNT(*) FROM ORDER_DISHES WHERE DISH_ID = ?";
        String deleteSql = "DELETE FROM DISHES WHERE DISH_ID = ?";

        try (Connection conn = DatabaseManager.getConnection()) {

            // Pārbaudiet, vai ēdiens tiek izmantots kādā no pasūtījumiem
            try (PreparedStatement check = conn.prepareStatement(checkSql)) {
                check.setInt(1, dishId);

                try (ResultSet rs = check.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        throw new SQLException(
                            "Šo ēdienu nevar dzēst, jo tas ir iekļauts pasūtījumos!");
                    }
                }
            }

            // Ja trauks netiek izmantots, mēs to aizvācam
            try (PreparedStatement ps = conn.prepareStatement(deleteSql)) {
                ps.setInt(1, dishId);

                int deleted = ps.executeUpdate();

                if (deleted == 0) {throw new SQLException("Ēdiens ar šo ID nav atrasts!");
                }
            }
        }
    }
}
        
    
