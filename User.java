// class for User
public class User {
    // userNumber and personalIDnumber are integers
    private int userNumber;
    private int personalIDnumber;

    // checkingAccount and savingsAccount come from the BankAccount class
    private BankAccount checkingAccount;
    private BankAccount savingsAccount;

    /**
     * the user is provided with their number and PIN
     * @param anAmount
     * @param aPersonalIDNumber
     */
    public User(int anAmount, int aPersonalIDNumber) {
        userNumber = anAmount;
        personalIDnumber = aPersonalIDNumber;
        checkingAccount = new BankAccount();
        savingsAccount = new BankAccount();
    }

    /**
     * does the user match their number and PIN?
     * @param anAmount
     * @param aPersonalIDNumber
     * @return userNumber
     */
    public boolean match(int anAmount, int aPersonalIDNumber) {
        return userNumber == anAmount && personalIDnumber == aPersonalIDNumber;
    }

    /**
     * give the user their checking account
     * @return checkingAccount
     */
    public BankAccount getCheckingAccount() {
        return checkingAccount;
    }
    
    /**
     * give the user their savings account
     * @return savingsAccount
     */
    public BankAccount getSavingsAccount() {
        return savingsAccount;
    }
}