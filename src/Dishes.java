/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */
public class Dishes {
 
    public String nameDish;
    public Double price;
    public String description;
    public String category;

    public Dishes(String nameDish, Double price, String description, String category) {
        this.nameDish = nameDish;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public String getnameDish() {
        return nameDish;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}