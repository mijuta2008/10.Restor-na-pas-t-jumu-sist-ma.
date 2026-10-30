/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dao;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena.Kurtisa
 */
public class UserDaoTest {
    
    public UserDaoTest() {
    }

    /**
     * Test of registerUser method, of class UserDao.
     */
    @Test
    public void testRegisterUser() throws Exception {

    UserDao dao = new UserDao();

    int result = dao.registerUser(
            "Anna",
            "anna123",
            "password123");

    assertTrue(result > 0);
}
    }
    

