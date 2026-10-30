package db;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */
public class DatabaseManager {
    public static void main(String[] args) {
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String URL ="jdbc:derby:database/appdb;create=true";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "dbuser", "dbuser");
    }

    public static void showDatabaseData() {
    String[] tables = {"USERS", "DISHES", "ORDERS","ORDER_DISHES", "RESERVATIONS"};
    
    try (Connection conn = DriverManager.getConnection("jdbc:derby:database/appdb", "dbuser", "dbuser")) {

        for (String table : tables) {
            System.out.println("\n========== " + table + " ==========");

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM " + table)) {

                ResultSetMetaData meta = rs.getMetaData();
                int columns = meta.getColumnCount();

                // Kolonnas nosaukumi
                for (int i = 1; i <= columns; i++) {
                    System.out.print(meta.getColumnName(i) + "\t");
                }
                System.out.println();

                // Tabulas dati
                while (rs.next()) {
                    for (int i = 1; i <= columns; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }

            } catch (SQLException e) {
                System.out.println(
                    "Tabulas kļūda " + table + ": " + e.getMessage()
                );
                e.printStackTrace();
            }
        }

    } catch (SQLException e) {
        System.out.println("Kļūda, veidojot savienojumu ar datubāzi!");
        e.printStackTrace();
    }
    
}
   
    public static void init() throws SQLException {

        try (Connection conn = getConnection()){
            try(Statement st = conn.createStatement()){

            // USERS
            try {
                st.executeUpdate(
                    "CREATE TABLE USERS (" +
                    "user_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "login VARCHAR(100) NOT NULL UNIQUE, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "role VARCHAR(50) NOT NULL)"
                );
            } catch (SQLException e) {
                if ("X0Y32".equals(e.getSQLState())) {
                    System.out.println("USERS table already exists.");
                } else {
                    throw e;
                }
            }

            // DISHES
            try {
                st.executeUpdate(
                    "CREATE TABLE DISHES (" +
                    "dish_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "price DECIMAL(10,2) NOT NULL, " +
                    "description VARCHAR(255) NOT NULL, " +
                    "category VARCHAR(50))"
                );
            } catch (SQLException e) {
                if ("X0Y32".equals(e.getSQLState())) {
                    System.out.println("DISHES table already exists.");
                } else {
                    throw e;
                }
            }

            // ORDERS
            try {
              st.executeUpdate(
                  "CREATE TABLE ORDERS (" +
                  "order_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                  "order_date DATE NOT NULL, " +
                  "status VARCHAR(50) NOT NULL, " +
                  "total_price DECIMAL(10,2) NOT NULL, " +
                  "payment_status BOOLEAN NOT NULL, " +
                  "user_id INT REFERENCES USERS(user_id))"
              );
            } catch (SQLException e) {
                if ("X0Y32".equals(e.getSQLState())) {
                    System.out.println("ORDERS table already exists.");
                } else {
                    throw e;
                }
            } 
                
            // RESERVATIONS
            try {
                st.executeUpdate(
                    "CREATE TABLE RESERVATIONS (" +
                    "reservation_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "reservation_date DATE NOT NULL, " +
                    "reservation_time TIME NOT NULL, " +
                    "status BOOLEAN NOT NULL, " +
                    "user_id INT REFERENCES USERS(user_id))"
                );
            } catch (SQLException e) {
                if ("X0Y32".equals(e.getSQLState())) {
                    System.out.println("RESERVATIONS table already exists.");
                } else {
                    throw e;
                }
            }

            // ORDER_DISHES
            try {
                st.executeUpdate(
                    "CREATE TABLE ORDER_DISHES (" +
                    "order_id INT REFERENCES ORDERS(order_id), " +
                    "dish_id INT REFERENCES DISHES(dish_id), " +
                    "quantity INT NOT NULL, " +
                    "price DECIMAL(10,2) NOT NULL)"
                );
            } catch (SQLException e) {
                if ("X0Y32".equals(e.getSQLState())) {
                    System.out.println("ORDER_DISHES table already exists.");
                } else {
                    throw e;
                }
            }
        }
        insertUsers(conn);
        insertDishes(conn);
        insertORDERS(conn);
        insertRESERVATIONS(conn);
        insertORDER_DISHES(conn);
        

        System.out.println("Database initialized!");

    } catch (SQLException e) {
        System.out.println("Kļūda inicializējot datubāzi: " + e.getMessage());
        e.printStackTrace();
    }

    showDatabaseData();
    }
            
        private static void insertDishes(Connection conn) throws SQLException {
            String checkSql = "SELECT COUNT(*) FROM DISHES";

            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(checkSql)) {

                rs.next();

                if (rs.getInt(1) > 0) {
                    System.out.println("DISHES jau ir aizpildīts!");
                    return;
                }
            }

            String sql = "INSERT INTO DISHES(name, price, description, category) VALUES (?, ?, ?, ?)";

            String[][] dishes = {
                {"Cēzara salāti ar vistu", "8.50", "Svaigas salātu lapas, grilēta vistas fileja, Cēzara mērce, parmezāna siers un grauzdiņi", "Uzkodas"},
                {"Grieķu salāti", "7.00", "Svaigi dārzeņi, feta siers, olīvas un olīveļļa", "Uzkodas"},
                {"Siera plate", "12.00", "Dažādu Latvijas un Eiropas sieru izlase ar medu un riekstiem", "Uzkodas"},
                {"Ķiploku grauzdiņi ar mērci", "4.50", "Tradicionālie rudzu maizes grauzdiņi ar ķiploku un siera krējuma mērci", "Uzkodas"},
                {"Aukstā zupa", "5.50", "Tradicionālā biešu zupa ar kefīru, vārītām olām, gurķiem un zaļumiem", "Zupas"},
                {"Soļanka", "6.50", "Bagātīga gaļas zupa ar marinētiem gurķiem, olīvām un citronu", "Zupas"},
                {"Sēņu krēmzupa", "6.00", "Maiga meža sēņu krēmzupa ar grauzdiņiem", "Zupas"},
                {"Cūkgaļas steiks ar kartupeļiem", "14.50", "Sulīgs cūkgaļas steiks uz grila ar ceptiem kartupeļiem un BBQ mērci", "Pamatēdieni"},
                {"Vistas filejas medaljoni", "11.00", "Vistas fileja siera mērcē ar rīsiem un dārzeņu garnīru", "Pamatēdieni"},
                {"Laša fileja ar citronu mērci", "16.00", "Cepts lasis ar tvaicētiem dārzeņiem un baltvīna mērci", "Pamatēdieni"},
                {"Pasta Carbonara", "9.50", "Klasiskā pasta ar speķi, saldo krējumu, olas dzeltenumu un parmezāna sieru", "Pamatēdieni"},
                {"Liellopa gaļas burgers", "12.50", "Burgeris ar liellopa gaļas kotleti, čederas sieru, frī kartupeļiem un mērci", "Pamatēdieni"},
                {"Cūkgaļas karbonāde French style", "10.00", "Karbonāde ar tomātiem un ceptu sieru, pasniegta ar kartupeļu biezeni", "Pamatēdieni"},
                {"Pildītas pankūkas ar gaļu", "7.50", "Plānās pankūkas pildītas ar maltu gaļu un ceptas zeltainas", "Pamatēdieni"},
                {"Maizes zupa", "4.50", "Tradicionālais latviešu deserts no rudzu maizes, dzērvenēm un putukrējuma", "Deserti"},
                {"Siera kūka Cheesecake", "5.50", "Klasiskā maigā biezpiena kūka ar ogu piedevu", "Deserti"},
                {"Šokolādes fondants", "6.00", "Silta šokolādes kūka ar šķidru vidu un saldējuma bumbiņu", "Deserti"},
                {"Dzērveņu morss", "3.00", "Pašu gatavots atsvaidzinošs dzērveņu dzēriens", "Dzērieni"},
                {"Svaigi spiesta apelsīnu sula", "4.50", "100% dabīga svaigi spiesta apelsīnu sula", "Dzērieni"},
                {"Klasiskā kafija Latte", "3.50", "Aromātiska kafija ar pienu un piena putām", "Dzērieni"}
            };

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (String[] dish : dishes) {
                    ps.setString(1, dish[0]);
                    ps.setDouble(2, Double.parseDouble(dish[1]));
                    ps.setString(3, dish[2]);
                    ps.setString(4, dish[3]);
                    ps.executeUpdate();
                }
            }

            System.out.println("DISHES veiksmīgi pievienoti: " + dishes.length);
            }

            private static void insertUsers(Connection conn) throws SQLException {
                String checkSql = "SELECT COUNT(*) FROM USERS";

                try (Statement st = conn.createStatement();
                     ResultSet rs = st.executeQuery(checkSql)) {

                    rs.next();

                    if (rs.getInt(1) > 0) {
                        System.out.println("USERS jau ir aizpildīts!");
                        return;
                    }
                }
                
                String sql = "INSERT INTO USERS (name, login, password, role) VALUES (?, ?, ?, ?)";

            String[][] users = {
                {"Anna Bragina", "anna@gmail.com", "anna123", "lietotājs"},
                {"Viktorija Balalajeva", "viktorija@gmail.com", "vika123", "lietotājs"},
                {"Evelina Barila", "evelina@gmail.com", "evelina123", "lietotājs"},
                {"Olga Ivanova", "olga@gmail.com", "olga123", "administrators"},
                {"Stanislavs Malīņš", "stas@gmail.com", "stas123", "lietotājs"},
                {"Danils Silovs", "danils@gmail.com", "danil123", "lietotājs"},
                {"Alina Ivanova", "alina@gmail.com", "alina123", "administrators"},
                {"Kirils Krosovskis", "kiril@gmail.com", "kiril123", "lietotājs"},
                {"Devids Ašpids", "devid@gmail.com", "devid123", "lietotājs"},
                {"Vadims Lavrenovs", "vadim@gmail.com", "vadim123", "lietotājs"},
                {"Anastasija Solovjeva", "anastasija@gmail.com", "anastasija123", "lietotājs"},
                {"Roberts Podzis", "robert@gmail.com", "robert123", "lietotājs"},
                {"Vitalijs Krukovskis", "vitalik@gmail.com", "vitalik123", "lietotājs"},
                {"Svetlana Bogoslova", "svetlana@gmail.com", "svetlana123", "administrators"},
                {"Olga Buzova", "olgab@gmail.com", "olga123", "lietotājs"},
                {"Danils Košlačs", "danilsk@gmail.com", "danils123", "lietotājs"},
                {"Pavels Āvele", "pavels@gmail.com", "pavels123", "lietotājs"},
                {"Stanislavs Mihailovs", "stanislavs@gmail.com", "stas123", "lietotājs"},
                {"Ludmila Čipele", "ludmila@gmail.com", "ludmila123", "lietotājs"},
                {"Maksims Plehanovs", "maksims@gmail.com", "max123", "lietotājs"}
            };

            try (Statement check = conn.createStatement();
                 ResultSet rs = check.executeQuery(checkSql)) {

                rs.next();

                if (rs.getInt(1) == 0) {
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        for (String[] user : users) {
                            ps.setString(1, user[0]);
                            ps.setString(2, user[1]);
                            ps.setString(3, user[2]);
                            ps.setString(4, user[3]);
                            ps.executeUpdate();
                        }
                    }

                    System.out.println("Lietotāji veiksmīgi pievienoti!");
                } else {
                    System.out.println("USERS jau ir dati. Izlaižam pievienošanu.");
                }
            }
        }
        
        private static void insertORDERS(Connection conn) throws SQLException {
            String checkSql = "SELECT COUNT(*) FROM ORDERS";

            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(checkSql)) {

                rs.next();

                if (rs.getInt(1) > 0) {
                    System.out.println("ORDERS jau ir aizpildīts!");
                    return;
                }
            }

            String sql = "INSERT INTO ORDERS (order_date, status, total_price, payment_status, user_id) VALUES (?, ?, ?, ?, ?)";

            Object[][] orders = {
                {"2026-10-09", "Pabeigts", 25.50, true, 1},
                {"2026-10-29", "Progresā", 42.00, false, 2},
                {"2026-09-01", "Atcelts", 15.00, true, 3},
                {"2026-10-18", "Progresā", 5.00, true, 4},
                {"2026-10-21", "Progresā", 15.00, false, 5},
                {"2026-10-21", "Pabeigts", 35.00, true, 6},
                {"2026-10-30", "Atcelts", 29.00, false, 7},
                {"2026-11-30", "Atcelts", 29.00, true, 8},
                {"2026-12-25", "Progresā", 29.00, false, 9},
                {"2026-11-10", "Pabeigts", 29.00, true, 10},
                {"2026-10-09", "Pabeigts", 25.50, true, 11},
                {"2026-12-29", "Progresā", 42.00, true, 12},
                {"2026-09-01", "Atcelts", 15.00, false, 13},
                {"2026-10-18", "Progresā", 5.00, false, 14},
                {"2026-11-21", "Progresā", 15.00, true, 15},
                {"2026-10-09", "Pabeigts", 35.00, true, 16},
                {"2026-12-30", "Atcelts", 29.00, true, 17},
                {"2026-11-01", "Atcelts", 29.00, true, 18},
                {"2026-09-25", "Progresā", 29.00, true, 19},
                {"2026-12-05", "Pabeigts", 29.00, false, 20}
            };

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Object[] order : orders) {
                    ps.setDate(1, java.sql.Date.valueOf((String) order[0]));
                    ps.setString(2, (String) order[1]);
                    ps.setDouble(3, (Double) order[2]);
                    ps.setBoolean(4, (Boolean) order[3]);
                    ps.setInt(5, (Integer) order[4]);

                    ps.executeUpdate();
                }
            }

            System.out.println("ORDERS veiksmīgi pievienoti: " + orders.length);
        }
       
        private static void insertORDER_DISHES(Connection conn) throws SQLException {
            String checkSql = "SELECT COUNT(*) FROM ORDER_DISHES";

            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(checkSql)) {

                rs.next();

                if (rs.getInt(1) > 0) {
                    System.out.println("ORDER_DISHES jau ir aizpildīts!");
                    return;
                }
            }

            String sql = "INSERT INTO ORDER_DISHES(order_id, dish_id, quantity, price) VALUES (?, ?, ?, ?)";

            int[][] orderDishes = {
                {1, 1, 5},
                {2, 2, 2},
                {3, 3, 1},
                {4, 4, 1},
                {5, 5, 2},
                {6, 6, 7},
                {7, 7, 5},
                {8, 8, 4},
                {9, 9, 3},
                {10, 10, 4},
                {11, 11, 5},
                {12, 12, 2},
                {13, 13, 1},
                {14, 14, 1},
                {15, 15, 2},
                {16, 16, 4},
                {17, 17, 2},
                {18, 18, 1},
                {19, 19, 7},
                {20, 20, 4}
            };

            double[] prices = {
                25.50, 42.00, 15.00, 5.00, 15.00,
                35.00, 29.00, 29.00, 29.00, 29.00,
                25.50, 42.00, 15.00, 5.00, 15.00,
                35.00, 15.00, 9.00, 49.00, 29.00
            };

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (int i = 0; i < orderDishes.length; i++) {
                    ps.setInt(1, orderDishes[i][0]);
                    ps.setInt(2, orderDishes[i][1]);
                    ps.setInt(3, orderDishes[i][2]);
                    ps.setDouble(4, prices[i]);

                    ps.executeUpdate();
                }
            }

            System.out.println(
                "ORDER_DISHES veiksmīgi pievienoti: " + orderDishes.length
            );
        }
        
        private static void insertRESERVATIONS(Connection conn) throws SQLException {
            String checkSql = "SELECT COUNT(*) FROM RESERVATIONS";

            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(checkSql)) {

                rs.next();

                if (rs.getInt(1) > 0) {
                    System.out.println("RESERVATIONS jau ir aizpildīts!");
                    return;
                }
            }

            String sql = "INSERT INTO RESERVATIONS(reservation_date, reservation_time, status, user_id) VALUES (?, ?, ?, ?)";

            Object[][] reservations = {
                {"2026-10-09", "14:30:00", true, 1},
                {"2026-09-18", "16:00:00", true, 2},
                {"2026-09-18", "20:00:00", true, 3},
                {"2026-09-30", "16:45:00", false, 4},
                {"2026-09-30", "12:05:00", true, 5},
                {"2026-10-01", "19:30:00", true, 6},
                {"2026-10-07", "09:30:00", false, 7},
                {"2026-10-26", "18:50:00", true, 8},
                {"2026-10-29", "17:45:00", true, 9},
                {"2026-10-29", "20:30:00", true, 10},
                {"2026-11-03", "10:30:00", true, 11},
                {"2026-11-10", "16:45:00", false, 12},
                {"2026-11-15", "13:30:00", true, 13},
                {"2026-11-16", "18:45:00", false, 14},
                {"2026-12-16", "20:00:00", false, 15},
                {"2026-12-24", "18:30:00", true, 16},
                {"2026-12-22", "11:30:00", false, 17},
                {"2026-12-24", "10:45:00", false, 18},
                {"2026-12-26", "18:00:00", true, 19},
                {"2026-12-29", "13:30:00", false, 20}
            };

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for (Object[] reservation : reservations) {
                    ps.setDate(1,java.sql.Date.valueOf((String) reservation[0]));
                    ps.setTime(2,java.sql.Time.valueOf((String) reservation[1]));
                    ps.setBoolean(3, (Boolean) reservation[2]);
                    ps.setInt(4, (Integer) reservation[3]);

                    ps.executeUpdate();
                }
            }

            System.out.println(
                "RESERVATIONS veiksmīgi pievienoti: " + reservations.length
            );
        }
    
}