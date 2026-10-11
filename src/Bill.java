/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */
public class Bill {
    public Double totalpayment;
    public Boolean paymentStatus;
    public String date;

    public Double calcultateTotal() {
        return totalpayment;
    }

    public void changePaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void pay() {
    }
}