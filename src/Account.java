public abstract class Account {
    protected Profile holder;
    protected boolean closed;
    protected double balance;

    @Override
    public boolean equals(Object obj) { }

    @Override
    public String toString() { }

    public void withdraw(double amount) {
        if (this.closed || this.balance < amount) return; //error trapping(no funds or closed account)
        this.balance = this.balance - amount;
    }

    public void deposit(double amount) {
        if (this.closed) return; //error trapping for closed account
        this.balance = this.balance + amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public abstract double monthlyInterest(); //return the monthly interest

    public abstract double fee(); //return the monthly fee

    public abstract String getType(); //return the account type (class name)

}