import java.text.DecimalFormat;
/**
 * A class that holds and organizes all the bank accounts in the database.
 * @author Tyler Sarno, Aum Pathak.
 */
public class AccountDatabase {
    private Account [] accounts;
    private int numAcct;

    /**
     * Constructor for AccountDatabase.
     */
    public AccountDatabase(){
        accounts = new Account[4];
        numAcct = 0;
    }

    /**
     * Searches the database to see if there is a duplicate account.
     * @param account the account you are searching for.
     * @return returns -1 if no duplicate has been found, returns the index of the dupe account otherwise.
     */
    public int find(Account account) {
        if(numAcct == 0) return -1;

        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account)) return i;
        }
        return -1;
    }

    /**
     * if the database array has reached capacity, expand it by 4 slots.
     */
    private void grow() {

        Account[] temp = new Account[numAcct+4];
        for (int i = 0; i < numAcct; i++) {
            temp[i] = accounts[i];
        }
        accounts = temp;
    }

    /**
     * open an account and add it to the database.
     * @param account the account you want to add.
     * @return return true if acc opening successful, false otherwise.
     */
    public boolean open(Account account) {
        int accIndex = find(account);
        System.out.println(accIndex);
        if (accIndex >= 0 && !accounts[accIndex].closed) return false; //acc already exits and is not closed.
        if (accIndex >= 0 && accounts[accIndex].closed) return false; //acc needs to be reopened (handle in the console).

        if (accounts.length == numAcct) this.grow();
        accounts[numAcct] = account; //numAcct SHOULD be the right index every time.
        numAcct++;
        return true;
    }

    /**
     * Method to close an account.
     * @param  account the account that needs to be closed
     * @return false if no account present. Else, true once the account is closed
     */
    public boolean close(Account account) {
        int index = find(account);
        if (index == -1) return false;
        accounts[index].closed = true;
        accounts[index].balance = 0;
        if(accounts[index].getType().equals("Savings")){
            ((Savings)accounts[index]).setLoyal(0);
        }
        return true;
    }

    /**
     * deposit money into an account.
     * @param account the account you want to deposit money into.
     */
    public void deposit(Account account) {
        int index = find(account);
        if (index == -1) {
            System.out.println(account.holder.toString() + " " + account.getType() + " is not in the database.");
        }

        if (index >= 0 && accounts[index].closed) {
            System.out.println(account.holder.toString() + " " + account.getType() + " has been closed, deposit failed.");
        }

        accounts[index].deposit(account.balance);
        System.out.println("Deposit - balance updated.");
    }

    /**
     * withdraw money from an account.
     * @param account the account you are withdrawing from.
     * @return return true if the withdrawal was successful, false otherwise.
     */
    public boolean withdraw(Account account) {
        int index = find(account);
        if (index == -1) {
            System.out.println(account.holder.toString() + " " + account.getType() + " is not in the database.");
            return false;
        }
        if (index >= 0 && accounts[index].closed) {
            System.out.println(account.holder.toString() + " " + account.getType() + " has been closed, withdrawal failed.");
            return false;
        }

        if (account.balance > accounts[index].balance) return false;

        accounts[index].withdraw(account.balance);

        return true;
    }

    /**
     * print all the accounts in the database.
     */
    public void print() {
        if(numAcct == 0) System.out.println("Account Database is empty!");
        for (int i = 0; i < numAcct; i++){
            System.out.println(accounts[i].toString());
        }
    }

    /**
     * method that sorts the database for printing (uses insertion sort).
     */
    private void sortDatabase() {

        for (int i = 1; i < numAcct; i++) {

            Account temp = accounts[i];
            int j = i - 1;
            while (j >= 0 && accounts[j].getType().compareTo(temp.getType()) > 0) {
                accounts[j + 1] = accounts[j];
                j--;
            }
            accounts[j + 1] = temp;
        }
    }

    /**
     * print all the accounts in the database organized by the type.
     */
    public void printByAccountType() {
        if(numAcct == 0) System.out.println("Account Database is empty!");

        sortDatabase();
        print();
    }

    public void printFeeAndInterest() {
        if(numAcct == 0) System.out.println("Account Database is empty!");

        DecimalFormat fmt = new DecimalFormat("###,##0.00");
        String fee;
        String monthlyInt;

        for (int i = 0; i < numAcct; i++){
            fee = fmt.format(accounts[i].fee());
            monthlyInt = fmt.format((accounts[i].monthlyInterest()));
            System.out.println(accounts[i].toString() + "::" + "fee $" + fee + "::" + "monthly interest $" + monthlyInt);
        }
    }

    /**
     * Update the balance of all the accounts in the database (that aren't closed).
     */
    public void printUpdatedBalances() {

        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].closed) continue;
            accounts[i].deposit(accounts[i].monthlyInterest());
            accounts[i].balance = accounts[i].balance - accounts[i].fee();
            if (accounts[i].getType().equals("Money Market Savings") && accounts[i].balance < 2500) {
                ((MoneyMarket)accounts[i]).setLoyal(0);
            }
        }
        this.print();
    }
}
