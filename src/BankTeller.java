import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Bank Teller program to take info from the client through the console.
 * @author Tyler Sarno.
 */
public class BankTeller {
    Date today = new Date();
    public void run() {
        System.out.println("Bank Teller is running.");
        AccountDatabase db = new AccountDatabase();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String read = sc.nextLine();
            StringTokenizer input = new StringTokenizer(read,"\t ");
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

            else if (command.equals("O") || command.equals("C") || command.equals("D") || command.equals("W")) {
                if (command.equals("C") && input.countTokens() < 3) {
                    System.out.println("Missing data for closing an account");
                    continue;
                }
                if (command.equals("O") && input.countTokens() < 4) {
                    System.out.println("Missing data for opening an account");
                    continue;
                }
                Double bal = 0.0;
                String type = input.nextToken();
                String fname = input.nextToken();
                String lname = input.nextToken();
                String birth = input.nextToken();
                Date dob = new Date(birth);
                if (!dob.isValid() && dob.compareTo(today) >= 0) {
                    System.out.println("Date of birth invalid.");
                    continue;
                }
                Profile prof = new Profile(fname, lname, dob);

                if (command.equals("C")) {

                    if (type.equals("C")) {
                        Checking acc = new Checking(prof, 0.0);
                        db.close(acc);
                    }
                    if (type.equals("CC")) {
                        CollegeChecking acc = new CollegeChecking(prof, 0.0, 0);
                        db.close(acc);
                    }
                    if (type.equals("S")) {
                        Savings acc = new Savings(prof, 0.0, 0);
                        db.close(acc);
                    }
                    if (type.equals("MM")) {
                        MoneyMarket acc = new MoneyMarket(prof, 0.0);
                        db.close(acc);
                    }
                }
                int campus = -1;
                int loyalty = -1;
                if (command.equals("O")) {
                    try { bal = Double.valueOf(input.nextToken()); }
                    catch(NumberFormatException e) { System.out.println("Balance must be a number."); }
                    if (bal <= 0) {
                        System.out.println("Initial deposit cannot be 0 or negative.");
                        continue;
                    }
                    if (type.equals("C")) {
                        Checking acc = new Checking(prof, bal);
                        db.open(acc);
                    }
                    if (type.equals("CC")) {
                        if (input.hasMoreTokens()) {
                            campus = Integer.parseInt(input.nextToken());
                        }
                        if (campus < 0 || campus > 2) {
                            System.out.println("Invalid campus code.");
                            continue;
                        }
                        CollegeChecking acc = new CollegeChecking(prof, bal, campus);
                        db.open(acc);
                    }
                    if (type.equals("S")) {
                        loyalty = Integer.parseInt(input.nextToken());
                        if (loyalty != 0 && loyalty != 1) {
                            System.out.println("Loyalty code is invalid.");
                            continue;
                        }
                        Savings acc = new Savings(prof, bal, loyalty);
                        db.open(acc);
                    }
                    if (type.equals("MM")) {
                        if (bal < 2500) {
                            System.out.println("Minimum of $2500 to open a MoneyMarket account.");
                            continue;
                        }
                        MoneyMarket acc = new MoneyMarket(prof, bal);
                        int x = db.find(acc);
                        if (x >= 0)
                        db.open(acc);
                    }
                }
            }

            else {
                System.out.println("Invalid Command!");
            }
        }
        System.out.println("Bank Teller is terminated.");

    }
    public static void main(String[] args) {
        BankTeller bt = new BankTeller();
        bt.run();
    }
}
