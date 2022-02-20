import java.util.Calendar;
import java.util.StringTokenizer;
/**
 This class defines the date object, as well as methods to use with it.
 @author Aum Pathak
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public Date(String date) { //take “mm/dd/yyyy” and create a Date object
        StringTokenizer s = new StringTokenizer(date, "/");
        if (s.countTokens() != 3) throw new IllegalArgumentException();
        int mm = Integer.parseInt(s.nextToken());
        int dd = Integer.parseInt(s.nextToken());
        int yy = Integer.parseInt(s.nextToken());

        this.year = yy;
        this.month = mm;
        this.day = dd;
    }

    public Date() { //create an object with today’s date (see Calendar class)
        Calendar TODAY = Calendar.getInstance();
        this.day = TODAY.get(Calendar.DATE);
        this.month = TODAY.get(Calendar.MONTH)+1;
        this.year = TODAY.get(Calendar.YEAR);

    }

    //checking if the date is a leap year
    public static boolean isLeap(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return true;
        }
    }
    // convert date to string
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        if (this.month < 10) s.append("0");
        s.append(String.valueOf(this.month));
        s.append("/");
        if (this.day < 10) s.append("0");
        s.append(String.valueOf(this.day));
        s.append("/");
        s.append(String.valueOf(this.year));

        return s.toString();
    }

    //checking if the date is valid
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

    //compareTo method for Date
    @Override
    public int compareTo(Date date) {
        int day1 = date.getDay();
        int month1 = date.getMonth();
        int year1 = date.getYear();

        if(year1 > this.year)
            return -1;
        if(year1 < this.year)
            return 1;
        if(month1 > this.month)
            return -1;
        if(month1 < this.month)
            return 1;
        if(day1 > this.day)
            return -1;
        if(day1 < this.day)
            return 1;
        return 0;

    }

    public static void main(String[] args) {
        // get today
        Date today = new Date();
        System.out.println(today.toString());

        // tomorrow
        Date tomorrow = new Date("02/07/2021");
        System.out.println(tomorrow.isValid());
        System.out.println(today.compareTo(tomorrow)); // today is earlier than tomorrow

        // 2013 is not leap year
        System.out.println(Date.isLeap(2016) ? "is leap": "is not leap");

        Date rando = new Date("21/21/2050");
        System.out.println(rando.isValid());
    }
}