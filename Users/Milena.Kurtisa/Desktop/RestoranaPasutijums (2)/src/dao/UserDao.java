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
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    public int registerUser(String name, String login, String password) throws SQLException {

            // Pārbauda, vai e-pasts jau ir reģistrēts
            String checkSql = "SELECT USER_ID FROM USERS WHERE LOGIN = ?";

            try (Connection conn = DatabaseManager.getConnection()) {
                try(PreparedStatement check = conn.prepareStatement(checkSql)){
                   check.setString(1, login);
                   try (ResultSet rs = check.executeQuery()) {
                    if (rs.next()) {
                        throw new SQLException("Šis e-pasts jau ir reģistrēts!");
                    }
                }
            }

            // Saglabā lietotāju
            String insertSql = "INSERT INTO USERS (NAME, LOGIN, PASSWORD, ROLE)VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(insertSql,Statement.RETURN_GENERATED_KEYS)){

                ps.setString(1, name);
                ps.setString(2, login);
                ps.setString(3, password);
                ps.setString(4, "lietotajs");

                ps.executeUpdate();
                
                try(ResultSet rs = ps.getGeneratedKeys()){
                    if(rs.next()){
                        return rs.getInt(1);
                    }
                }
            }
        }
        throw new SQLException("Neizdevās reģistrēt lietotāju!");
    }
}