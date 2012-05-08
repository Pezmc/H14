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


    public static String getDelay(double delayNum, String route, String busStopSelection) {

        String message = "";
        if (delayNum < 1) {
                message += "There has been an accident on route " + route + " ";
                message += "after " + busStopSelection + ".";
            } else if (delayNum < 2 && delayNum > 1) {
                message += "There has been a crash involving a cyclist on the " + route + " route.";
            } else if (delayNum < 3 && delayNum > 2) {
                message += "A plane has hit the bus! ";
            } else if (delayNum < 4 && delayNum > 3) {
                message += "The bus that was supposed to be at this stop has broken down";

            }else if (delayNum < 5 && delayNum > 4) {
                message += "A herd of cows has blocked the road!";
            }else if (delayNum < 6 && delayNum > 5) {
                message += "The bus driver is running late!";

            }else if (delayNum < 7 && delayNum > 6) {
                message += "A new bus is being driven to collect you as the old bus fell apart.";

            }else if (delayNum < 8 && delayNum > 7) {
                message += "The bus that was supposed to be at this stop has broken down";

            }else if (delayNum < 9 && delayNum > 8) {
                message += "There has been an accident on route " + route + " ";
                message += "after " + busStopSelection + ", ";

            }else if (delayNum < 10 && delayNum > 9) {
                message += "There has been an accident on route " + route + " ";
                message += "after " + busStopSelection + ", ";
            }

        return message;

    }

}

