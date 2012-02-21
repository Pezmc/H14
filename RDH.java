/* This class needs */

/*

4.	A completely working system for UC1 is defined as follows:
a.	Its interface is interactive: it asks for the input and displays the output, and its display is understandable, clear, neat and near professional.
b.	Given the driver ID as the input, the interface shows the number of holidays that the driver has.
c.	The driver selects specific dates for the holidays and the interface will display one of the two outcomes: (1) holidays granted; (2) holidays rejected due to invalid dates. 
d.	The above operations should be repeatable without crashing the system.

*/

/*
Driver logs into roster system.
Driver gives notice about upcoming holiday.
Enough notice is given.
Request is confirmed and allowed.
Driver logs out of system.
*/

/*
RULES
1. A roster is generated for each week based upon the timetable for that week.
2. The maximum driving time for any driver in any one day is 10 hours.
3. There can be no more than 50 hours driven by any one driver in any one week.
4. A driver can drive for a maximum of 5 hours at any one time and must have a break of at least one hour. Breaks can only be taken at the bus depot.
5. A driver shift consists of one period of up to 5 hours driving time, or two such periods with a 1 hour break between them.
6. Time spent with the bus whilst not actually moving counts as driving time for the driver (that is, while responsible for the bus).
7. There is a sufficiency of fueled buses available for the roster.
8. If a bus is available, it is available for the whole day.
9. A driver may specify up to two resting days for each week in which they will not be available for work. (We assume not all drivers will choose the same two days.)
*/

/*
RELATED RULES
10. Drivers can normally take 25 days of holidays a year – this is in addition to the two resting days a week they specified.  
11. For any weekdays, the maximum number of drivers who can request the same holidays is 10 and the request is approved by the company management in the order of first-come-first –served.
  During Sundays and public holidays when fewer buses are in operation then it is possible for more than 10 drivers to request the holidays for the same period. This information should be available from the database provided. 
12.	If a driver requests holidays, he or she should specify the intended starting date and the finishing date.
*/

/*
Basic workflow
  //Prompt user for driver ID
    //Display holidays driver has at the moment
  //Allow user to request new holidays 
    //Take start date and end date as input
    //Holiday is accepted or rejected due to invalid dates

  //Don't crash and allow go back to start
*/


/**
 * Class used with a CLI to request a driver holiday
 * Takes driver ID and requests holidays
 */
public class RDH {

  
  // Buffered reader to grab input
  static BufferedReader input =
               new BufferedReader(new InputStreamReader(System.in));
  static int maxHolidays = 25;
  static int minDrivers = 25;
  
  /**
   * Read a line of input from STDIn
   */
  public static String readLine() {
    String string = "";
    try {
      string = input.readLine();
    }
    catch (IOException e) {
      System.out.println("Error with STD IN"); 
      System.exit(1);
    }
    return string;
  }
  
  /**
   * Print out a line (alias)
   */
  private static void print(String message) {
    System.out.print(message);
  }
  
  /**
   * Print out information to the user (alias)
   */
  private static void println(String message) {
    System.out.println(message);
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      database.openBusDatabase();

      //Greet       
      println("=RDS H14="); 
      println("Welcome to the H14 request driver holiday system\n"); 
       
      //while not done loop
      boolean gotUser = false;
      do {
        //Take driver ID
        println("Please enter your driver id: ");
        String driverId = readLine();
        
        try {
          int id = Integer.parseInt(driverId);
          
          System.out.println(DriverInfo.findDriver((String) driverId));
          
        }
        catch (NumberFormatException e) {
          println("Your ID must be a number");
        }
        
        //Search for driver ID
          //Driver doesn't exist error and try again
          //Found driver continue
          
        //Catch none number
      }
      while(!gotUser);
      
      //Create data Format
        //Request holidays
        //View holidays
        //Quit


      //Switch on request
      
      //Requst holiday
        //read start date 0 - validate etc...
        //read end date - validate etc…
        //complete - calclate holiday lenth
          //check holiday legth + current holidays not bigger than toal holidays
          //check their are are enough drivers over the period
            //10?
          //mark holiday taken 
        //exit
              
      //List all the holidays a driver has taken already
      
    //DONE
  } // main
} //RDH