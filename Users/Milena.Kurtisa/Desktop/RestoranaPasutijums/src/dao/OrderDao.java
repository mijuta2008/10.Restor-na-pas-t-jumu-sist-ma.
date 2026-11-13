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

public class OrderDao {
    
    // Jauna pasūtījuma izveide
    
public int createOrder(int userId) throws SQLException {

    String sql = "INSERT INTO ORDERS "
            + "(ORDER_DATE, STATUS, TOTAL_PRICE, PAYMENT_STATUS, USER_ID) "
            + "VALUES (CURRENT_DATE, ?, ?, ?, ?)";

    try (Connection conn = DatabaseManager.getConnection();
         PreparedStatement ps = conn.prepareStatement(
                 sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, "In progress");
        ps.setBigDecimal(2, BigDecimal.ZERO);
        ps.setBoolean(3, false);
        ps.setInt(4, userId);

        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
    }

    throw new SQLException("Neizdevās izveidot pasūtījumu!");
}

    // Ēdiena pievienošana grozam
    public void addDish(int orderId, int dishId) throws SQLException {

        if (orderId <= 0) {
            throw new SQLException("Vispirms jāizveido pasūtījums!");
        }

        try (Connection conn = DatabaseManager.getConnection()) {

            String updateSql =
                    "UPDATE ORDER_DISHES "
                    + "SET QUANTITY = QUANTITY + 1 "
                    + "WHERE ORDER_ID = ? AND DISH_ID = ?";

            int updated;

            try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                ps.setInt(1, orderId);
                ps.setInt(2, dishId);
                updated = ps.executeUpdate();
            }

            if (updated == 0) {

                String insertSql =
                        "INSERT INTO ORDER_DISHES "
                        + "(ORDER_ID, DISH_ID, QUANTITY, PRICE) "
                        + "SELECT ?, DISH_ID, 1, PRICE "
                        + "FROM DISHES WHERE DISH_ID = ?";

                try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                    ps.setInt(1, orderId);
                    ps.setInt(2, dishId);

                    if (ps.executeUpdate() == 0) {
                        throw new SQLException(
                                "Ēdiens ar šo ID nav atrasts!"
                        );
                    }
                }
            }
        }

        updateTotal(orderId);
    }

    // Pasūtījuma kopsummas pārrēķins
    public BigDecimal updateTotal(int orderId) throws SQLException {

        String sql =
            "SELECT COALESCE(SUM(QUANTITY * PRICE), 0) "
            + "FROM ORDER_DISHES WHERE ORDER_ID = ?";

        BigDecimal total;

        try (Connection conn = DatabaseManager.getConnection()) {

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, orderId);

                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    total = rs.getBigDecimal(1);

                    if (total == null) {
                        total = BigDecimal.ZERO;
                    }
                }
            }

            String updateSql =
                "UPDATE ORDERS SET TOTAL_PRICE = ? "
                + "WHERE ORDER_ID = ?";

            try (PreparedStatement ps =
                    conn.prepareStatement(updateSql)) {

                ps.setBigDecimal(1, total);
                ps.setInt(2, orderId);
                ps.executeUpdate();
            }
        }

        return total;
    }
}