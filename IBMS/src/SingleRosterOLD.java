/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ben
 */
/*
 * A very simple application illustrating how to use the interface.
 * Prints the names of all the drivers in the database.
 * @author Manos_s32, Agni Gautam
 */

import java.util.*;
import java.text.*;
import java.io.*;
import java.util.ArrayList;


public class SingleRosterOLD
{


  // A stream pipeline which will allow us to take in lines of input.
  private static BufferedReader input =
               new BufferedReader(new InputStreamReader(System.in));

  /**
   * Input a line of text
   */
  public static String input()
  {
    String result = "";
    try
    {
      result = input.readLine();
    }
    catch (IOException e)
    {
      // Shouldn't happen since we are using System.in!
      System.out.println("Problem with System.in or bug in this code!");
      System.exit(1);
    }
   // _validated = false;
    return result;
  }

  private static List<ArrayList<ArrayList<Integer>>> slots = new ArrayList<ArrayList<ArrayList<Integer>>>();
  private static List<ArrayList<ArrayList<Integer>>> busSlots = new ArrayList<ArrayList<ArrayList<Integer>>>();

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws Exception
  {
    String str = generateRoster();
    //System.out.println(str);
  }

  public static String generateRoster()
  {
    String rostere="";
       database.openBusDatabase();

       // get available drivers here ------
       int[] drivers = DriverInfo.getDrivers();
       int[] buses = BusInfo.getBuses();
       // SET weekly/daily/isBack times of all drivers to 0
       for (int index = 0; index < drivers.length; index++)
       {
         DriverInfo.setHoursThisWeek(drivers[index], 0);
         DriverInfo.setHoursThisDay(drivers[index], 0);
         DriverInfo.setIsBackTime(drivers[index], 0);
       } // for

       // Starting route 65, goes up to 68
       int route = 65;

       // Counting variables for stats
       int totalWeeklyTimes = 0;
       int totalSaturdayTimes = 0;
       int totalSundayTimes = 0;

       int totalTimes = 0;

       // Array that stores the duration of services of each route
       List<ArrayList<Integer>> routeServicesDuration = new ArrayList<ArrayList<Integer>>();

       // Array that stores the driver name/id at a SLOT "day/route/service"
       //List<ArrayList<ArrayList<Integer>>> slots = new ArrayList<ArrayList<ArrayList<Integer>>>();

       //for (int k = 65; k < 69; k++)
       //{
         //totalWeekdayServices = TimetableInfo.getNumberOfServices
         //                 (i, TimetableInfo.timetableKind.weekday);
         //routesServiceDuration = int[j][totalWeekdayServices];
       //}

       // Day range: 0-6 (Mon-Sun)
       int day = 0;
       //routeServicesDuration.add(new ArrayList<Integer>());
       TimetableInfo.timetableKind kind;
       while( day < 7) // change to <7 to include sat/sun
       {
         // SET daily/isBack times of all drivers to 0
         for (int index = 0; index < drivers.length; index++)
         {
           DriverInfo.setHoursThisDay(drivers[index], 0);
           DriverInfo.setIsBackTime(drivers[index], 0);

         } // for

         for (int index = 0; index < buses.length; index++)
         {
           BusInfo.setIsBackTime(buses[index], 0);
         }

         Sort.sortDrivers(drivers);

         if (day < 5)
         {
            kind = TimetableInfo.timetableKind.weekday;
         }
         else if (day == 5)
         {
            kind = TimetableInfo.timetableKind.saturday;
         }
         else
         {
            kind = TimetableInfo.timetableKind.sunday;
         }
           //routeServicesDuration.add(new ArrayList<Integer>());
           slots.add(new ArrayList<ArrayList<Integer>>());
           busSlots.add(new ArrayList<ArrayList<Integer>>());
           //slots.get(day).add(new ArrayList<Integer>());
           route=65;
        while (route <= 66) // add to include all routes
        {
           routeServicesDuration.add(new ArrayList<Integer>());
           //slots.add(new ArrayList<ArrayList<Integer>>());
           slots.get(day).add(new ArrayList<Integer>());
           busSlots.get(day).add(new ArrayList<Integer>());
           //slots.add(new ArrayList<ArrayList<Integer>>());
           //slots.get(day).add(new ArrayList<Integer>());

         //slots.get(day).add(new ArrayList<Integer>());
         //slots.get(day).get(route-65).add(0);
         //System.out.println(slots.get(day).get(route-65).get(0));

           int[] busStops = BusStopInfo.getBusStops(route);
         //totalWeekdayServicesForRoute = TimetableInfo.getNumberOfServices
         //                 (route, TimetableInfo.timetableKind.weekday);
         //totalWeekdayServices = totalWeekdayServices + totalWeekdayServicesForRoute;


         //TimetableInfo.timetableKind kind = TimetableInfo.timetableKind.weekday;

         //System.out.print("Weekday Services : ");
           int[] services = TimetableInfo.getServices(route,kind);
           for (int i=0;i<services.length;i++)
           {

             // Array that stores the service times
             int[] times = TimetableInfo.getServiceTimes(route,kind, i);

             // Calculating and storing the service's duration
             routeServicesDuration.get(route-65).add(times[times.length-1]-times[0]);


             //Integer toPrint = routeServicesDuration.get(route-65).get(i);
             //totalWeeklyTimes = totalWeeklyTimes + toPrint;
             //System.out.println("Duration of Service " + services[i] + "(" + route + "): "
             //                    + toPrint);

             // Index to go through all the drivers
             int index = 0;

             // Boolean variable to check if a driver is allocated to the service
             boolean driverNotAllocated = true;
             // Stupid variable to check if driver isBack to take another service
             // change it to int
             //int isBackTime = DriverInfo.getIsBackTime(drivers[index]);



             while (driverNotAllocated && index < drivers.length)
             {
               int hoursThisWeek = DriverInfo.getHoursThisWeek(drivers[index]);
               int hoursThisDay = DriverInfo.getHoursThisDay(drivers[index]);
               int isBackTime = DriverInfo.getIsBackTime(drivers[index]);

               // If driver's daily and weekly hours dont exceed the maximums(5h / 50h)
               // and he is back from the previous taken service (and he is available
               // - for iteration 3) then he can take this service slot.
               if( hoursThisDay + routeServicesDuration.get(route-65).get(i) < 300
                   && hoursThisWeek + routeServicesDuration.get(route-65).get(i) < 3000
                   && isBackTime < times[0])
               {
                 // System.out.println("Day: "+ day + " ,Route/Service: " + (route-65) + "/" + i);
                 slots.get(day).get(route-65).add(drivers[index]);
                 //System.out.println(slots.get(day).get(route-65).get(i));


                 // Update driver's daily and weekly hours (could do it for yearly too)
                 DriverInfo.setHoursThisWeek(drivers[index],
                         hoursThisWeek + routeServicesDuration.get(route-65).get(i));
                 DriverInfo.setHoursThisDay(drivers[index],
                         hoursThisDay + routeServicesDuration.get(route-65).get(i));

                 // Update isBackTime to service's end time
                 // 65-66 : end time is at Stockport so dont care
                 // 67-68 : to check
                 //isBackTime = times[times.length-1];
                 //System.out.println(times[times.length-1]);
                 DriverInfo.setIsBackTime(drivers[index], times[times.length-1]);
                 // Driver allocated successfully
                 driverNotAllocated = false;

//IAM
                 boolean busNotAllocated=true;
                 int bindex = 0;
                 // Now allocate a bus to that slot too // another 3d array? yes prolly
                 while (busNotAllocated)
                 {
                   int isBusBackTime = BusInfo.getIsBackTime(buses[bindex]);
                   if (isBusBackTime < times[0])
                   {
                     busSlots.get(day).get(route-65).add(buses[bindex]);
                     BusInfo.setIsBackTime(buses[bindex], times[times.length-1]);
                     // Bus allocated successfully
                     busNotAllocated = false;
                   }
                   bindex++;
                 }
               }
               index++;
             }
           }
         //}

         //System.out.println("\nTotal: " + TimetableInfo.getNumberOfServices(route, kind));
         //System.out.println("=================================================");
         //day++;

           route++;
         } // inner while
         day++;
       } // while
       route=65;
         System.out.println("=================================================");
         TimetableInfo.timetableKind dayKind;
try
{
// Create file
FileWriter fstream = new FileWriter("roster");
BufferedWriter out = new BufferedWriter(fstream);

         for(int dayIndex=0; dayIndex<7; dayIndex++ )
         {
           out.write("\n=================================================");
           rostere += "\n=================================================";
           switch(dayIndex)
           {
             case 0:
               System.out.println("Monday");
               out.write("\nMonday");
               rostere+="\nMonday";
               break;
             case 1:
               System.out.println("Tuesday");
               out.write("\nTuesday");
               rostere+="\nTuesday";
               break;
             case 2:
               System.out.println("Wednesday");
               out.write("\nWednesday");
               rostere+="\nWednesday";
               break;
             case 3:
               System.out.println("Thursday");
               out.write("\nThursday");
               rostere+="\nThursday";
               break;
             case 4:
               System.out.println("Friday");
               out.write("\nFriday");
               rostere+="\nFriday";
               break;
             case 5:
               System.out.println("Saturday");
               out.write("\nSaturday");
               rostere+="\nSaturday";
               break;
             case 6:
               System.out.println("Sunday");
               out.write("\nSunday");
               rostere+="\nSunday";
               break;
           }
           out.write("\n=================================================");
           rostere += "\n=================================================";
           if (day < 5)
           {
             dayKind = TimetableInfo.timetableKind.weekday;
           }
           else if (day == 5)
           {
             dayKind = TimetableInfo.timetableKind.saturday;
           }
           else
           {
             dayKind = TimetableInfo.timetableKind.sunday;
           }
           for (int routeIndex=0; routeIndex<2; routeIndex++)
           {
             int[] servicesS = TimetableInfo.getServices(route+routeIndex, dayKind);
             switch(routeIndex)
             {
               case 0:
                 System.out.println("Route 65:");
                 out.write("\nRoute 65:");
                 rostere+="\nRoute 65:";
                 break;
               case 1:
                 System.out.println("Route 66:");
                 out.write("\nRoute 66:");
                 rostere+="\nRoute 66:";
                 break;
               case 2:
                 System.out.println("Route 67:");
                 out.write("\nRoute 67:");
                 rostere+="\nRoute 67:";
                 break;
               case 3:
                 System.out.println("Route 68:");
                 out.write("\nRoute 68:");
                 rostere+="\nRoute 68:";
                 break;
             } // switch
             //System.out.println(servicesS.length);
             rostere+="\n=================================================";
             out.write("\n=================================================");

             for (int service=0; service<servicesS.length; service++)
             {
               int id = slots.get(dayIndex).get(routeIndex).get(service);
               int busID = busSlots.get(dayIndex).get(routeIndex).get(service);
               String name = DriverInfo.getName(id);
               System.out.println("\nRoute / Service: " + (routeIndex+route) + "/"
                                  + servicesS[service]
                                  + "\nDriver: "
                                  + name  + "\nBus:" + busID + "\n");
                       out.write("\nRoute / Service: " + (routeIndex+route) + "/"
                                  + servicesS[service]
                                  + "\nDriver: "
                                  + name + "\nBus:" + busID  + "\n");
                       rostere+="\nRoute / Service: " + (routeIndex+route) + "/"
                                  + servicesS[service]
                                  + "\nDriver: "
                                  + name + "\nBus:" + busID  + "\n";
               //System.out.println("Hours: " + DriverInfo.getHoursThisWeek(id)/60);
             }
             out.write("\n=================================================");
             System.out.println("=================================================");
           }
           //out.write("\n=================================================");
           //System.out.println("=================================================");
           //System.out.println("Successfull generation of roster.");
          // System.out.println("=================================================");
          // rostere+="\n=================================================";
           //out.write("\n=================================================");
         }
rostere+="\n=================================================";
out.write("\n=================================================");
rostere+="\nSuccessfull generation of roster.";
out.close();
  } // try
         catch (Exception e)
         {//Catch exception if any
               System.err.println("Error: " + e.getMessage());
         }
return rostere;
    } // generateRoster

  public static List<ArrayList<ArrayList<Integer>>> getSlots()
  {
    return slots;
  }

}
