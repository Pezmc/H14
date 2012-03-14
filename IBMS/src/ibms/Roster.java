package ibms;

/**
 *
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
 * 3. The number of days worked per week per driver is minimised.
 * 4. The allocation of hours in a week to drivers is fair.
 * 5. The use of buses should be balanced to give each bus a roughly equal workload in any one roster.
 */

public class Roster
{

  //read input

  //array lists to store info

  //generate roster
  public static String generateRoster() {
    //opendb
    database.openBusDatabase();

    //lists of drivers and busses
    int[] busIds = BusInfo.getBuses();
    int[] driverIds = DriverInfo.getDrivers();

    //for every driver
    int i;
    for(i = 0; i < driverIds.length; i++) {
       //reset driver hours to zero
       DriverInfo.setHoursThisWeek(driverIds[i], 0);      
       //store duration of routes
    }

    //for every day 0-6
    int dayOfWeek = 0;
    for(dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) { //do can we skip these?
      //set the times to zero for each day
      //reset busses

      //switch on day to get kind (week/sat/sun)
      TimetableInfo.timetableKind dayType;
      if(dayOfWeek<=4)
          dayType = TimetableInfo.timetableKind.weekday;
      else if(dayOfWeek==5)
          dayType = TimetableInfo.timetableKind.saturday;
      else if(dayOfWeek==6)
          dayType = TimetableInfo.timetableKind.sunday;
      else
          throw new IllegalArgumentException("The day of the week "
                                             + dayOfWeek + " doesn't exist!");

      //create array lists that are empty
      int[] routeList = BusStopInfo.getRoutes();

      //for every route
      int routeNo;
      for(routeNo = 0; routeNo < routeList.length; routeNo++) {
          //empty the slots/busSlots

          //get a list of bus stops on this route
          int[] busStops = BusStopInfo.getBusStops(routeList[routeNo]);

          //get a list of services on this route
          int[] services = TimetableInfo.getServices(routeList[routeNo],dayType);

          //for every service
             //get the list of times

             //get route duration

               //while we haven't allocated a driver
                  //calculate hours this week and day
                      //if the drivers hours don't exceed the max
                      //and he is back and he is available
                          //add this route
                          //update hourly week/day

               //while we haven't allocated a bus
                  //calculate bus back time
                      //if the bus available mark it as used
          //end for every service
       } //end for every route
     } //end for every day
     

     //For every day from 0-7
        //print mon-sun
        //switch on day kind
            //for each route
                //get list of services
                //for every service
                    //print route, service, driver, bus
      throw new UnsupportedOperationException("Just pseudo code atm...");
    }
}
