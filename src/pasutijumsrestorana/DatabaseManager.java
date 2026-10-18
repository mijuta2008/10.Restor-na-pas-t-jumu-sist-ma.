package pasutijumsrestorana;

import java.sql.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */


public class DatabaseManager {

    private static final String URL =
            "jdbc:derby:database/appdb;create=true";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "app", "app");
    }

    public static void init() {

        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {

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
            } catch (SQLException ignored) {}

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
} catch (SQLException ignored) {
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
} catch (SQLException ignored) {
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
            } catch (SQLException ignored) {}

            // ORDER_DISHES
            try {
                st.executeUpdate(
                    "CREATE TABLE ORDER_DISHES (" +
                    "order_id INT REFERENCES ORDERS(order_id), " +
                    "dish_id INT REFERENCES DISHES(dish_id), " +
                    "quantity INT NOT NULL, " +
                    "price DECIMAL(10,2) NOT NULL)"
                );
            } catch (SQLException ignored) {}

            try {
    st.executeUpdate(
        "INSERT INTO DISHES (name, price, description, category) VALUES " +
        "('Cēzara salāti ar vistu',8.50,'Svaigas salātu lapas, grilēta vistas fileja, Cēzara mērce, parmezāna siers un grauzdiņi','Uzkodas')," +
        "('Grieķu salāti',7.00,'Svaigi dārzeņi, feta siers, olīvas un olīveļļa','Uzkodas')," +
        "('Siera plate',12.00,'Dažādu Latvijas un Eiropas sieru izlase ar medu un riekstiem','Uzkodas')," +
        "('Ķiploku grauzdiņi ar mērci',4.50,'Tradicionālie rudzu maizes grauzdiņi ar ķiploku un siera krējuma mērci','Uzkodas')," +
        "('Aukstā zupa',5.50,'Tradicionālā biešu zupa ar kefīru, vārītām olām, gurķiem un zaļumiem','Zupas')," +
        "('Soļanka',6.50,'Bagātīga gaļas zupa ar marinētiem gurķiem, olīvām un citronu','Zupas')," +
        "('Sēņu krēmzupa',6.00,'Maiga meža sēņu krēmzupa ar grauzdiņiem','Zupas')," +
        "('Cūkgaļas steiks ar kartupeļiem',14.50,'Sulīgs cūkgaļas steiks uz grila ar ceptiem kartupeļiem un BBQ mērci','Pamatēdieni')," +
        "('Vistas filejas medaljoni',11.00,'Vistas fileja siera mērcē ar rīsiem un dārzeņu garnīru','Pamatēdieni')," +
        "('Laša fileja ar citronu mērci',16.00,'Cepts lasis ar tvaicētiem dārzeņiem un baltvīna mērci','Pamatēdieni')," +
        "('Pasta Carbonara',9.50,'Klasiskā pasta ar speķi, saldo krējumu, olas dzeltenumu un parmezāna sieru','Pamatēdieni')," +
        "('Liellopa gaļas burgers',12.50,'Burgeris ar liellopa gaļas kotleti, čederas sieru, frī kartupeļiem un mērci','Pamatēdieni')," +
        "('Cūkgaļas karbonāde French style',10.00,'Karbonāde ar tomātiem un ceptu sieru, pasniegta ar kartupeļu biezeni','Pamatēdieni')," +
        "('Pildītas pankūkas ar gaļu',7.50,'Plānās pankūkas pildītas ar maltu gaļu un ceptas zeltainas','Pamatēdieni')," +
        "('Maizes zupa',4.50,'Tradicionālais latviešu deserts no rudzu maizes, dzērvenēm un putukrējuma','Deserti')," +
        "('Siera kūka Cheesecake',5.50,'Klasiskā maigā biezpiena kūka ar ogu piedevu','Deserti')," +
        "('Šokolādes fondants',6.00,'Silta šokolādes kūka ar šķidru vidu un saldējuma bumbiņu','Deserti')," +
        "('Dzērveņu morss',3.00,'Pašu gatavots atsvaidzinošs dzērveņu dzēriens','Dzērieni')," +
        "('Svaigi spiesta apelsīnu sula',4.50,'100% dabīga svaigi spiesta apelsīnu sula','Dzērieni')," +
        "('Klasiskā kafija Latte',3.50,'Aromātiska kafija ar pienu un piena putām','Dzērieni')"
    );
} catch (SQLException ignored) {}
            
            
            
             try {
    st.executeUpdate(
        "INSERT INTO USERS ( name, login, password, role) VALUES " +
        "('Anna Bragina','anna@gmail.com','anna123','lietotājs')," +
        "('Viktorija Balalajeva','viktorija@gmail.com','vika123','lietotājs')," +
        "('Evelina Barila','evelina@gmail.com','evelina123','lietotājs')," +
        "('Olga Ivanova','olga@gmail.com','olga123','administrators')," +
        "('Stanislavs Malīņš','stas@gmail.com','stas123','lietotājs')," +
        "('Danils Silovs','danils@gmail.com','danil123','lietotājs')," +
        "('Alina Ivanova','alina@gmail.com','alina123','administrators')," +
        "('Kirils Krosovskis','kiril@gmail.com','kiril123','lietotājs')," +
        "('Devids Ašpids','devid@gmail.com','devid123','lietotājs')," +
        "('Vadims Lavrenovs','vadim@gmail.com','vadim123','lietotājs')," +
        "('Anastasija Solovjeva','anastasija@gmail.com','anastasija123','lietotājs')," +
        "('Roberts Podzis','robert@gmail.com','robert123','lietotājs')," +
        "('Vitalijs Krukovskis','vitalik@gmail.com','vitalik123','lietotājs')," +
        "('Svetlana Bogoslova','svetlana@gmail.com','svetlana123','administrators')," +
        "('Olga Buzova','olgab@gmail.com','olga123','lietotājs')," +
        "('Danils Košlačs','danilsk@gmail.com','danils123','lietotājs')," +
        "('Pavels Āvele','pavels@gmail.com','pavels123','lietotājs')," +
        "('Stanislavs Mihailovs','stanislavs@gmail.com','stas123','lietotājs')," +
        "('Ludmila Čipele','ludmila@gmail.com','ludmila123','lietotājs')," +
        "('Maksims Plehanovs','maksims@gmail.com','max123','lietotājs')"
    );

} catch (SQLException ignored) {}
              try {
             st.executeUpdate(
    "INSERT INTO ORDERS ( order_date, status, total_price, payment_status, user_id) VALUES " +
    "( DATE('2026-10-09'), 'Pabeigts', 25.50, TRUE, 1)," +
    "( DATE('2026-10-29'), 'Progresā', 42.00, FALSE, 2)," +
    "( DATE('2026-09-01'), 'Atcelts', 15.00, TRUE, 3)," +
    "( DATE('2026-10-18'), 'Progresā', 5.00, TRUE, 4)," +
    "( DATE('2026-10-21'), 'Progresā', 15.00, FALSE, 5)," +
    "( DATE('2026-10-21'), 'Pabeigts', 35.00, TRUE, 6)," +
    "( DATE('2026-10-30'), 'Atcelts', 29.00, FALSE, 7)," +
    "( DATE('2026-11-30'), 'Atcelts', 29.00, TRUE, 8)," +
    "( DATE('2026-12-25'), 'Progresā', 29.00, FALSE, 9)," +
    "( DATE('2026-11-10'), 'Pabeigts', 29.00, TRUE, 10)," +
    "( DATE('2026-10-09'), 'Pabeigts', 25.50, TRUE, 11)," +
    "( DATE('2026-12-29'), 'Progresā', 42.00, TRUE, 12)," +
    "( DATE('2026-09-01'), 'Atcelts', 15.00, FALSE, 13)," +
    "( DATE('2026-10-18'), 'Progresā', 5.00, FALSE, 14)," +
    "( DATE('2026-11-21'), 'Progresā', 15.00, TRUE, 15)," +
    "( DATE('2026-10-09'), 'Pabeigts', 35.00, TRUE, 16)," +
    "(DATE('2026-12-30'), 'Atcelts', 29.00, TRUE, 17)," +
    "( DATE('2026-11-01'), 'Atcelts', 29.00, TRUE, 18)," +
    "(DATE('2026-09-25'), 'Progresā', 29.00, TRUE, 19)," +
    "(DATE('2026-12-05'), 'Pabeigts', 29.00, FALSE, 20)"
);
    } catch (SQLException ignored) {}
         try {
        st.executeUpdate(
    "INSERT INTO ORDER_DISHES (order_id, dish_id, quantity, price) VALUES " +
    "(1,1,5,25.50)," +
    "(2,2,2,42.00)," +
    "(3,3,1,15.00)," +
    "(4,4,1,5.00)," +
    "(5,5,2,15.00)," +
    "(6,6,7,35.00)," +
    "(7,7,5,29.00)," +
    "(8,8,4,29.00)," +
    "(9,9,3,29.00)," +
    "(10,10,4,29.00)," +
    "(11,11,5,25.50)," +
    "(12,12,2,42.00)," +
    "(13,13,1,15.00)," +
    "(14,14,1,5.00)," +
    "(15,15,2,15.00)," +
    "(16,16,4,35.00)," +
    "(17,17,2,15.00)," +
    "(18,18,1,9.00)," +
    "(19,19,7,49.00)," +
    "(20,20,4,29.00)"
);
    } catch (SQLException ignored) {}
         
               try {
         st.executeUpdate(
    "INSERT INTO RESERVATIONS ( reservation_date, reservation_time, status, user_id) VALUES " +
    "( DATE('2026-10-09'), TIME('14:30:00'), TRUE, 1)," +
    "( DATE('2026-09-18'), TIME('16:00:00'), TRUE, 2)," +
    "( DATE('2026-09-18'), TIME('20:00:00'), TRUE, 3)," +
    "( DATE('2026-09-30'), TIME('16:45:00'), FALSE, 4)," +
    "(DATE('2026-09-30'), TIME('12:05:00'), TRUE, 5)," +
    "(DATE('2026-10-01'), TIME('19:30:00'), TRUE, 6)," +
    "( DATE('2026-10-07'), TIME('09:30:00'), FALSE, 7)," +
    "( DATE('2026-10-26'), TIME('18:50:00'), TRUE, 8)," +
    "( DATE('2026-10-29'), TIME('17:45:00'), TRUE, 9)," +
    "( DATE('2026-10-29'), TIME('20:30:00'), TRUE, 10)," +
    "( DATE('2026-11-03'), TIME('10:30:00'), TRUE, 11)," +
    "( DATE('2026-11-10'), TIME('16:45:00'), FALSE, 12)," +
    "( DATE('2026-11-15'), TIME('13:30:00'), TRUE, 13)," +
    "( DATE('2026-11-16'), TIME('18:45:00'), FALSE, 14)," +
    "(DATE('2026-12-16'), TIME('20:00:00'), FALSE, 15)," +
    "( DATE('2026-12-24'), TIME('18:30:00'), TRUE, 16)," +
    "( DATE('2026-12-22'), TIME('11:30:00'), FALSE, 17)," +
    "( DATE('2026-12-24'), TIME('10:45:00'), FALSE, 18)," +
    "( DATE('2026-12-26'), TIME('18:00:00'), TRUE, 19)," +
    "(DATE('2026-12-29'), TIME('13:30:00'), FALSE, 20)"
);
            } catch (SQLException ignored) {}
            
            System.out.println("Database initialized!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (
    Connection con = DatabaseManager.getConnection();
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM APP.DISHES")
) {

    while (rs.next()) {
        System.out.println(
            rs.getInt("DISH_ID") + " | " +
            rs.getString("NAME") + " | " +
            rs.getDouble("PRICE") + " | " +
            rs.getString("DESCRIPTION") + " | " +
            rs.getString("CATEGORY")
        );
    }

} catch (SQLException e) {
    e.printStackTrace();
}

    

           try (
    Connection con = DatabaseManager.getConnection();
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM APP.USERS")
) {

    while (rs.next()) {
        System.out.println(
            rs.getInt("USER_ID") + " | " +
            rs.getString("NAME") + " | " +
            rs.getString("LOGIN") + " | " +
            rs.getString("PASSWORD") + " | " +
            rs.getString("ROLE")
        );
    }

} catch (SQLException e) {
    e.printStackTrace();
}

    
      try (
    Connection con = DatabaseManager.getConnection();
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM APP.RESERVATIONS")
) {

    while (rs.next()) {
        System.out.println(
            rs.getInt("reservation_id") + " | " +
            rs.getString("reservation_date") + " | " +
            rs.getString("reservation_time") + " | " +
            rs.getString("status") + " | " +
            rs.getString("user_id")
        );
    }

} catch (SQLException e) {
    e.printStackTrace();
}}}

