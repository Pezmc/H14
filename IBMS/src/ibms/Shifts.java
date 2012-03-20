package ibms;

import java.util.ArrayList;

/**
 *
 * @author pezcuckow
 */
public class Shifts {
  private ArrayList<int[]> hours = new ArrayList<int[]>();
  
  /**
   * Add a shift to the object
   * @param start Start of the shift in mins past midnight
   * @param end End of the shift in mins past midnight 
   */
  void addShift(int start, int end) {
    int[] time = new int[2];
    time[0] = start;
    time[1] = end;
    hours.add(time);
  }
  
  /**
   * Check whether the object is allowed to do another shift today
   * @param start when the shift would start
   * @param end when the shift would end
   * @param margin required margin (mins)
   * @return boolean true/false whether driver can work
   */ 
  public boolean checkShift(int start, int end, int margin) {
    for(int[] shift : hours) {
      //end during other shift...
      //if the start - end and the end - start are both possitive
      //  or they are both negative then it is on either side
      //  so if NOT return false
      if(!(shift[0]-end>margin&&shift[1]-start>margin
              ||shift[0]-end<-margin&&shift[1]-start<-margin)) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Delete all the shifts done
   */
  public void clearShifts() {
    hours = new ArrayList<int[]>();
  }
   
  /**
   * Move all the shifts forwards a day (mostly delete them)
   */
  public void nextDayShifts() {
    int i;
    for(i = 0; i < hours.size(); i++) {
      //System.out.println("for"+i);
      int[] shift = hours.get(i);
      if(shift[0]<1440&&shift[1]<1440) {
        //System.out.println("Delete "+i);
        hours.remove(i); //remove
        i--; //cope with shrinking array
      } else {
        if(shift[0]>=1440) shift[0] -= 1440;
        else shift[0] = 0;
        //System.out.println("ShiftStart: "+shift[0]);
        
        if(shift[1]>=1440) shift[1] -= 1440;
        //System.out.println("ShiftEnd: "+shift[1]);
        hours.set(i, shift); //update
      }
    }
  }
  
  /**
   * Print out all the shifts a driver has...
   */
  public void printShifts() {
    for(int[] shift : hours) {
      System.out.println("Start: "+Util.minToTime(shift[0])
                         + " End: "+Util.minToTime(shift[1]));
    }
  }  
}
