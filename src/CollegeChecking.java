/**
 * A subclass of Account, defining the college checking account.
 * @author Tyler Sarno
 */
public class CollegeChecking extends Checking {
    protected int campus;

    /**
     * Constructor for CollegeChecking.
     * @param holder the owner of the account.
     * @param deposit the initial deposit.
     * @param campus the rutgers campus student is located on.
     */
    public CollegeChecking(Profile holder, Double deposit, int campus){
        this.holder = holder;
        this.balance = deposit;
        this.campus = campus;
        this.closed = false;
    }

    /**
     * Calculate the monthly interest you will receive this month with the current balance.
     * @return the interest as a double.
     */
    @Override
    public double monthlyInterest() {
        if (this.closed) return -1; //closed account
        return this.balance * .025;
    }

    /**
     * Check to see if you will receive a service fee.
     * @return return the monthly fee.
     */
    @Override
    public double fee() {
        return 0;
    }

    /**
     * Check what type of bank account you have.
     * @return the bank account type as a string.
     */
    @Override
    public String getType() {
        return "College Checking";
    }

}
