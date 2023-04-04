import java.io.IOException;
import java.util.Scanner;

// class for ATMSimulator
public class ATMSimulator {

    // main method
    public static void main(String[] args) {

        // information comes from the ATM class
        ATM theATM;

        // try...catch
        try {
            // the bank is built
            Bank theDealer = new Bank();
            theDealer.glanceThroughUsers("Source/users.txt");
            theATM = new ATM(theDealer); // the ATM is built
            
        // catch the error if the text file can't be opened
        } catch(IOException e) {
            System.out.println("Couldn't open the file!");
            return;
        }
        Scanner in = new Scanner(System.in);

        // introduction
        String NameOfUser = "";
        String address = "";
        int accountNumber = -1;
        int transferAccountNumber = -1;
        System.out.println("Hello, welcome to RYANbankNG!");
        System.out.print("What's your first and last name? ");
        NameOfUser = in.nextLine();
        System.out.print("What's your home address? ");
        address = in.nextLine();

        // execution
        do {
            int condition = theATM.getCondition();

            // ask the user for their bank account number once they start banking
            if(condition == ATM.INITIATE) {

                // type a one-digit number
                System.out.print("What is your bank account number? Type '0' if you want to leave. ");
                accountNumber = in.nextInt();
                if(accountNumber == 0) {
                    return;
                }
                theATM.setUserNumber(accountNumber);
            
            // get their PIN number
            } else if(condition == ATM.PERSONAL_IDENTIFICATION_NUMBER) {

                /*
                 * If the bank account number is '1', type '5670'.
                 * If the bank account number is '2', type '4492'.
                 * If the bank account number is '3', type '9046'.
                 * If the bank account number is '4', type '3858'.
                 * If the bank account number is '5', type '6284'.
                 * If the bank account number is '6', type '1622'.
                 * If the bank account number is '7', type '2416'.
                 * If the bank account number is '8', type '8838'.
                 * If the bank account number is '9', type '7264'.
                 */
                System.out.print("What is your PIN? ");
                int personalIDnumber = in.nextInt();
                theATM.pickPurchaser(personalIDnumber);
            
            // get their account type
            } else if(condition == ATM.BANK_TYPE) {
                System.out.println("C = Checking");
                System.out.println("S = Savings");
                System.out.println("E = Exit");
                System.out.print("What would you like to do? ");
                String command = in.next();
                if(command.equalsIgnoreCase("C")) {
                    theATM.selectAccount(ATM.BANKTYPEONE);
                } else if(command.equalsIgnoreCase("S")) {
                    theATM.selectAccount(ATM.BANKTYPETWO);
                } else if(command.equalsIgnoreCase("E")) {
                    System.out.println("Thank you for coming! Have a great day.");
                    theATM.reset();
                } else {
                    System.out.println("Illegal input!");
                }
            
            // their transaction begins
            } else if(condition == ATM.BANK_TRANSACTION) {
                System.out.println();
                System.out.println("Balance = $" + String.format("%.2f", theATM.getBalance()));
                System.out.println("D = Deposit");
                System.out.println("W = Withdraw");
                System.out.println("T = Transfer");
                System.out.println("C = Cancel");
                System.out.print("What would you like to do? ");
                String command = in.next();

                // deposit
                if(command.equalsIgnoreCase("D")) {
                    System.out.print("Amount: $");
                    double amount = in.nextDouble();
                    theATM.deposit(amount);

                    System.out.print("Do you want a receipt?\nY = Yes\nN = No\n");
                    String answer = in.next();
                    if(answer.equalsIgnoreCase("Y")) {
                        // print the receipt
                        Receipt data = new Receipt(NameOfUser, address, accountNumber, amount, command);
                        System.out.println("------------------------------------------------------------");
                        data.printReceipt();
                        System.out.println("------------------------------------------------------------");
                    } else {
                        System.out.println();
                    }
                    theATM.back();
                
                // withdraw
                } else if(command.equalsIgnoreCase("W")) {
                    System.out.print("Amount: $");
                    double amount = in.nextDouble();
                    theATM.withdraw(amount);

                    System.out.print("Do you want a receipt?\nY = Yes\nN = No\n");
                    String answer = in.next();
                    if(answer.equalsIgnoreCase("Y")) {
                        // print the receipt
                        Receipt data = new Receipt(NameOfUser, address, accountNumber, amount, command);
                        System.out.println("------------------------------------------------------------");
                        data.printReceipt();
                        System.out.println("------------------------------------------------------------");
                    } else {
                        System.out.println();
                    }
                    theATM.back();
                
                // transfer
                } else if(command.equalsIgnoreCase("T")) {
                    System.out.print("Which account number are you transferring to? Type '0' to return. ");
                    transferAccountNumber = in.nextInt();
                    if(transferAccountNumber == 0) {
                        return;
                    }
                    theATM.setUserNumber(transferAccountNumber);
                    System.out.print("Amount: $");
                    double amount = in.nextDouble();
                    theATM.transfer(amount, transferAccountNumber);

                    System.out.print("Do you want a receipt?\nY = Yes\nN = No\n");
                    String answer = in.next();
                    if(answer.equalsIgnoreCase("Y")) {
                        // print the receipt
                        Receipt data = new Receipt(NameOfUser, address, accountNumber, amount, command);
                        System.out.println("------------------------------------------------------------");
                        data.printReceipt();
                        System.out.println("------------------------------------------------------------");
                    } else {
                        System.out.println();
                    }
                    theATM.back();
                
                // cancel
                } else if(command.equalsIgnoreCase("C")) {
                    theATM.back();
                
                // wrong input
                } else {
                    System.out.println("Wrong input!");
                }
            }
        } while(true);
    }
}