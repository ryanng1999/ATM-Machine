// import packages to measure times
import java.time.LocalDate;
import java.time.LocalTime;

// class for Receipt
public class Receipt {
    public static final String BANKNAME = "RYAN bankNG";
    public static final String BANKADDRESS = "2350 NY-110, Farmingdale, NY 11735";
    private String NameOfUser;
    private String address;
    private int accountNumber;
    private double amount;
    private String transaction;
    private LocalDate today;
    private LocalTime hour;

    /**
     * the Receipt method looks for the user's banking information
     * @param ATMNameOfUser
     * @param ATMaddress
     * @param ATMaccountNumber
     * @param ATMamount
     * @param ATMtransaction
     */
    public Receipt(String ATMNameOfUser, String ATMaddress, int ATMaccountNumber, double ATMamount, String ATMtransaction) {
        today = LocalDate.now();
        hour = LocalTime.now();
        address = ATMaddress;
        NameOfUser = ATMNameOfUser;
        accountNumber = ATMaccountNumber;
        amount = ATMamount;
        transaction = ATMtransaction;
    }

    // the printReceipt method helps with printing the receipt
    public void printReceipt() {
        System.out.println(BANKNAME);
        System.out.println(BANKADDRESS);
        System.out.println("User: " + NameOfUser);
        System.out.println("User's Address: " + address);
        System.out.println("User's Account Number: " + accountNumber);
        System.out.println("Transaction Amount: $" + String.format("%.2f", amount));
        System.out.println("Transaction Type: " + transaction);
        System.out.println("Transaction Date and Time: " + today + " " + hour);
    }
}