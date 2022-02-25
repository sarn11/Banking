/**
 * The superclass of all types of bank accounts.
 * @author Aum Pathak, Tyler Sarno
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
        // If account type is MoneyMarket
        if (this.getType().equals("Money Market Savings")){
            return this.getType() + "::" + this.holder.toString() + "::" + "Balance $" + this.getBalance() + ((MoneyMarket)this).getLoyal() + "::" + ((MoneyMarket)this).getWithdraws();
        }
        // If account type is MoneyMarket AND it is closed
        if (this.getType().equals("Money Market Savings") && this.closed){
            return this.getType() + "::" + this.holder.toString() + "::" + "Balance $" + this.getBalance() + "::" + "CLOSED" + "::" + ((MoneyMarket)this).getWithdraws();
        }
        // If account type is CollegeChecking
        if(this.getType().equals("College Checking")){
            return this.getType() + "::" + this.holder.toString() + "::" + "Balance $" + this.getBalance() + ((CollegeChecking)this).getClosed() + "::" + ((CollegeChecking)this).getCampus().toString();
        }
        // If account type is Savings
        if (this.getType().equals("Savings")){
            return this.getType() + "::" + this.holder.toString() + "::" + "Balance $" + this.getBalance() + "::" + ((Savings)this).getLoyal();
        }
        // If account type is Savings AND it's closed
        if (this.getType().equals("Savings") && this.closed){
            return this.getType() + "::" + this.holder.toString() + "::" + "Balance $" + this.getBalance() + "::" + "CLOSED";
        }
        // If account type is Checking and it's closed
        if (this.getType().equals("Checking") && this.closed){
            return this.getType() + "::" + this.holder.toString() + "::" + "Balance $" + this.getBalance() + "::" + "CLOSED";
        }
        return this.getType() + "::" + this.holder.toString() + "::" + "Balance $" + this.getBalance();
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