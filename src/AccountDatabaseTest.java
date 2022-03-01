import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This JUnit class tests the open() and close() method of the AccountDatabase class
 * @author Aum Pathak, Tyler Sarno
 */

class AccountDatabaseTest {

    AccountDatabase db = new AccountDatabase();
    
    // Money market account
    Profile holder = new Profile("Mike", "Ross", new Date("01/11/1992"));
    double bal = 3200.00;
    MoneyMarket mm = new MoneyMarket(holder, bal);

    // Savings account
    Profile s_Holder = new Profile("James", "Ross", new Date("02/21/2002"));
    double bal_s = 3200.00;
    Savings ss = new Savings(s_Holder, bal_s, 1);

    // Checking account
    Profile c_holder = new Profile("Apple", "Seed", new Date("11/10/2000"));
    double bal_c = 3200.00;
    Checking checking = new Checking(c_holder, bal_c);

    // College checking account
    Profile cc_holder = new Profile("Alpha", "Gamma", new Date("02/29/1996"));
    double bal_cc = 3200.00;
    CollegeChecking cc = new CollegeChecking(cc_holder, bal_cc, 0);
    
    @Test
    void open() {
        // Test case 1, opening a MoneyMarket account.
        assertTrue(db.open(mm));
        
        // Test case 2, trying to open the same MoneyMarket account as test case 1.
        assertFalse(db.open(mm));
        
        // Test case 3, opening a Savings account.
        assertTrue(db.open(ss));

        // Test case 4, trying to open the same Savings account as test case 3.
        assertFalse(db.open(ss));

        // Test case 5, trying to close and then reopen the Savings account.
        assertTrue(db.close(ss));
        assertTrue(db.open(ss));

        // Test case 6, opening a Checking account.
        assertTrue(db.open(checking));

        // Test case 7, trying to open the same Checking account as test case 6.
        assertFalse(db.open(ss));

        // Test case 8, opening a CollegeChecking account.
        assertTrue(db.open(cc));

        // Test case 9, trying to close and then reopen the CollegeChecking account.
        assertTrue(db.close(cc));
        assertTrue(db.open(cc));


    }

    @Test
    void close() {
        // Test case 1, trying to close a non-existent Savings account.
        assertFalse(db.close(ss));

        // Test case 2, opening the Savings account and then closing it.
        assertTrue(db.open(ss));
        assertTrue(db.close(ss));

        // Test case 3, trying to close a non-existent Checking account.
        assertFalse(db.close(checking));

        // Test case 4, closing the CollegeChecking account.
        assertFalse(db.close(cc));

        // Test case 5, opening the CollegeChecking account and then closing it.
        assertTrue(db.open(cc));
        assertTrue(db.close(cc));

        // Test case 6, closing the already closed savings account.
        assertFalse(db.close(ss));

        // Test case 7, opening the MoneyMarket account and then closing it.
        assertTrue(db.open(mm));
        assertTrue(db.close(mm));

    }
}