package ibms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cuckowp0
 */

/*
 * The rules for rostering are:
 * 1. A roster is generated for each week based upon the timetable for that week.
 * 2. The maximum driving time for any driver in any one day is 10 hours.
 * 3. There can be no more than 50 hours driven by any one driver in any one week.
 * 4. A driver can drive for a maximum of 5 hours at any one time and must have a break of at least one hour. Breaks can only be taken at the bus depot.
 * 5. A driver shift consists of one period of up to 5 hours driving time, or two such periods with a 1 hour break between them.
 * 6. Time spent with the bus whilst not actually moving counts as driving time for the driver (that is, while responsible for the bus).
 * 7. There is a sufficiency of fueled buses available for the roster.
 * 8. If a bus is available, it is available for the whole day.
 * 9. A driver may specify up to two resting days for each week in which they will not be available for work. (We assume not all drivers will choose the same two days.)
 * 10. Drivers can normally take 25 days of holidays a year – this is in addition to the two resting days a week they specified.
 * 11. For any weekdays, the maximum number of drivers who can request the same holidays is 10 and the request is approved by the company management in the order of first-come-first –served.  During Sundays and public holidays when fewer buses are in operation then it is possible for more than 10 drivers to request the holidays for the same period. This information should be available from the database provided.
 * 12. If a driver requests holidays, he or she should specify the intended starting date and the finishing date.
 *
 * The properties of a good roster are:
 * 1. Rostering should maximise the amount of time a driver actually drives during a shift whilst not countering rules for breaks.
 * 2. The number of drivers used in a roster is minimised.
 * 3. The number of days worked per week per driver is minimised. - 
 * 4. The allocation of hours in a week to drivers is fair.
 * 5. The use of buses should be balanced to give each bus a roughly equal workload in any one roster.
 */

public class Roster
{
  //Three dimensional array on DOW, ROUTE, SERVICE
  private static HashMap<Integer, HashMap<Integer, HashMap<Integer, Driver>>> driverTimes = new HashMap<Integer, HashMap<Integer, HashMap<Integer, Driver>>>();
  private static HashMap<Integer, HashMap<Integer, HashMap<Integer, Bus>>> busTimes = new HashMap<Integer, HashMap<Integer, HashMap<Integer, Bus>>>();
  
  /**
   * Get the hashmap of driver times, you must have generated it
   * @return The driver times as a hashmap on DOW, ROUTE, SERVICE
   */
  public HashMap<Integer, HashMap<Integer, HashMap<Integer, Driver>>> getDriverTimes() {
    if(driverTimes.size()==0)
      throw new IllegalStateException("You haven't generated the roster yet?!?");
    
    return driverTimes;
  }
  
  /**
   * Get the hashmap of driver times, you must have generated it
   * @return The driver times as a hashmap on DOW, ROUTE, SERVICE
   */
  public HashMap<Integer, HashMap<Integer, HashMap<Integer, Bus>>> getBusTimes() {
    if(busTimes.size()==0)
      throw new IllegalStateException("You haven't generated the roster yet?!?");
    
    return busTimes;
  } 

  /**
   * Print out the driver hours at LOTS of text
   */
  public static void printDriverHours() {
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
  public static void printRoster() {
    //for every day 0-6
    int dayOfWeek = 0;
    for(dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
      System.out.print("========================");
      System.out.print(Util.dowToString(dayOfWeek));
      System.out.println("========================");

      //switch on day to get kind (week/sat/sun)
      TimetableInfo.timetableKind dayType = Util.dowToKind(dayOfWeek);

      //create array lists that are empty
      int[] routeList = BusStopInfo.getRoutes();

      //for every route
      int routeNo;
      for(routeNo = 0; routeNo < routeList.length; routeNo++) {
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
           
           //get the list of times
           
        } //end for every service
      } //end for every route
    } //end for every day
  }

  /**
   * This generates the actual roster and saves it in this class 
   * @return
   * @throws InterruptedException
   * @throws Exception 
   */
  public static String generateRoster() throws InterruptedException, Exception {
    //opendb 
    database.openBusDatabase();

    //lists of drivers and busses
    int[] busIds = BusInfo.getBuses();
    int[] driverIds = DriverInfo.getDrivers();
    ArrayList<Driver> drivers = new ArrayList<Driver>();
    ArrayList<Bus> buses = new ArrayList<Bus>();

    //for every driver
    int i;
    for(i = 0; i < driverIds.length; i++) {
       //new driver
       Driver driver = new Driver(driverIds[i]);

       //Add to our list
       drivers.add(driver);
       //store duration of routes
    }
    
    //for every bus
    for(i = 0; i < busIds.length; i++) {
       //new driver
       Bus bus = new Bus(busIds[i]);

       //Add to our list
       buses.add(bus);
    }

    //Random numbers
    Random rand = new Random();

    //for every day 0-6
    int dayOfWeek = 0;
    for(dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) { //do can we skip these?
      debug("================================================================");
      debug("===========Looking at new day "+dayOfWeek);
      debug("================================================================");
      
      driverTimes.put(dayOfWeek, new HashMap<Integer, HashMap<Integer, Driver>>());
      busTimes.put(dayOfWeek, new HashMap<Integer, HashMap<Integer, Bus>>());
      
      ArrayList<Driver> todaysDrivers = new ArrayList<Driver>();

      //set the times to zero for each day
      for (Driver driver : drivers) {
        driver.setMinutesThisDay(0);
        driver.nextDayShifts();
      }
      
      //set the times to zero FUR DER BUSSES
      for (Bus bus : buses) {
        bus.nextDayShifts();
      }

      //switch on day to get kind (week/sat/sun)
      TimetableInfo.timetableKind dayType = Util.dowToKind(dayOfWeek);

      //create array lists that are empty
      int[] routeList = BusStopInfo.getRoutes();
      //System.out.println("Route "+routeNo);

      //for every route
      int routeNo;
      for(routeNo = 0; routeNo < routeList.length; routeNo++) {
        debug("========================================================");
        debug("===========Looking at route "+routeNo+":"+routeList[routeNo]);
        debug("========================================================");
        
        driverTimes.get(dayOfWeek).put(routeList[routeNo], new HashMap<Integer, Driver>());
        busTimes.get(dayOfWeek).put(routeList[routeNo], new HashMap<Integer, Bus>());
        //busTimes.put(dayOfWeek, new HashMap<Integer, HashMap<Integer, Bus>>());
        
        //65, 66, 67, 68
        //if(routeList[routeNo]>66) continue; ////////////////////ONLY DO 66 ATM
        //empty the slots/busSlots

        //get a list of bus stops on this route
        int[] busStops = BusStopInfo.getBusStops(routeList[routeNo]);

        //get a list of services on this route
        int[] services = TimetableInfo.getServices(routeList[routeNo],dayType);

        //for every service
        int serviceNo;
        for(serviceNo = 0; serviceNo < services.length; serviceNo++) {
           debug("========================================");
           debug("=======Looking at service "+serviceNo+" "+services[serviceNo]);
           debug("========================================");
           
           //get the list of times
           int[] serviceTimes = TimetableInfo.getServiceTimes(routeList[routeNo],dayType,serviceNo);
           
           /*for(int service : serviceTimes) {
             System.out.println("\tService time: "+Util.minToTime(service));
           }*/

           //get route duration
           int serviceLength;
           int end = serviceTimes[serviceTimes.length-1];
           int start = serviceTimes[0];
           if(end<start)
             end = serviceTimes[serviceTimes.length-1]+1440;
           serviceLength = end - serviceTimes[0];
             
           
           //Just in case, for error checking
           if(serviceLength<=0) {
             throw new NumberFormatException("The service has a negative time? "
                     + "Length" + serviceLength + " Service "+serviceNo
                     + " Service "+services[serviceNo]
                     + "Service length "+end+"-"+start);
           }
           
           Driver chosenDriver = null;

           //Sort all the drivers by hours this day
           Collections.sort(drivers);
           
           //Reverse so most hours are at the top aka only choose new driver
           //if we have too... this might not minimise days worked...
           Collections.reverse(drivers);

           //find a driver that we are allowed to choose
           int driverId = 0; //look at todays drivers...
           while(chosenDriver==null&&driverId<todaysDrivers.size()) {
             //Driver randomDriver = drivers.get(rand.nextInt(drivers.size()));
             Driver currentDriver = todaysDrivers.get(driverId);

             //if the drivers hours don't exceed the max
                //and he is back and he is available
             if(currentDriver.checkAddMinutes(serviceLength)
                     &&currentDriver.checkShift(start, end))
               chosenDriver = currentDriver;

             driverId++;
           }

           //look at other drivers
           driverId = 0;
           while(chosenDriver==null&&driverId<drivers.size()) {
             //Driver randomDriver = drivers.get(rand.nextInt(drivers.size()));
             Driver randomDriver = drivers.get(driverId);

             //if the drivers hours don't exceed the max
                //and he is back and he is available
             if(randomDriver.checkAddMinutes(serviceLength)
                     &&randomDriver.checkShift(start, end)) {
               chosenDriver = randomDriver;
               todaysDrivers.add(chosenDriver);
             }

             driverId++;
           }

           if(chosenDriver==null) {
             throw new Exception("I couldn't find a driver for service "
                     +serviceNo+" "+services[serviceNo]+" :-(");
           }
   
           //look at busses
           Bus chosenBus = null;
           int busId = 0;
           while(chosenBus==null&&busId<buses.size()) {
             Bus possibleBus = buses.get(busId);

             //if the bus is available
             if(possibleBus.checkShift(start, end, 0)) {
               chosenBus = possibleBus;
             }

             busId++;
           }
           
           if(chosenBus==null) {
             throw new Exception("I couldn't find a bus for service "
                     +serviceNo+" "+services[serviceNo]+" :-(");
           }

           //Add the hours to the driver
           chosenDriver.addMinutesThisDay(serviceLength);

           //Update the drivers end time
           chosenDriver.addShift(start, end);
           
            //Update the busses end time
           chosenBus.addShift(start, end);

           //Store this infomation
           driverTimes.get(dayOfWeek).get(routeList[routeNo]).put(services[serviceNo], chosenDriver);
           busTimes.get(dayOfWeek).get(routeList[routeNo]).put(services[serviceNo], chosenBus);
           
       
           System.out.println("==Chose driver "+chosenDriver+ " for service "
                   + services[serviceNo]+" Time: "+Util.minToTime(start)+"->"
                   +Util.minToTime(end) + " with bus "+chosenBus);
        } //end for every service
      } //end for every route
    } //end for every day
    
    //Save all our drivers to the DB
    for(Driver driver : drivers)
      driver.save();
    
    return "";
  }

  /**
   * Test method of the roster
   */
  public static void main(String[] args) throws Exception {
    try {
      generateRoster();
      printRoster();
      printDriverHours();
    } catch (InterruptedException ex) {
      Logger.getLogger(Roster.class.getName()).log(Level.SEVERE, null, ex);
    } /*catch (Exception ex) {
      System.out.println("Something went wrong: "+ex.getMessage());
      System.out.println(ex);
    }*/
  }
  //read input  
  
  /**
   * Use this to output info and (un)comment as required
   * @param String message 
   */
  private static void debug(String message) {
    //System.out.println(message);
  }
}
