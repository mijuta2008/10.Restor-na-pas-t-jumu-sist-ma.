package pasutijumsrestorana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Milena.Kurtisa
 */
public class Admin extends User {
    private static final String DB_URL = "jdbc:derby://localhost:1527/Restoranapasutijuma;create=true";
    private static final String DB_USER = "dbuser";
    private static final String DB_PASSWD = "dbuser";
    
     //@param name administratora vārds
     //@param login administratora e-pasts
     // @param password administratora parole
     
    public Admin(String name, String login, String password) {
        super(name, login, password, "administrators"); 
    }

    
     // @param nameDish meklējamā ēdiena nosaukums
     //@param price ēdiena cena
     //@param description ēdiena apraksts
     
    public boolean addDish(String nameDish, double price, String description, String category) {
        DishesDao dishesDao = new DishesDao();
        try {
            return dishesDao.addDish(nameDish, price, description, category);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
      //@param nameDish meklējamā ēdiena nosaukums
     
  public List<Dishes> findDish(String nosaukums) throws SQLException {
        List<Dishes> dishesList = new ArrayList<>();
        String sql = "SELECT DISH_ID, NAME, DESCRIPTION, PRICE, CATEGORY "
                   + "FROM APP.DISHES "
                   + "WHERE LOWER(NAME) LIKE ? "
                   + "ORDER BY DISH_ID";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nosaukums.trim().toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("DISH_ID");
                    String name = rs.getString("NAME");
                    String description = rs.getString("DESCRIPTION");
                    double price = rs.getDouble("PRICE");
                    String category = ""; 
                    
                    Dishes dish = new Dishes(name, price, description, category);
                    dish.setId(id);
                    
                    dishesList.add(dish);
                }
            }
        }
        return dishesList;
    }


    
    // @param nameDish ēdiena nosaukums
     // @param price jaunā ēdiena cena
     //@param description jaunais ēdiena apraksts
     
public boolean editDish(int dishId, String name, double price, String description, String category) {
    DishesDao dishesDao = new DishesDao();
    try {
        return dishesDao.updateDish(dishId, name, price, description, category);
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
     // @param username lietotājvārds
     
    public void viewOrders(String username) {
    }
      
    @Override
    public boolean enter(String login, String password) {
        return super.enter(login, password) && this.role.equals("administrators");
    }
}