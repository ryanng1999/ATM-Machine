import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// class for Bank
public class Bank {

    // this Bank constructor doesn't have users nor accounts
    public Bank() {
        users = new ArrayList<User>();
    }

    /**
     * the glanceThroughUsers method reads users' numbers & PIN numbers
     * bank accounts are set to starting values
     * @param filename
     * @throws IOException
     */
    public void glanceThroughUsers(String filename) throws IOException {
        Scanner in = new Scanner(new File(filename));
        while(in.hasNext()) {
            int amount = in.nextInt();
            int personalIDnumber = in.nextInt();
            User u = new User(amount, personalIDnumber);
            addUser(u);
        }
        in.close();
    }

    /**
     * adds a bank user
     * @param u
     */
    public void addUser(User u) {
        users.add(u);
    }

    /**
     * the LookForPurchaser method looks for the user
     * @param anAmount
     * @param aPersonalIDNumber
     * @return either the matching user or null
     */
    public User LookForPurchaser(int anAmount, int aPersonalIDNumber) {
        for(User u : users) {
            if(u.match(anAmount, aPersonalIDNumber)) {
                return u;
            }
        }
        return null;
    }
    
    private ArrayList<User> users;
}