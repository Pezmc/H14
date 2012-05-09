/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibms;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patelr0
 */
public class LiveInfoTest {

    public LiveInfoTest() {
    }

    public int route = 68;
    public String busStopSelection = "busStopSelection";

    @Test
    public void testForAccident() {
        double delayNum = 0.5;
        String expResult = "There has been an accident on route 68 after \nbusStopSelection.";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCyclistCrash() {
        double delayNum = 1.5;
        String expResult = "There has been a crash involving a cyclist on the 68 route.";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testPlaneCrash() {
        double delayNum = 2.5;
        String expResult = "A plane has hit the bus! ";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);

    }
    @Test
    public void testBrokenDown() {
        double delayNum = 3.5;
        String expResult = "The bus that was supposed to be at this stop has \nbroken down.";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);

    }

    @Test
    public void testCowDisease() {
        double delayNum = 4.5;
        String expResult = "A herd of cows has blocked the road!";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);

    }
    @Test
    public void testDriverIsLate() {
        double delayNum = 5.5;
        String expResult = "The bus driver is running late!";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);

    }
    @Test
    public void testFellApart() {
        double delayNum = 6.5;
        String expResult = "A new bus is being driven to collect you as the old \nbus fell apart.";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);

    }

    @Test
    public void testBrokenDownBus() {
        double delayNum = 7.5;
        String expResult = "The bus that was supposed to be at this stop has \nbroken down.";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);

    }

    @Test
    public void testAccidentAfter() {
        double delayNum = 8.5;
        String expResult = "There has been an accident on route 68 after \nbusStopSelection. ";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);

    }
}