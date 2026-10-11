/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */
public class Admin extends User {
    private String password;

    public Admin(String name, String login, String password) {
        super(name, login, password);
    }

    public void addDish(String nameDish, Double price, String description) {
    }

    public void findDish(String nameDish) {
    }

    public void editDish(String nameDish, Double price, String description) {
    }

    public void viewOrders(String username) {
    }

    @Override
    public boolean enter(String login, String password) {
        return super.enter(login, password);
    }
}



