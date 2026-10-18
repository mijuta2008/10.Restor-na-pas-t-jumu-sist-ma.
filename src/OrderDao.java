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

    private DefaultTableModel model = new DefaultTableModel();
    private int activeRow;

 
    public void saveOrder(Order order) {
    }

    public List<Order> getOrdersByUser(String username) {
        List<Order> orders = new ArrayList<>();
        return orders;
    }

 
}
