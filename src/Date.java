import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * This class defines the date object, as well as methods to check its validity, etc.
 *
 * @author Aum Pathak, Tyler Sarno
 */
public class Date implements Comparable<Date> {
    private final int year;
    private final int month;
    private final int day;

    /**
    getter method for day
    @return int value for day
     */
    public int getDay() {
        return this.day;
    }

    /**
    getter method for month
    @return the int value for the month
     */
    public int getMonth() {
        return this.month;
    }

    /**
    getter method for year
    @return the int value for the year
     */
    public int getYear() {
        return this.year;
    }

    /**
    Constructor for the date object where the user can specify a certain day.
    @param date takes in the string form of a date
     */
    public Date(String date) {
        StringTokenizer s = new StringTokenizer(date, "/");
        if (s.countTokens() != 3) throw new IllegalArgumentException();
        this.month = Integer.parseInt(s.nextToken());
        this.day = Integer.parseInt(s.nextToken());
        this.year = Integer.parseInt(s.nextToken());
    }

    /**
    constructor without parameters for the date object
    it automatically sets the date to today's date
     */
    public Date() {
        Calendar TODAY = Calendar.getInstance();
        this.day = TODAY.get(Calendar.DATE);
        this.month = TODAY.get(Calendar.MONTH) + 1;
        this.year = TODAY.get(Calendar.YEAR);
    }

    /**
    Check to see if a certain year is a leap year
    @param year takes in an int value for the year
    @return true if it is a leap year, false otherwise
     */
    public static boolean isLeap(int year) {
        if (year % 4 != 0) return false;
        else if (year % 400 == 0) return true;
        else return year % 100 != 0;
    }

    /**
    convert date to string
    @return the date in the form of a string
    */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        if (this.month < 10) s.append("0");
        s.append(this.month);
        s.append("/");
        if (this.day < 10) s.append("0");
        s.append(this.day);
        s.append("/");
        s.append(this.year);

        return s.toString();
    }

    /**
    checking if the date is valid
    @return true if the date is valid and false otherwise
     */
    public boolean isValid() {
        if (year < 0) return false;
        if ((month < 1) || (month > 12)) return false;
        if ((day < 1) || (day > 31)) return false;
        return switch (month) {
            case 2 -> (isLeap(year) ? day <= 29 : day <= 28);
            case 4, 6, 9, 11 -> day < 31;
            default -> true;
        };
    }

    /**
    compareTo method for the Date object
    @param date the date you want to compare
    @return 1 if the date is greater, -1 if the date is less than, and 0
    if the dates are the same
     */
    @Override
    public int compareTo(Date date) {
        int day1 = date.getDay();
        int month1 = date.getMonth();
        int year1 = date.getYear();

        if (year1 > this.year)
            return -1;
        if (year1 < this.year)
            return 1;
        if (month1 > this.month)
            return -1;
        if (month1 < this.month)
            return 1;
        if (day1 > this.day)
            return -1;
        if (day1 < this.day)
            return 1;
        return 0;

    }

    public static void main(String[] args) {
//        // get today
//        Date today = new Date();
//        System.out.println(today);
//
//        // tomorrow
//        Date tomorrow = new Date("02/07/2021");
//        System.out.println(tomorrow.isValid());
//        System.out.println(today.compareTo(tomorrow)); // today is earlier than tomorrow
//
//        // 2013 is not leap year
//        System.out.println(Date.isLeap(2013) ? "is leap" : "is not leap");
//
//        Date rando = new Date("21/21/2050");
//        System.out.println(rando.isValid());

        Profile x1 = new Profile("Tyler", "Sarno", "02/22/1990");
        System.out.println(x1);
        Profile x2 = new Profile("Aum", "Pathak", "6/13/1989");
        Profile x3 = new Profile("Tyler", "Sarno", "02/22/1990");
        System.out.println(x1.equals(x2));
        System.out.println(x2);
        System.out.println(x1.equals(x3));


    }
}