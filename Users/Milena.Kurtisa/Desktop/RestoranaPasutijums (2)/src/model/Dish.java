/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Viktorija.Balalajeva
 */
public class Dish {
    private int dish_id;
    private String name;
    private float price;
    private String description;
    private String category;
    
    public int getDishId() {
    return this.dish_id;
    }
    
    public void setDishId(int dish_id) {
    this.dish_id = dish_id;
    }
    
    public String getName() {
    return this.name;
    }
    
    public void setName(String name) {
    this.name = name;
    }
    
    public float getPrice() {
    return this.price;
    }
    
    public void setPrice(float price) {
    this.price = price;
    }
    
    public String getDescription() {
    return this.description;
    }
    
    public void setDescription(String description) {
    this.description = description;
    }
    
    public String getCategory() {
    return this.category;
    }
    
    public void setCategory(String category) {
    this.category = category;
    }
}
