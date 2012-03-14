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

  int driverId = -1;
  int hoursWeek = 0;
  int hoursDay = 0;

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
   * Set how many hours they have worked
   */
  public void setHoursThisWeek(int hours) {
    hoursWeek = hours;
    //DriverInfo.setHoursThisWeek(driverId, hours);
  }

  /**
   * Set how many hours they have worked "today"
   */
  public void setHoursThisDay(int hours) {
    hoursDay = hours;
  }
}



          boolean validId = false;
