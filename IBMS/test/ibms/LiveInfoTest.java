/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibms;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patelr0
 */
public class LiveInfoTest {

    public LiveInfoTest() {
    }

    @Test
    public void testGetDelay() {
        System.out.println("getDelay");
        double delayNum = 0.5;
        String route = "R";
        String busStopSelection = "busStopSelection";
        String expResult = "There has been an accident on route R after busStopSelection.";
        String result = LiveInfo.getDelay(delayNum, route, busStopSelection);
        assertEquals(expResult, result);
        
    }

}