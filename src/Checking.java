/**
A subclass of Account, this class defines the checking account.
@author Tyler Sarno
 */
public class Checking extends Account{

    /**
     *
     */
    public Checking (Profile holder, Double deposit) {
        this.holder = holder;
        this.balance = deposit;
        this.closed = false;
    }

    /**
     * Calculate the monthly interest you will receive this month with the current balance.
     * @return the interest as a double.
     */
    @Override
    public double monthlyInterest() {
        if (this.closed) return -1; //closed account
        return this.balance * .01;
    }

    /**
     * Check to see if you will receive a service fee (based on your current balance).
     * @return return the monthly fee.
     */
    @Override
    public double fee() {
        if (this.closed) return -1; //closed account
        if (this.balance < 1000) return 25.0;
        else return 0;
    }

    /**
     * Check what type of bank account you have.
     * @return the bank account type as a string.
     */
    @Override
    public String getType() {
        return "Checking";
    }
}
