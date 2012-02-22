package ibms;

import ibms.DriverInfo;
import ibms.database;
import java.io.*;
import java.util.*;
import java.text.*;


/*************************************************************************************/
/*************************************************************************************/
/**********************DON'T EDIT THIS FILE AS IT's THE OLD ONE***********************/
/*************************************************************************************/
/*************************************************************************************/
/*************************************************************************************/

/*
 * This is a command line interface that provides
 * to a driver that option to request/view/cancel
 * his/her holidays.
 * It may be extended in a GUI in a later stage.
 * This is an extended version of the provided CLI
 * from John Sargeant.
 */

/**
 *
 * @author Emmanouil Theodorou
 */
public class RDHOld
{
  // A stream pipeline which will allow us to take in lines of input.
  private static BufferedReader input =
               new BufferedReader(new InputStreamReader(System.in));

  private static int totalHolidays = 25;
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

  // The gatekeeper.
  private static boolean _validated = true;

  /**
   * Confirm that the previous input has been validated
   */
  public static void confirmInputValidated() { _validated = true; }

  /**
   * Utility method to check validation and issue a warning if necessary
   */
  private static void checkValidation()
  {
    if (!_validated) System.out.println("Previous input not validated");
  }

  // Check whether driver ID exists in the database or not
  public static boolean checkID(int enteredID)
  {
    boolean found = false;
    int[] driverIDs = DriverInfo.getDrivers();
    for (int index = 0; index < driverIDs.length; index++)
    {
      if(enteredID == driverIDs[index])
      {
        found = true;
        break;
      } // if
    } // for
    if (found)
      return true;
    else
      return false;
  } // checkID

//  public static boolean checkDate

  public static void main(String[] args)
  {
    database.openBusDatabase();
    
    System.out.println("Welcome to the IBMS-RDH System.");
    boolean done = false;
    int driversID = 0;
    while (!done)
    {
      System.out.println("Give your Identification Number please or 0 to quit:");

      String givenDriversID = input();
      try
      {
        driversID = Integer.parseInt(givenDriversID);
        confirmInputValidated();
        done = true;
        // check database if that ID exists
        // if id does not exist output appropriate msg
        if (!checkID(driversID))
        {  System.out.println("Incorrect Identification Number");
           done = false;
        } // if
        // else continue
        else
        {
          System.out.println("\nWelcome " + DriverInfo.getName(driversID));
          System.out.println("You have "
                              + (25 - DriverInfo.getHolidaysTaken(driversID))
                              + " holidays remaining\n");
          done = true;
        }
          
      } // try
      catch(NumberFormatException e)
      {
        System.out.println("Invalid input - Only numbers accepted");
      } // catch
    } // while

    done = false;
    int selection;

    Date startDate = null;
    Date endDate = null;

    int holidayDuration = 0;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    do
    {
      System.out.println("Please choose from the following list by entering"
                          + " the option's number.");
      System.out.println("1) Request Holidays\n2) View approved holidays\n"
                          + "0) Exit");
      try
      {
        selection = Integer.parseInt(input());

        switch (selection)
        {
          case 1:
          {
            
            break;
          }
        
          case 2:
          {
            ArrayList list = DriverInfo.getUnavailableDates(driversID);
            System.out.println("List of Dates that are holidays");
            System.out.println(list);
            //System.out.println("Retrieve and print all driver's approved holidays");
            /*Date startingDate = (Date) list.get(0);
            Date cloning = (Date) startingDate.clone();
            Date tempDate = cloning;
            Date endingDate = null;
            for(int i=1; i< list.size(); i++)
            {
              //tempDate.setDate(startingDate.getDate());
              System.out.println("Tempdate: "+ tempDate);
              System.out.println("Next date: " + list.get(i));
              if (tempDate.compareTo((Date)list.get(i-1)) != 0)
              {
                System.out.println("Starting Date: " + startingDate);
                //tempDate.setDate(tempDate.getDate()-1);
                //endingDate.setDate(endingDate.getDate());
                System.out.println("End Date: " + endingDate);
                startingDate = (Date) list.get(i);
              }
              if (tempDate.getMonth() != ((Date) list.get(i)).getMonth())
              {
                //tempDate.setDate(tempDate.getDate()+1);
                endingDate = tempDate;
                tempDate.setMonth(((Date) list.get(i)).getMonth());
              }
              else
              {
                endingDate.setDate(endingDate.getDate()-1);
                tempDate.setDate(tempDate.getDate()+1);
              }
            } */
            break;
          }
          case 0:
          {
            // Exits to the main menu.
            startDate = endDate = null;
            done = true;
            break;
          }
          default:
            System.out.println("Invalid option.");
        } // switch
      }
      catch(NumberFormatException e)
      {
        System.out.println("Invalid input - Only numbers accepted");
      } // catch
    } while (!done);

    System.out.println("Thank you for using the RDH panel, goodbye.");
  } // main
} // class RDH

