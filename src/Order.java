/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Date;

/**
 *
 * @author Milena.Kurtisa
 */
import java.util.Date;

public class Order {
    public Date date;
    public Boolean status;
    public Double totalPrice;

    public void addDish(String nameDish, Double price, String description) {
    }

    public Double calculateResult() {
        return totalPrice;
    }

    public void changeStatus(Boolean status) {
        this.status = status;
    }
}