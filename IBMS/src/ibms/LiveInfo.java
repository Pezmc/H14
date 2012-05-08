/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ibms;

/**
 *
 * @author Ben
 */
public class LiveInfo {

    public static String getDelay(double delayNum, int route, String busStopSelection) {

        String message = "";
        if (delayNum < 1) {
                message += "There has been an accident on route " + route + " ";
                message += "after \n" + busStopSelection + ". ";

            } else if (delayNum < 2 && delayNum > 1) {
                message += "There has been a crash involving a cyclist on the " + route + " \nroute.";
            } else if (delayNum < 3 && delayNum > 2) {
                message += "A plane has hit the bus! ";
            } else if (delayNum < 4 && delayNum > 3) {
                message += "The bus that was supposed to be at this stop has \nbroken down.";
            }else if (delayNum < 5 && delayNum > 4) {
                message += "A herd of cows has blocked the road!";
            }else if (delayNum < 6 && delayNum > 5) {
                message += "The bus driver is running late!";
            }else if (delayNum < 7 && delayNum > 6) {
                message += "A new bus is being driven to collect you as the old \nbus fell apart.";
            }else if (delayNum < 8 && delayNum > 7) {
                message += "The bus that was supposed to be at this stop has \nbroken down.";
            }else if (delayNum < 9 && delayNum > 8) {
                message += "There has been an accident on route " + route + " ";
                message += "after \n" + busStopSelection + ". ";
            }else if (delayNum < 10 && delayNum > 9) {
                message += "There has been an accident on route " + route + " ";
                message += "after \n" + busStopSelection + ". ";
            }

        return message;
    }

    public static String cancellation(double probability, int [] time, int route, String busStopSelection) {
        String message = "";
        double delay = 60 * Math.random();

        //there has been a delay
        //pick a random statement to print out regarding a delay
        double delayNum = Math.random() * 10;

        //update the string with the delay information
        message += "" + getDelay(delayNum, route, busStopSelection);
        message += "\nWhich has lead to a delay of " + (int)delay + " mins.";

        return message;
    }

    public static String noCancellation (double probability, int [] time, int route, String busStopSelection) {
        //give the next available bus from the timetable at the bus stop
        //get the timing points for the route selected
        String message = "";
        int noServices = TimetableInfo.getNumberOfServices(route);
        System.out.println("Number of services: " + noServices);
        int [] services = TimetableInfo.getServices(route, TimetableInfo.timetableKind(database.today()));
        for (int i = 0; i < services.length; i ++) {
            System.out.println("Service [" + services[i] + "]");
        }

        String time_s = "" + time[0] + time[1] + time[2] + time[3];
        int timeNum = Integer.parseInt(time_s);
        int nextTime = 0;
        int tempTime = 0;

        for (int i = 0; i < services.length; i ++) {
            int timings[] = TimetableInfo.getServiceTimes(route, services[i]);
            for (int j = 0; j < timings.length; j ++) {
                if (timings[j] >= timeNum) {
                    tempTime = timings[j];
                    break;
                }
            }
            if (tempTime < nextTime) {
                nextTime = tempTime;
            }
        }

        //the raw time is the time given turned into time that the database can handle
        int rawTime = (time[0]*60*10) + (time[1]*60) + (time[2]*10 + time[3]);

        //System.out.println("The raw time I have calculated is: " + rawTime);
        

        message += "There is no delay on route " + route + ".\n";
        message += "The next available bus at " + busStopSelection + " \nwill arrive at at: " + nextTime + ".";

        return message;
    }

}

