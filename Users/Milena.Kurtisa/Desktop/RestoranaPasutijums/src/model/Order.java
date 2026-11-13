/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Viktorija.Balalajeva
 */
public class Order {
    private int order_id;
    private Date date;
    private String status;
    private float total_price;
    private boolean payment_status;
    
    public int getOrderId() {
    return this.order_id;
    }
    
    public void setOrderId(int order_id) {
    this.order_id = order_id;
    }
    
    public Date getDate() {
    return this.date;
    }
    
    public void setDate(Date date) {
    this.date = date;
    }
    
    public String getStatus() {
    return this.status;
    }
    
    public void setStatus(String status) {
    this.status = status;
    }
    
    public float getTotalPrice() {
    return this.total_price;
    }
    
    public void setTotalPrice(float total_price) {
    this.total_price = total_price;
    }
    
    public boolean getPaymentStatus() {
    return this.payment_status;
    }
    
    public void setPaymentStatus(boolean payment_status) {
    this.payment_status = payment_status;
    }
}
