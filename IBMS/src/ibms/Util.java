package ibms;

/**
 *
 * @author cuckowp0
 */
public class Util {
  public static String minToTime(int min) {
    int hours = min / 60;
    min = min % 60;

    return String.format("%02d:%02d", hours, min);
  }
}
