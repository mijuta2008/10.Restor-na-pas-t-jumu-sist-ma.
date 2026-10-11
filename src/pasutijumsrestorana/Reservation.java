package pasutijumsrestorana;


import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */
import java.util.Date;

public class Reservation {
    public Date date;
    public String guess; 
    public String time;
    private Boolean status;
    
    
//Atceļ esošo rezervāciju, pamatojoties uz datumu, viesi un laiku.
     // @param date rezervācijas datums, ko nepieciešams atcelt
     // @param guess viesa vārds vai identifikators
     // @param time rezervācijas laiks
    
    public void cancelReservation(String date, String guess, String time) {
    }
//Izveido jaunu rezervāciju sistēmā.
     
    public void createReservation() {
    }
 //pārbauda un atgriež pašreizējo rezervācijas statusu
// @return rezervācijas statuss (true.false)
    private Boolean checkStatus() {
        return status;
    }
}