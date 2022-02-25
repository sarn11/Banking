public class AccountDatabase {
    private Account [] accounts; 
    private int numAcct;

    private static final int NOT_FOUND = -1;

    public AccountDatabase(){
        accounts = new Account[4];
        numAcct = 0;
    }
    
    private int find(Account account) {
        if(numAcct == 0) return NOT_FOUND;
        int size = numAcct;
        int i = 0;

        while(i<size){
            if (accounts[i].equals(account)) return i;
            else i++;
        }

        return NOT_FOUND;
    }

    private void grow() {
        int curr_len = accounts.length;
        Account [] temp;
        if (numAcct == curr_len){
            int new_len = curr_len + 4;
            temp = new Account[new_len];
            for(int i=0; i<curr_len; i++){
                temp[i] = accounts[i];
            }
            accounts = temp;
        }
    }

    public boolean open(Account account) {
        if(find(account) != NOT_FOUND && !account.closed) return false;
        if(find(account) != NOT_FOUND && account.closed){
            account.closed = false;
            return true;
        }

        if (accounts.length == numAcct) grow();
        int index = numAcct;
        accounts[index] = account;
        numAcct++;
        return true;
    }

    /**
     * Method to close an account.
     * @param  account the account that needs to be closed
     * @return false if no account present. Else, true once the account is closed
     */
    public boolean close(Account account) {
        if (find(account) == NOT_FOUND) return false;
        account.closed = true;
        account.balance = 0;
        if(account.getType().equals("Savings")){
            ((Savings)account).setLoyal(0);
        }
        return true;
    }
    public void deposit(Account account) {

    }
    public boolean withdraw(Account account) { } //return false if insufficient fund 
    
    public void print() {
        if(numAcct == 0) System.out.println("Account Database is empty!");
        for (int i = 0; i < numAcct; i++){
            System.out.println(accounts[i].toString());
        }
    }
    public void printByAccountType() {
        if(numAcct == 0) System.out.println("Account Database is empty!");
        for (int i = 0; i < numAcct; i++) {
            for (int j = i + 1; j < numAcct; j++) {
                Account temp;
                // Sort by zipCode
                if (accounts[i].getType().compareTo(accounts[j].getType()) > 0) {
                    temp = accounts[i];
                    accounts[i] = accounts[j];
                    accounts[j] = temp;
                }
                // If zipCodes are same, sort by slot
                else if(Integer.parseInt(accounts[i].getLocation().getZip()) == Integer.parseInt(accounts[j].getLocation().getZip())){
                    if(accounts[i].getSlot().compareTo(accounts[j].getSlot())>0){
                        temp = accounts[i];
                        accounts[i] = accounts[j];
                        accounts[j] = temp;
                    }
                }
            }
            //prints the sorted element of the array
            System.out.println(accounts[i]);
        }
    }
    public void printFeeAndInterest() { }
}
