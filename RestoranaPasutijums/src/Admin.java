/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Milena.Kurtisa
 */
public class Admin extends User{
    private String name;
    private String login;
    private String password;

    public Admin(String name, String login, String password) {
        super(name, login, password);
    }
    
    @Override
        public boolean enter(String login, String password){ 
return true; 
}
    private boolean User(String login, String password){
        return false;
    }
private String addDish(String name, Double price,String description)
{
        return null;
}
public String findDish(String dish)
{
        return null;
}
public String editDish(String name,Double price, String description ){
    return null;
}
public String viewOrders(String username)
{
 return null;
 
}





}