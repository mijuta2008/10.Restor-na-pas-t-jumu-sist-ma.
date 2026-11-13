/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Viktorija.Balalajeva
 */
public class order_dish {
    private int order_id;
    private int dish_id;
    private int quantity;
    private float price;
    
    public int getOrderId() {
    return this.order_id;
    }
    
    public void setOrderId(int order_id) {
    this.order_id = order_id;
    }
    
    public int getDishId() {
    return this.dish_id;
    }
    
    public void setDishId(int dish_id) {
    this.dish_id = dish_id;
    }
    
    public int getQuantity() {
    return this.quantity;
    }
    
    public void setQuantity(int quantity) {
    this.quantity = quantity;
    }
    
    public float getPrice() {
    return this.price;
    }
    
    public void setPrice(float price) {
    this.price = price;
    }
    
}
