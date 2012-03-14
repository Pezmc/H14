/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibms;

/**
 * This class represents an instance of a driver
 * @author cuckowp0
 */
class Driver {
  private static int[] drivers = DriverInfo.getDrivers();
  private static int MAX_DAY_HOURS = 10;
  private static int MAX_WEEK_HOURS = 50;

  private int driverId = -1;
  private int hoursWeek = 0;
  private int hoursDay = 0;
  private int timeAtStation = 0;


  /* Reference by driver id */
  public Driver(int id) {
    //Check the driver actually exists
    for (int i = 0; i < drivers.length; i++) {
      if(id == drivers[i]) {
        driverId = id;
      } // if
    } // for

    if(driverId==-1)
       throw new IllegalArgumentException("Driver id "+id+" doesn't exist");
  }

  /**
   * Set how many hours they have worked "today"
   */
  public int getHoursThisWeek() {
    return hoursWeek;
  }

  /**
   * Set how many hours they have worked
   */
  public void setHoursThisWeek(int hours) {
    hoursWeek = hours;
    //DriverInfo.setHoursThisWeek(driverId, hours);
  }

  /**
   * Set how many hours they have worked "today"
   */
  public int getHoursThisDay() {
    return hoursDay;
  }

  /**
   * Set how many hours they have worked "today"
   */
  public void setHoursThisDay(int hours) {
    hoursDay = hours;
  }

  /**
   * Set how many hours they have worked "today"
   */
  public void addHoursThisDay(int hours) {
    hoursWeek += hours;
    hoursDay += hours;
  }


  /**
   * Set how many hours they have worked "today"
   */
  public boolean checkAddHours(int hours) {
    hoursWeek += hours;
    hoursDay += hours;
    return hoursDay+hours<=MAX_DAY_HOURS&&hoursWeek+hours<=MAX_WEEK_HOURS;
  }

  /**
   * Get hours at station
   */
  public int getTimeAtStation() {
    return timeAtStation;
  }

  /**
   * Get time back at station (when free again)
   */
  public int setTimeAtStation(int time) {
    timeAtStation = time;
  }

}
