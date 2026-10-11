package pasutijumsrestorana;

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
    
//Izveido jaunu ēdiena objektu ar datiem
    // @param nameDish ēdiena nosaukums
    // @param price ēdiena cena eiro
    // @param description ēdiena apraksts
    
    public void addDish(String nameDish, Double price, String description) {
    }
//@return aprēķina un atgriež pasūtījuma kopējo rezultātu
    public Double calculateResult() {
        return totalPrice;
    }
    
    //nomaina pasūtījuma statusu uz jaunu norādīto vērtību.
// @param status jaunais pasūtījuma statuss (false/true)
    public void changeStatus(Boolean status) {
        this.status = status;
    }


    public Order() {
        this.date = new Date();
        this.status = false;
        this.totalPrice = 0.0;
    }

    public Date getDate() {
        return date;
    }

    public boolean isStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addDish(String nameDish, double price, String description) {
        this.totalPrice += price;
    }
}

 