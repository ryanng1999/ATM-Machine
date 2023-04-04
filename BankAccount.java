// class for BankAccount
public class BankAccount {
    // balance is doubled
    private double balance;

    // BankAccount() is declared as public
    public BankAccount() {
        // initial balance is $0.00
        balance = 0;
    }

    /**
     * bank account has a specified balance
     * @param startingBalance
     */
    public BankAccount(double startingBalance) {
        // balance is set
        balance = startingBalance;
    }
    
    /**
     * insert the amount into the bank account
     * @param amount
     */
    public void deposit(double amount) {
        balance = balance + amount;
    }

    /**
     * amount is taken from the bank account
     * @param amount
     */
    public void withdraw(double amount) {
        balance = balance - amount;
    }

    /**
     * amount is taken from the original bank account
     * @param amount
     */
    public void transfer(double amount) {
        balance = balance - amount;
    }

    /**
     * current balance enters the bank account
     * @return balance
     */
    public double getBalance() {
        // current balance is returned
        return balance;
    }
}