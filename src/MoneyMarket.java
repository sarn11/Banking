/**
 * A subclass of Savings, defining the Money Market account.
 * @author Aum Pathak, Tyler Sarno
 */
public class MoneyMarket extends Savings{
    private int withdraws;
    /**
     * Constructor method for a MoneyMarket account.
     * @param holder the owner of the account.
     * @param deposit the initial deposit for the account.
     */
    public MoneyMarket (Profile holder, Double deposit) {
        this.holder = holder;
        this.balance = deposit;
        this.closed = false;
        this.withdraws = 0;
    }

    public void incrementWithdraws(){
        this.withdraws++;
    }

    /**
     * Calculate the monthly interest you will receive this month with the current balance.
     * @return the interest as a double.
     */
    public double monthlyInterest() {
        if (this.closed) return -1; //closed account
        if (this.balance < 2500) return this.balance * (.008/12);
        return this.balance * (.0095/12);
    }

    /**
     * Check to see if you will receive a service fee (based on your current balance).
     * @return return the monthly fee.
     */
    public double fee() {
        if (this.closed) return -1; //closed account
        if (this.balance < 2500) return 10.0;
        else if (this.withdraws > 3) return 10.0;
        else return 0;
    }

    /**
     * Check what type of bank account you have.
     * @return the bank account type as a string.
     */
    public String getType() {
        return "MoneyMarket";
    }

}
