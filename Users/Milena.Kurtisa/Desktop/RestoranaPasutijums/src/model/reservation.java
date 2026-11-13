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
public class reservation {
    private int reservation_id;
    private Date date;
    private String time;
    private boolean status;
    private int user_id;
    
    public int getReservationId() {
    return this.reservation_id;
    }
    
    public void setReservationId(int reservation_id) {
    this.reservation_id = reservation_id;
    }
    
    public Date getDate() {
    return this.date;
    }
    
    public void setDate(Date date) {
    this.date = date;
    }
    
    public String getTime() {
    return this.time;
    }
    
    public void setTime(String time) {
    this.time = time;
    }
    
    public boolean getStatus() {
    return this.status;
    }
    
    public void setStatus(boolean status) {
    this.status = status;
    }
    
    public int getUserId() {
    return this.user_id;
    }
    
    public void setUserId(int user_id) {
    this.user_id = user_id;
    }
}
