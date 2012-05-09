
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

    public double probability = 0.5;
    public int [] time = {1, 4, 0, 0};
    public int routeNo = 66;
    public String busStopSelectionName = "Offerton Fold";

    // Time is 1400
    @Test
    public void testCancellation() {
        probability = 0.5;
        routeNo = 65;
        busStopSelectionName = "Offerton Fold";
        String result = LiveInfo.cancellation(probability, time, routeNo, busStopSelectionName);

        // this message is okay even if it isn't the same, just testing an error
        assertEquals("There has been an accident on route 66 after Offerton Fold. Which has led to",
                      result);
    }

    @Test
    public void testNoCancellation() {
        String result2 = LiveInfo.noCancellation(probability, time, routeNo, busStopSelectionName);
        
        assertEquals("There is no delay on route 66.\nThe next available bus at - Offerton Fold \nwill arrive at normal time.", result2);
    }

    @Test
    public void testInfoNotNull() {
        String result3 = LiveInfo.cancellation(probability, time, routeNo, busStopSelectionName);
        assertTrue(result3 != null);

    }
   
}