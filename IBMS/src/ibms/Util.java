package ibms;
 
/**
 * Utilities used for the rest of the project
 * Useful little snippits
 * @author cuckowp0
 */
public class Util {
  /**
   * Convert a time in min to a string
   * @param min Min past mightnight
   * @return 00:00
   */
  public static String minToTime(int min) {
    int hours = min / 60;
    min = min % 60;

    return String.format("%02d:%02d", hours, min);
  }

  /**
   * Convert a day of week 0-6 to the timetable kind
   * @param dayOfWeek
   * @return The string for that day e.g. "Monday"
   */
  public static String dowToString(int dayOfWeek) {
    switch(dayOfWeek) {
      case 0: return "Monday";
      case 1: return "Tuesday";
      case 2: return "Wednesday";
      case 3: return "Thrsday";
      case 4: return "Friday";
      case 5: return "Saturday";
      case 6: return "Sunday";
      default:
        throw new IllegalArgumentException("The day of the week "
                                           + dayOfWeek + " doesn't exist!");
    }
  }

  /**
   * Convert a day of week 0-6 to the timetable kind
   * @return weekday, saturday, sunday object
   */
  public static TimetableInfo.timetableKind dowToKind(int dayOfWeek) {
    TimetableInfo.timetableKind dayType;
    if(dayOfWeek<=4) //week day
        dayType = TimetableInfo.timetableKind.weekday;
    else if(dayOfWeek==5) //sat
        dayType = TimetableInfo.timetableKind.saturday;
    else if(dayOfWeek==6) //sun
        dayType = TimetableInfo.timetableKind.sunday;
    else
        throw new IllegalArgumentException("The day of the week "
                                           + dayOfWeek + " doesn't exist!");
    return dayType;
  }
}
