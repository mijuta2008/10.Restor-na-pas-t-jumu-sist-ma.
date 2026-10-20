package pasutijumsrestorana;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */
public class Bill {
    public Double totalpayment; //kopējā samaksa par pasūtīujum
    public Boolean paymentStatus;//apmaksa status(true/false)
    public String date; //datums

    //Aprēķins un atgriež kopējo ŗeķina summu.
    public Double calcultateTotal() {
        return totalpayment;
    }
//@param paymentStatus jaunas apmaksas status(true/false)
    public void changePaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
//veic rēķina procesu.
    public void pay() {
    }
}