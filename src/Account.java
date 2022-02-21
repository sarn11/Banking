/**
 * The superclass of all types of bank accounts.
 * @author Tyler Sarno.
 */
public abstract class Account {
    protected Profile holder;
    protected boolean closed;
    protected double balance;

    /**
     * Check if two accounts are equal.
     * @param obj takes in a generic obj type
     * @return return true if the two accounts are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            return this.toString().equals(obj.toString());
        }
        return false;
    }

    /**
     * Convert account to a string with all its info.
     * @return returns the string
     */
    @Override
    public String toString() {
        return this.getType() + "::" + this.holder.toString() + "::" + "Balance " + this.getBalance();
    }

    /**
     * a method to take money out of an account
     * @param amount the amount you want to withdraw.
     */
    public void withdraw(double amount) {
        if (this.closed || this.balance < amount) return; //error trapping(no funds or closed account)
        this.balance = this.balance - amount;
    }

    /**
     * a method to deposit money into an account.
     * @param amount the amount to be deposited.
     */
    public void deposit(double amount) {
        if (this.closed) return; //error trapping for closed account
        this.balance = this.balance + amount;
    }

    /**
     * getter method for balance.
     * @return the balance of the account.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Method to close an account.
     * @return "account is already closed" if that's the case, and "account closed" if it hasn't
     * already previously been closed.
     */
    public String closeAccount() {
        if (this.closed) return "Account is already closed.";
        this.closed = true;
        this.balance = 0;
        return "Account closed.";
    }

    /**
     * abstract method for monthly interest
     * @return the interest as a double.
     */
    public abstract double monthlyInterest(); //return the monthly interest

    /**
     * abstract method for monthly fee.
     * @return the fee for this month.
     */
    public abstract double fee(); //return the monthly fee

    /**
     * abstract method for getting the type of account.
     * @return the account type as a string.
     */
    public abstract String getType(); //return the account type (class name)

}