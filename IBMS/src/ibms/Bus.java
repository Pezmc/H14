/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibms;

import java.util.ArrayList;

/** 
 *
 * @author pezcuckow
 */
class Bus extends Shifts {
  private static int[] buses = BusInfo.getBuses();
  
  private int busId = -1;
  private int minutesWeek = 0;
  private int minutesDay = 0;
  private int timeAtStation = -1;
  
  private String busNumber = ""; 
  
  
  /* Reference by driver id */
  public Bus(int id) {
    //Check the bus actually exists
    for (int i = 0; i < buses.length; i++) {
      if(id == buses[i]) {
        busId = id;
      } // if
    } // for

    if(busId==-1)
       throw new IllegalArgumentException("Bus id "+id+" doesn't exist");
    
    //Update id
    busNumber = BusInfo.busNumber(busId);
  }
  
  public String getBusNumber() {
    return busNumber;
  }
  
  @Override
  public String toString() {
    return getBusNumber();
  }
}
