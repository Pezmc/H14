/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibms;

//import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patelr0
 */
public class JourneyPlannerTest {

    public JourneyPlannerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testAreaAndBusStopNotSame() {
        System.out.println("testBusStopNotSame");
        String fromArea = "Marple";
        String fromStop = "Norfolk Arms";
        String toArea = "Marple";
        String toStop = "Norfolk Arms";
        String result = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        assertEquals("The origin and destination information cannot be the same\n", result);
    } 
    
    @Test
    public void testBusStopIsInCorrectArea() {
        System.out.println("testBusStopIsInCorrectArea");
        String fromArea = "New Mills";
        String fromStop = "Norfolk Arms";
        String toArea = "StockPort";
        String toStop = "Bus Station";
        String result2 = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        assertEquals("The origin and destination information cannot be the same\n"
                      + "Select a bus stop which is located in the correct area\n", result2);

    } 

    @Test
    public void testWrongBusStopForArea() {
        System.out.println("testWrongBusStopForArea");
        String fromArea = "Glossop";
        String fromStop = "Norfolk Arms";
        String toArea = "Stockport";
        String toStop = "Bus Station";
        String result3 = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        assertEquals("The origin and destination information cannot be the same\n"
                      + "Select a bus stop which is located in the correct area\n"
                      + "Bus stop 'Norfolk Arms' is not located in the Glossop area.\n", result3);
    }

    // RESULT ABOVE SHOWS THAT CERTAIN CODE NEVER RAN

    @Test
    public void testNotNull() {
        System.out.println("testNotNull");
        String fromArea = "Birch Vale";
        String fromStop = "Grouse Hotel";
        String toArea = "Marple";
        String toStop = "Back of Beyond";
        String result20 = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        assertTrue(result20 != null);
        
    }

    @Test
    public void testFromAreaID() {
        System.out.println("testFromAreaID");
        String fromArea = "Birch Vale";
        String fromStop = "Grouse Hotel";
        String toArea = "Hayfield";
        String toStop = "Bus Station";
        String nexting = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        int result4 = JourneyPlanner.fromAreaId;
        assertEquals(216, result4);

    }
    
    @Test
    public void testToAreaID() {
        System.out.println("testToAreaID");
        String fromArea = "Birch Vale";
        String fromStop = "Grouse Hotel";
        // testing Hayfield
        String toArea = "Hayfield";
        String toStop = "Bus Station";
        String nexting = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        int result5 = JourneyPlanner.toAreaId;
        assertEquals(217, result5);

    }
    
    @Test
    public void testFromBusStopID() {
        System.out.println("testFromBusStopID");
        String fromArea = "Birch Vale";
        String fromStop = "Grouse Hotel";
        // testing Hayfield
        String toArea = "Hayfield";
        String toStop = "Bus Station";
        String nexting = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        int result6 = JourneyPlanner.fromBusStopId;
        assertEquals(794, result6);

    }

    @Test
    public void testToBusStopID() {
        System.out.println("testToBusStopID");
        String fromArea = "Birch Vale";
        String fromStop = "Grouse Hotel";
        // testing Hayfield
        String toArea = "Marple";
        String toStop = "Norfolk Arms";
        String nexting = JourneyPlanner.getRoutes(fromArea, fromStop, toArea, toStop);
        int result7 = JourneyPlanner.toBusStopId;
        assertEquals(775, result7);

    }
}