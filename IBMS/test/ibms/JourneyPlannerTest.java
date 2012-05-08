/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibms;

//import java.util.ArrayList;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patelr0
 */
public class JourneyPlannerTest {

    public JourneyPlannerTest() {
    }

   
    /**
     * Test of sameRoutesInBusStop method, of class JourneyPlanner.
     */
    @Test
    public void testAreaAndBusStopNotSame() {
        System.out.println("testBusStopNotSame");
        String fromArea = "Marple";
        String fromStop = "Norfolk Arms";
        String toArea = "Marple";
        String toStop = "Norfolk Arms";
        String result = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        assertEquals("The origin and destination information cannot be the same", result);

    }
    
    @Test
    public void testThereIsNoNameClash() {
        System.out.println("testThereIsNoNameClash");
        String fromArea = "New Mills";
        String fromStop = "Bus Station";
        String toArea = "StockPort";
        String toStop = "Bus Station";
        String result = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        assertEquals("Origin: ", result);

    }

    /**
     * Test of getRoutes method, of class JourneyPlanner.
     
    @Test
    public void testGetRoutes() {
        System.out.println("getRoutes");
        String fromArea = "";
        String fromStop = "";
        String toArea = "";
        String toStop = "";
        String expResult = "";
        String result = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class JourneyPlanner.
     
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        JourneyPlanner.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    } */

}