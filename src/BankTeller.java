import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Bank Teller program to take info from the client through the console.
 * @author Tyler Sarno.
 */
public class BankTeller {
    Date today = new Date();
    AccountDatabase db = new AccountDatabase();

    /**
     * Check if you are reopening an account.
     * @param account the account you are searching for.
     * @return returns -1 if no duplicate has been found, returns the index of the dupe account otherwise.
     */
    private int isReopen(Account account) {
        int numAcct = db.getNumAcct();
        if(numAcct == 0) return -1;
        Account[] accounts = db.getAccounts();
        for (int i = 0; i < numAcct; i++) {
            if (accounts[i].equals(account) && accounts[i].closed) return -2;
            if (accounts[i].equals(account)) return i;
        }
        return -1;
    }

    public void createMM(String command, Profile holder, double bal) {
        if (command.equals("D")) {
            MoneyMarket acc = new MoneyMarket(holder, bal);
            if (isReopen(acc) == -2) {
                System.out.println("Cannot deposit into a closed account.");
                return;
            }
            if (isReopen(acc) == -1) {
                System.out.println(holder + " Money Market is not in the database.");
                return;
            }
            db.deposit(acc);
            System.out.println("Deposit - balance updated.");
        }
        if (command.equals("W")) {
            MoneyMarket acc = new MoneyMarket(holder, bal);
            if (acc.balance <= 0) {
                System.out.println("Withdraw - amount cannot be 0 or negative.");
                return;
            }
            boolean b = db.withdraw(acc);
            if (isReopen(acc) == -2 || !b) {
                System.out.println("Withdraw - insufficient funds.");
                return;
            }
            System.out.println("Withdraw - balance updated.");
        }
        if (command.equals("C")) {
            MoneyMarket acc = new MoneyMarket(holder, 0.0);
            if (isReopen(acc) == -2) System.out.println("Account is closed already.");
            if (isReopen(acc) == -1) System.out.println(holder + " Money Market is not in the database.");
            if (isReopen(acc) >= 0) {
                System.out.println("Account closed.");
                db.close(acc);
            }
        }
        if (command.equals("O")){
            if (bal < 2500) {
                System.out.println("Minimum of $2500 to open a MoneyMarket account.");
            }
            else {
                MoneyMarket acc = new MoneyMarket(holder, bal);
                if (isReopen(acc) == -2) {
                    System.out.println("Account reopened.");
                }
                else if(isReopen(acc) >= 0) {
                    System.out.println(holder + " same account(type) is in the database.");
                    return;
                }
                else System.out.println("Account opened");
                db.open(acc);
            }
        }

    }

    public void createAccount(StringTokenizer input, String command) {

        if (input.countTokens() < 5) {
            System.out.println("Missing data for opening an account.");
            return;
        }
        String type = input.nextToken();
        String fname = input.nextToken();
        String lname = input.nextToken();
        String birth = input.nextToken();
        double bal = 0;

        try {
            bal = Double.parseDouble(input.nextToken());
        }
        catch (NumberFormatException e){
            System.out.println("Invalid balance.");
        }
        if (!Date.validFormat(birth)) {
            System.out.println("Date of birth invalid.");
            return;
        }
        Date dob = new Date(birth);
        if (!dob.isValid() || dob.compareTo(today) >= 0) {
            System.out.println("Date of birth invalid.");
            return;
        }
        if (bal <= 0 && command.equals("O")) {
            System.out.println("Initial deposit cannot be 0 or negative.");
            return;
        }
        if (bal <= 0 && command.equals("D")){
            System.out.println("Deposit - amount cannot be 0 or negative.");
            return;
        }
        Profile holder = new Profile(fname, lname, dob);
//        if (type.equals("C")) {
//            Checking acc = new Checking(holder, bal);
//            db.open(acc);
//        }
        if (type.equals("MM")) createMM(command, holder, bal);


    }

    public void run() {
        System.out.println("Bank Teller is running.");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String read = sc.nextLine();
            StringTokenizer input = new StringTokenizer(read, "\t ");
            //handles no such element
            if (!input.hasMoreTokens()) {
                System.out.println("Invalid Command!");
                continue;
            }
            String command = input.nextToken();

            if (command.equals("Q")) break;
            else if (command.equals("P")) db.print();
            else if (command.equals("PT")) db.printByAccountType();
            else if (command.equals("PI")) db.printFeeAndInterest();
            else if (command.equals("UB")) db.printUpdatedBalances();
            else if (!command.equals("O") && !command.equals("C") && !command.equals("D") && !command.equals("W")) {
                System.out.println("Invalid Command!");
            }
            else createAccount(input, command);

        }
        System.out.println("Bank Teller is terminated.");
    }
    public static void main(String[] args) {
        BankTeller bt = new BankTeller();
        bt.run();
    }
}