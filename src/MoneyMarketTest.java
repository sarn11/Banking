import org.junit.jupiter.api.Test;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This JUnit class tests the monthlyInterest() method of the MoneyMarket class
 * @author Aum Pathak, Tyler Sarno
 */

class MoneyMarketTest extends Savings {

    @Test
    void testMonthlyInterest() {
        Profile holder = new Profile("Mike", "Ross", new Date("01/11/1992"));
        double bal = 3200.00;
        MoneyMarket acc = new MoneyMarket(holder, bal);
        DecimalFormat fmt = new DecimalFormat("###,##0.00");
        String monInt = fmt.format(acc.monthlyInterest());

        // Test case 1, monthly interest of this loyal account is 2.53.
        assertEquals(monInt,"2.53");

        // Test case 2, wrong monthly interest of the same account
        assertNotEquals(monInt,"2.63");

        holder = new Profile("James", "Ross", new Date("05/01/2012"));
        bal = 2400.00;
        MoneyMarket acc2 = new MoneyMarket(holder, bal);
        monInt = fmt.format(acc2.monthlyInterest());

        // Test case 3, monthly interest of this non-loyal account is 1.60.
        assertEquals(monInt,"1.60");

        // Test case 4, wrong monthly interest of the same account
        assertNotEquals(monInt,"2.60");

    }
}