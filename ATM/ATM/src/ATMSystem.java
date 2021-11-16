import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args) {
        //prepare the STL to store account object
        ArrayList<Account> accounts = new ArrayList<>();
        showMain(accounts);

    }


    public static void showMain(ArrayList<Account> accounts){
        Scanner sc = new Scanner(System.in);  // user operator
        while (true) {
            System.out.println("================Welcome to Main Menu===================");
            System.out.println("Enter your operation:");
            System.out.println("1: Create a new account");
            System.out.println("2: Log in");

            int command = sc.nextInt();
            switch (command){
                case 1:
                    //create
                    Register(accounts,sc);
                    break;

                case 2:
                    //login
                    Login(accounts,sc);
                    break;

                default:
                    System.out.println("This operation is not supported!");
            }
        }
    }

    /**
     * user registration
     * @param accounts   the thing to store user information
     */
    private static void Register(ArrayList<Account> accounts,Scanner sc) {
        System.out.println("==================User Account Creation=========================");
        String name;
        String password;
        double quota;

        // get the name and password
        System.out.println("input your name:");
        sc.nextLine();          // eat the \n  created by entering number
        name = sc.nextLine();
        while(true){
            System.out.println("input your password:");
            password = sc.next();
            System.out.println("confirm your password:");
            if(sc.next().equals(password)) break;
            else System.out.println("two passwords don't match! please try again!");
        }

        System.out.println("Enter the amount of quota");
        quota = sc.nextDouble();

        // generate the CardId and it must be a unique one
        String id = CreateCardId(accounts);

        // make a new object to hold these information and add it to the accounts
        Account temp = new Account(id,name,password,quota);
        accounts.add(temp);
        System.out.println("Congrats! your account has been created successfully!");
        System.out.println("Your Card ID is "+ id);

    }

    /**
     *
     * @param accounts accounts set
     * @return  the generated Card ID
     */
    private static String CreateCardId(ArrayList<Account> accounts){
        // generate
        Random rd = new Random();

        StringBuilder res = null;
        while (true) {
            res = new StringBuilder("");
            for(int i=0;i<8;i++){
                res.append(rd.nextInt(10));
            }
            //check if this id valid
            if(getAccountById(res.toString(),accounts) == null) break;
        }

        return res.toString();
    }


    /**
     *
     * @param id  the Card ID we want to use to find account
     * @param accounts the accounts set
     * @return  return the account with correct ID or null, which indicates there's no such account
     */
    private static Account getAccountById(String id, ArrayList<Account> accounts){
        for(int i=0;i<accounts.size();i++){
            if(id.equals(accounts.get(i).getCardId())) return accounts.get(i);
        }
        return null;
    }


    /**
     * Login
     * @param accounts the set holding all the accounts
     * @param sc   scanner
     */
    private static void Login(ArrayList<Account> accounts,Scanner sc){
        System.out.println("=================Login=====================");
        if(accounts.size() == 0){
            System.out.println("There's no accounts to login!");
            return;
        }
        Account temp;
        while (true) {
            System.out.println("Enter your Card ID:");
            temp = getAccountById(sc.next(),accounts);
            if(temp != null) break;
            System.out.println("Card ID doesn't exist! please try again!");
        }
        while(true){
            System.out.println("Enter the password:");
            if(sc.next().equals(temp.getPassword())) break;
            System.out.println("wrong password! please try again!");
        }
        System.out.println("Hello "+temp.getUserName()+"! Your Card ID: "+temp.getCardId());
        // show the command menu after login
        showUserCommand(temp,sc,accounts);
    }

    private static void showUserCommand(Account acc, Scanner sc, ArrayList<Account> alist) {
        System.out.println("============Welcome to User Command Interface=============");
        System.out.println("1: Get Information About Account");
        System.out.println("2: Deposit");
        System.out.println("3: Withdraw");
        System.out.println("4: Transfer");
        System.out.println("5: Change Password");
        System.out.println("6: Exit To Main Menu");
        System.out.println("7: Log Off This Account");
        while (true) {
            System.out.println("Enter your operation: (0 to get hint)");
            int command = sc.nextInt();
            switch (command){
                case 0:
                    getHint();
                    break;
                case 1:
                    showAccount(acc);
                    break;
                case 2:
                    DepositMoney(acc,sc);
                    break;
                case 3:
                    WithdrawMoney(acc,sc);
                    break;
                case 4:
                    transferMoney(acc,sc,alist);
                    break;
                case 5:
                    changePassword(acc,sc);
                    break;
                case 6:
                    return;
                case 7:
                    LogOff(acc,sc,alist);
                    acc = null;
                    return;
                default:
                    System.out.println("This operation is not supported! Try again!");
            }
        }
    }


    /**
     * logoff the account
     * @param acc account to logoff
     * @param sc user
     */
    private static void LogOff(Account acc, Scanner sc,ArrayList<Account> alist) {
        sc.nextLine();
        System.out.println("==================Logoff================");
        System.out.println("Enter you password:    enter quit to quit");
        String temp = sc.nextLine();
        while(!temp.equals("quit") && !temp.equals(acc.getPassword())){
            System.out.println("wrong password, please try again: ");
        }
        if(temp.equals("quit")){
            System.out.println("logoff canceled");
            return;
        }

        alist.remove(acc);
        System.out.println("logoff successfully");

    }


    /**
     * change the user's password
     * @param acc  the user account
     * @param sc  user interaction scanner
     */
    private static void changePassword(Account acc, Scanner sc) {
        sc.nextLine();
        System.out.println("==================Changing password================");
        System.out.println("Enter you old password:    enter quit to quit");
        String old = sc.nextLine();
        while(!old.equals("quit") && !old.equals(acc.getPassword())){
            System.out.println("wrong password, please try again: ");
            old = sc.nextLine();
        }
        if(old.equals("quit")){
            System.out.println("Changing password canceled");
            return;
        }

        System.out.println("Enter your new password: ");
        String newpass = sc.nextLine();
        System.out.println("Confirm your password:   enter quit to quit");
        String authentication = sc.nextLine();
        while(!authentication.equals("quit") && !authentication.equals(newpass)){
            System.out.println("password doesn't match, please enter again: ");
            authentication = sc.nextLine();
        }

        if(authentication.equals("quit")){
            System.out.println("Changing password canceled");
            return;
        }

        acc.setPassword(newpass);
        System.out.println("password change successfully!");
    }

    private static void transferMoney(Account acc, Scanner sc, ArrayList<Account> alist) {
        sc.nextLine();
        System.out.println("==================Withdraw Operation================");

        if(alist.size() < 2) {
            System.out.println("there's no account you can transfer to");
            return;
        }
        // check if the amount of money is valid
        double amount = 0;

        while(true){
            System.out.println("Enter the amount you want to transfer:   (enter 0 to quit)");
            amount = sc.nextDouble();
            if(amount == 0){
                System.out.println("operation canceled");
                return;
            }
            if(amount <= acc.getMoney() && amount > 0 && amount <= acc.getQuota()) break;
            System.out.println("Invalid input!\n amount can't \n1.exceeds the money you have \n" +
                    "2.amount can't be negative! \n3.amount can't exceed the quota! \nTry again: ");
            System.out.println("Hint: Your money: " + acc.getMoney());
        }

        // check the recipient info
        System.out.println("enter the Card ID you want to transfer money to:  (enter quit to cancel)");
        String id = sc.nextLine();
        while(true){
            if(id.equals("quit")){
                System.out.println("Transfer canceled!");
                return;
            }
            else if(id.equals(acc.getCardId())){
                System.out.println("you can't transfer money to yourself!");
            }

            else if(getAccountById(id,alist) != null){
               break;
            }
            System.out.println("ID not found or invalid! Check your input and try again:");
            System.out.println("enter the Card ID you want to transfer money to:  (enter quit to cancel)");
            id = sc.nextLine();
        }

        Account rec = getAccountById(id,alist);
        String name_input;
        while(true){
            System.out.println("Enter the name of the recipient:");
            name_input = sc.nextLine();

            if(name_input.equals("quit")) {
                System.out.println("Transfer canceled!");
                return;
            }

            if(name_input.equals(rec.getUserName())) break;
            System.out.println("wrong name, try again! (enter quit to cancel)");
        }

        //change data
        double oMUser = acc.getMoney(); //original money of user
        double oMRecipient = rec.getMoney();
        acc.setMoney(oMUser - amount);
        rec.setMoney(oMRecipient + amount);
        System.out.println("Transfer Successfully!");
        showAccount(acc);
    }


    /**
     * withdraw money
     * @param acc user account
     * @param sc user interaction
     */
    private static void WithdrawMoney(Account acc, Scanner sc) {
        sc.nextLine();
        System.out.println("==================Withdraw Operation================");
        // change money amount
        while (true) {
            System.out.println("input the amount of money you want to withdraw:");
            double minus = sc.nextDouble();

            double origin = acc.getMoney();
            if(minus > acc.getQuota()){
                System.out.println("Amount exceeds the quota! Try again!");
                continue;
            }
            else if(minus > acc.getMoney()){
                System.out.println("Amount exceeds the money you have! Try again!");
                continue;
            }
            acc.setMoney(origin-minus);
            System.out.println("Withdraw Successfully!");
            showAccount(acc);
            break;
        }
    }

    /**
     * deposit money
     * @param acc  the user account
     * @param sc   user interaction
     */
    private static void DepositMoney(Account acc, Scanner sc) {
        sc.nextLine();
        System.out.println("==================Deposit Operation================");
        System.out.println("input the amount of money you want to deposit:");
        double add = sc.nextDouble();

        // change money amount
        double origin = acc.getMoney();
        acc.setMoney(origin+add);
        System.out.println("Deposit Successfully!");
        showAccount(acc);
    }

    private static void showAccount(Account acc) {
        System.out.println("==================Account Information================");
        System.out.println("Card ID: "+acc.getCardId());
        System.out.println("Holder: "+acc.getUserName());
        System.out.println("Money: "+acc.getMoney());
        System.out.println("Quota: "+acc.getQuota());
    }

    private static void getHint(){
        System.out.println("1: Get Information About Account");
        System.out.println("2: Deposit");
        System.out.println("3: Withdraw");
        System.out.println("4: Transfer");
        System.out.println("5: Change Password");
        System.out.println("6: Exit To Main Menu");
        System.out.println("7: Log Off This Account");
    }

}
