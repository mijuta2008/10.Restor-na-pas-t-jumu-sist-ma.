package pasutijumsrestorana;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pasutijumsrestorana.DatabaseManager;
 import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import pasutijumsrestorana.Order;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */
public class OrderDao {

           
    
        private static final String URL =
            "jdbc:derby:database/appdb;create=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "app", "app");
    }

    
    
    private DefaultTableModel model = new DefaultTableModel();
    private int activeRow;

 

public class ReservationDao {

    public void saveOrder(Reservation reservation)
            throws SQLException {

        String sql =
            "INSERT INTO RESERVATIONS " +
            "(reservation_id, reservation_date, reservation_time, status, user_id) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn =
                 DatabaseManager.getConnection();
             PreparedStatement ps =
                 conn.prepareStatement(sql)) {

           
    }
}
}}

    

  
 

