/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dao;

import dao.DishesDao;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Milena.Kurtisa
 */
public class DishesDaoTest {
    
    public DishesDaoTest() {
    }

    /**
     * Test of ieladetEdienkarti method, of class DishesDao.
     */
    @Test
    public void testIeladetEdienkarti() throws Exception {
    DishesDao dao = new DishesDao();
    List result = dao.ieladetEdienkarti();
    assertNotNull(result);
}
    

    /**
     * Test of mekletEdienus method, of class DishesDao.
     */
    @Test
    public void testMekletEdienus() throws Exception {
    DishesDao dao = new DishesDao();
    List result = dao.mekletEdienus("");
    assertNotNull(result);
}
    

    /**
     * Test of redigetEdienu method, of class DishesDao.
     */
    @Test
    public void testRedigetEdienu() throws Exception {
    DishesDao dao = new DishesDao();
    List result = dao.mekletEdienus("Pizza");

    assertNotNull(result);
}
    }
    

