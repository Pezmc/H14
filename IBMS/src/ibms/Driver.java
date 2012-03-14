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
  private static int BREAK_TIME_HOURS = 1;

  private int driverId = -1;
  private int minutesWeek = 0;
  private int minutesDay = 0;
  private int timeAtStation = -1;
  private String name = "";


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

    //Update name
    name = DriverInfo.getName(driverId);
  }

  /**
   * Get the name of the driver
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Get the id of the driver
   * @return id
   */
  public int getId() {
    return driverId;
  }

  /**
   * Set how many hours they have worked "today"
   */
  public int getMinutesThisWeek() {
    return minutesWeek;
  }

  /**
   * Set how many minutes they have worked
   */
  public void setMinutesThisWeek(int minutes) {
    minutesWeek = minutes;
    //DriverInfo.setHoursThisWeek(driverId, hours);
  }

  /**
   * Set how many minutes they have worked "today"
   */
  public int getminutesThisDay() {
    return minutesDay;
  }

  /**
   * Set how many hours they have worked "today"
   */
  public void setMinutesThisDay(int minutes) {
    minutesDay = minutes;
  }

  /**
   * Set how many hours they have worked "today"
   */
  public void addMinutesThisDay(int minutes) {
    minutesWeek += minutes;
    minutesDay += minutes;
  }

  /**
   * Set how many hours they have worked "today"
   */
  public boolean checkAddMinutes(int minutes) {
    System.out.println("Minutes "+minutesDay+minutes+" minutes:"+minutesWeek+minutes);
    return minutesDay+minutes<=(MAX_DAY_HOURS*60)
            &&minutesWeek+minutes<=(MAX_WEEK_HOURS*60);
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
  public void setTimeAtStation(int time) {
    timeAtStation = time;
  }

  /**
   * Are they allowed to be there at this time
   * @param startTime
   * @return whether a valid time
   */
  boolean checkStartTime(int startTime) {
    return startTime>=timeAtStation;
  }

  /**
   * Set the time the driver will be back
   * automatically allocated break time
   * @param time
   */
  void setEndTime(int time) {
    setTimeAtStation(time + BREAK_TIME_HOURS * 60);
  }

  @Override
  public String toString() {
    return getName()+":"+getId();
  }
}
