import static org.junit.jupiter.api.Assertions.*;

/**
 * This JUnit class tests the isValid() method of the Date class
 * @author Aum Pathak, Tyler Sarno
 */

class DateTest {

    @org.junit.jupiter.api.Test
    void isValid() {
        // Test case 1, testing for leap year.
        Date date = new Date ("2/29/2018");
        assertFalse(date.isValid());

        // Test case 2, testing a date with an invalid month.
        Date date1 = new Date("13/21/1999");
        assertFalse(date1.isValid());

        // Test case 3, testing an invalid date of the month.
        Date date2 = new Date("11/32/1990");
        assertFalse(date2.isValid());

        // Test case 4, testing an invalid year.
        Date date3 = new Date("11/32/0010");
        assertFalse(date3.isValid());

        // Test case 5, testing a correct date.
        Date date4 = new Date("11/30/1990");
        assertTrue(date4.isValid());

        // Test case 5, testing a correct leap year.
        Date date5 = new Date("2/29/2016");
        assertTrue(date5.isValid());
    }
}