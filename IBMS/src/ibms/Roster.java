package ibms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a roster that has been generated for partiular day
 * @author pezcuckow
 */
public class Roster {
  private static HashMap<Integer, HashMap<Integer, HashMap<Integer, Driver>>> driverTimes;
  private static HashMap<Integer, HashMap<Integer, HashMap<Integer, Bus>>> busTimes;
  
  /**
   * Make an instance of an empty roster
   */
  public Roster() {
    driverTimes = new HashMap<Integer, HashMap<Integer, HashMap<Integer, Driver>>>();
    busTimes = new HashMap<Integer, HashMap<Integer, HashMap<Integer, Bus>>>();
  }

  /**
   * Get the hashmap of driver times, you must have generated it
   * @return The driver times as a hashmap on DOW, ROUTE, SERVICE
   */
  public HashMap<Integer, HashMap<Integer, HashMap<Integer, Driver>>> getDriverTimes() {
    //if(driverTimes.size()==0)
      //throw new IllegalStateException("You haven't generated the roster yet?!?");
    
    return driverTimes;
  }
  
  /**
   * Get the hashmap of driver times, you must have generated it
   * @return The driver times as a hashmap on DOW, ROUTE, SERVICE
   */
  public HashMap<Integer, HashMap<Integer, HashMap<Integer, Bus>>> getBusTimes() {
    //if(busTimes.size()==0)
      //throw new IllegalStateException("You haven't generated the roster yet?!?");
    
    return busTimes;
  }  
  
  /**
   * Print out the driver hours at LOTS of text
   */
  public void printDriverHours() {
    int[] driverIds = DriverInfo.getDrivers();
    ArrayList<Driver> drivers = new ArrayList<Driver>();

    //for every driver
    int i;
    for(i = 0; i < driverIds.length; i++) {
       //new driver
       Driver driver = new Driver(driverIds[i]);

       //Load driver infro from db
       driver.load();
       
       //Add to our list
       drivers.add(driver);
       //store duration of routes
    }

    for(Driver driver : drivers) {
      System.out.println(""+driver+" Total Week Hours: "+Util.minToTime(driver.getMinutesThisWeek()));
      System.out.println();
    }
    
  }
  
 /**
   * Print out the roster as lots of text
   */
  public String print() {
    //for every day 0-6
      String message = "";
    int dayOfWeek = 0;
    for(dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
        message += "========================\n";
        System.out.print("========================");
        message += Util.dowToString(dayOfWeek);
        System.out.print(Util.dowToString(dayOfWeek));
        message += "\n========================\n";
        System.out.println("========================");

      //switch on day to get kind (week/sat/sun)
      TimetableInfo.timetableKind dayType = Util.dowToKind(dayOfWeek);

      //create array lists that are empty
      int[] routeList = BusStopInfo.getRoutes();

      //for every route
      int routeNo;
      for(routeNo = 0; routeNo < routeList.length; routeNo++) {
          message += "============Route "+routeList[routeNo]+"============\n";
        System.out.println("============Route "+routeList[routeNo]+"============");

        //get a list of bus stops on this route
        int[] busStops = BusStopInfo.getBusStops(routeList[routeNo]);

        //get a list of services on this route
        int[] services = TimetableInfo.getServices(routeList[routeNo],dayType);

        //for every service
        int serviceNo;
        for(serviceNo = 0; serviceNo < services.length; serviceNo++) {
          int[] serviceTimes = TimetableInfo.getServiceTimes(routeList[routeNo],dayType,serviceNo);
          
          System.out.println("Route/Service: "+routeList[routeNo]+"/"+services[serviceNo]);
          System.out.println("Driver: "+driverTimes.get(dayOfWeek).get(routeList[routeNo]).get(services[serviceNo]));
          System.out.println("Bus: "+busTimes.get(dayOfWeek).get(routeList[routeNo]).get(services[serviceNo]));
          System.out.println("Start: "+Util.minToTime(serviceTimes[0])
                              +"\tEnd: "+Util.minToTime(serviceTimes[serviceTimes.length-1]));
          System.out.println();


          message += "Route/Service: "+routeList[routeNo]+"/"+services[serviceNo]+"\n";
          message += "Driver: "+driverTimes.get(dayOfWeek).get(routeList[routeNo]).get(services[serviceNo]) + "\n";
          message += "Bus: "+busTimes.get(dayOfWeek).get(routeList[routeNo]).get(services[serviceNo]) + "\n";
          message += "Start: "+Util.minToTime(serviceTimes[0])
                              +"\tEnd: "+Util.minToTime(serviceTimes[serviceTimes.length-1]) + "\n";
          message += "\n";
           
           //get the list of times
           
        } //end for every service
      } //end for every route
    } //end for every day

    return message;
  }
  
  /**
   * Save the roster somehow
   */
  private void save() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  /** 
   * Load the roster somehow
   */
  private void load() {
    throw new UnsupportedOperationException("Not yet implemented");
  }
  
}
