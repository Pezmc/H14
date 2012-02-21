/* This class needs */

/* T5: Plan the implementation (10 minutes):  Use the Java programming language to implement the  design 
obtained in T3 and T4 into a working system.

This system, to be called “IBMS-RDH”, is a subsystem of the 
IBMS. Since each team will build your own IBMS-RDH, I suggest that you name your team’s IBMS-RDH as 
“teamID- IBMS-RDH”

For example, H3-IBMS-RDH is for team H3; G5-IBMS-RDH is for team G5, and 
I8-IBMS-RDH is for team I8. Before starting implementation, you should plan the implementation tasks and 
decide who does what by when. The main implementation tasks are: 
  T5-1: Implement the design classes; 
  T5-2: Implement the user interface; 
  T5-3: Connect the design classes to the IBMS bus database; 
  T5-4: Test the system (hint: test examples can be used for demonstration in Week 4).

The interface should be interactive such that it will ask the user to enter the holiday request information and 
display the approved holidays on the screen

*/

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

public class RDH {
  //Prompt user for driver ID
    //Display holidays driver has at the moment
  //Allow user to request new holidays 
    //Take start date and end date as input
    //Holiday is accepted or rejected due to invalid dates

  //Don't crash and allow go back to start
  
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
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      database.openBusDatabase();
       
      //Greet
      //while not done loop
        //Take driver ID
        
        //Search for driver ID
          //Driver doesn't exist error and try again
          //Found driver continue
          
        //Catch none number
      
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