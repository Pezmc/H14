/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibms;

/**
 *
 * @author pezcuckow
 */
public class TestDriver {
  public static void main(String[] args) {
    //opendb
    database.openBusDatabase();
    
    Driver driver1 = new Driver(2025);
    Driver driver2 = new Driver(2026);
    Driver driver3 = new Driver(2042);
    
    driver1.addShift(0, 60);
    driver1.addShift(1430, 1500);
    
    driver1.printShifts();
    
    System.out.println("Test1: "+driver1.checkShift(10, 40));
    System.out.println("Test2: "+driver1.checkShift(-20, 30));
    System.out.println("Test3: "+driver1.checkShift(30, 70));
    System.out.println("Test4: "+driver1.checkShift(-20, 100));
    System.out.println("Test5: "+driver1.checkShift(30, 50));
    System.out.println("Test6: "+driver1.checkShift(0, 60));
    System.out.println("Test7: "+driver1.checkShift(100, 200));
    System.out.println("Test8: "+driver1.checkShift(60, 80));
    System.out.println("Test9: "+driver1.checkShift(70, 80, 20));
    
    driver1.nextDayShifts();
    
    driver1.printShifts();
    
    System.out.println("Test1: "+driver1.checkShift(0, 20)); //false
    System.out.println("Test2: "+driver1.checkShift(100, 200)); //true
    
     
  }
}
