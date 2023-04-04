// class for ATM
public class ATM {
// inevitable variables are set
    public static final int BANKTYPEONE = 1;    // checking account
    public static final int BANKTYPETWO = 2;    // savings account
    private int condition;
    private int userNumber;
    private User currentUser;
    private BankAccount workingAccount;
    private Bank theDealer;
    public static final int INITIATE = 1;       // introduction in ATMSimulator.java
    public static final int PERSONAL_IDENTIFICATION_NUMBER = 2;     // ask for PIN number in ATMSimulator.java
    public static final int BANK_TYPE = 3;   // ask for bank type in ATMSimulator.java
    public static final int BANK_TRANSACTION = 4;      // start transaction in ATMSimulator.java

    /**
     * ATM constructor builds the bank's ATM
     * @param aDealer
     */
    public ATM(Bank aDealer) {
        theDealer = aDealer;
        reset();
    }

    // reset method send the ATM back to where it starts
    public void reset() {
        userNumber = -1;
        workingAccount = null;
        condition = INITIATE;
    }

    /**
     * setUserNumber method gets the user's current number ready and sets the condition to the PIN
     * @param amount
     */
    public void setUserNumber(int amount) {
        assert condition == INITIATE;
        userNumber = amount;
        condition = PERSONAL_IDENTIFICATION_NUMBER;
    }

    /**
     * pickPurchaser method looks for the user
     * @param personalIDnumber
     */
    public void pickPurchaser(int personalIDnumber) {
        assert condition == PERSONAL_IDENTIFICATION_NUMBER;
        currentUser = theDealer.LookForPurchaser(userNumber, personalIDnumber);

        // ask the user for their bank account number if they're found
        if(currentUser == null) {
            condition = INITIATE;
        } else {
            condition = BANK_TYPE;
        }
    }

    /**
     * selectAccount method allows user to choose checking or savings account before the discharge occurs
     * @param account
     */
    public void selectAccount(int account) {
        assert condition == BANK_TYPE || condition == BANK_TRANSACTION;
        if(account == BANKTYPEONE) {
            workingAccount = currentUser.getCheckingAccount();
        } else {
            workingAccount = currentUser.getSavingsAccount();
        }
        condition = BANK_TRANSACTION;
    }

    /**
     * withdraw
     * @param value
     */
    public void withdraw(double value) {
        assert condition == BANK_TRANSACTION;
        workingAccount.withdraw(value);
    }

    /**
     * deposit
     * @param value
     */
    public void deposit(double value) {
        assert condition == BANK_TRANSACTION;
        workingAccount.deposit(value);
    }

    /**
     * transfer
     * @param amount
     */
    public void transfer(double amount, int accountNumber) {
        assert condition == BANK_TRANSACTION;
        workingAccount.withdraw(amount);
        this.getAccount(accountNumber).deposit(amount);
    }

    /**
     * get the account's balance
     * @return balance
     */
    public double getBalance() {
        assert condition == BANK_TRANSACTION;
        return workingAccount.getBalance();
    }

    // go back to the bank account type options
    public void back() {
        if(condition == BANK_TRANSACTION) {
            condition = BANK_TYPE;
        } else if(condition == BANK_TYPE) {
            condition = PERSONAL_IDENTIFICATION_NUMBER;
        } else if(condition == PERSONAL_IDENTIFICATION_NUMBER) {
            condition = INITIATE;
        }
    }

    /**
     * get the ATM's current condition
     * @return condition
     */
    public int getCondition() {
        return condition;
    }

    /**
     * getAccount method helps with transferring funds
     * @param accountNumber
     * @return currentUser
     */
    private BankAccount getAccount(int accountNumber) {
        if(accountNumber == BANKTYPEONE) {
            return currentUser.getCheckingAccount();
        } else {
            return currentUser.getSavingsAccount();
        }
    }
}