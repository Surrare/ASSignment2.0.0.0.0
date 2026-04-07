import java.util.*;

public class Main {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<String> accountRequests = new LinkedList<>();
    static Scanner sc = new Scanner(System.in);
    static int accountCounter = 1000;

    // Task 1
    static void addAccount(String username, double balance) {
        accountCounter++;
        accounts.add(new BankAccount("ACC" + accountCounter, username, balance));
        System.out.println("Account added successfully");
    }

    static void displayAccounts() {
        if (accounts.isEmpty()) { System.out.println("No accounts found."); return; }
        System.out.println("Accounts List:");
        int i = 1;
        for (BankAccount a : accounts)
            System.out.println(i++ + ". " + a.getUsername() + " – Balance: " + (int) a.getBalance());
    }

    static BankAccount findByUsername(String username) {
        for (BankAccount a : accounts)
            if (a.getUsername().equalsIgnoreCase(username)) return a;
        return null;
    }

    // Task 2
    static void deposit(String username, double amount) {
        BankAccount a = findByUsername(username);
        if (a == null) { System.out.println("Account not found."); return; }
        a.setBalance(a.getBalance() + amount);
        history.push("Deposit " + (int) amount + " to " + username);
        System.out.println("Deposit: " + (int) amount);
        System.out.println("New balance: " + (int) a.getBalance());
    }

    static void withdraw(String username, double amount) {
        BankAccount a = findByUsername(username);
        if (a == null) { System.out.println("Account not found."); return; }
        if (a.getBalance() < amount) { System.out.println("Insufficient funds."); return; }
        a.setBalance(a.getBalance() - amount);
        history.push("Withdraw " + (int) amount + " from " + username);
        System.out.println("Withdraw: " + (int) amount);
        System.out.println("New balance: " + (int) a.getBalance());
    }

    // Task 3
    static void undoLastTransaction() {
        if (history.isEmpty()) { System.out.println("No transactions to undo."); return; }
        System.out.println("Undo → " + history.pop() + " removed");
    }

    static void peekLastTransaction() {
        if (history.isEmpty()) { System.out.println("No transactions yet."); return; }
        System.out.println("Last transaction: " + history.peek());
    }

    static void displayHistory() {
        if (history.isEmpty()) { System.out.println("No transaction history."); return; }
        System.out.println("Transaction History (most recent first):");
        Stack<String> temp = (Stack<String>) history.clone();
        int i = 1;
        while (!temp.isEmpty()) System.out.println(i++ + ". " + temp.pop());
    }

    // Task 4
    static void addBill(String billName) {
        billQueue.add(billName);
        System.out.println("Added: " + billName);
    }

    static void processNextBill() {
        if (billQueue.isEmpty()) { System.out.println("No pending bills."); return; }
        System.out.println("Processing: " + billQueue.poll());
        if (!billQueue.isEmpty())
            System.out.println("Remaining: " + String.join(", ", billQueue));
        else
            System.out.println("All bills processed.");
    }

    static void displayBillQueue() {
        if (billQueue.isEmpty()) { System.out.println("Bill queue is empty."); return; }
        System.out.println("Bill Queue: " + String.join(", ", billQueue));
    }

    // Task 5
    static void submitAccountRequest(String name) {
        accountRequests.add(name);
        System.out.println("Request submitted for: " + name);
    }

    static void processNextRequest() {
        if (accountRequests.isEmpty()) { System.out.println("No pending requests."); return; }
        String name = accountRequests.poll();
        addAccount(name, 0);
        System.out.println("Account created for: " + name);
        System.out.println("Pending requests: " + (accountRequests.isEmpty() ? "none" : String.join(", ", accountRequests)));
    }

    static void displayPendingRequests() {
        if (accountRequests.isEmpty()) { System.out.println("No pending requests."); return; }
        System.out.println("Pending Requests: " + String.join(", ", accountRequests));
    }

    // Task 6
    static void demonstrateArray() {
        System.out.println("\n--- Part 2: Physical Array (BankAccount[3]) ---");
        BankAccount[] arr = new BankAccount[3];
        arr[0] = new BankAccount("ACC001", "Ali",   150000);
        arr[1] = new BankAccount("ACC002", "Sara",  220000);
        arr[2] = new BankAccount("ACC003", "Damir", 95000);
        for (int i = 0; i < arr.length; i++)
            System.out.println((i + 1) + ". " + arr[i]);
    }

    static void bankMenu() {
        while (true) {
            System.out.println("\n=== BANK MENU ===");
            System.out.println("1 - Submit account opening request");
            System.out.println("2 - Deposit money");
            System.out.println("3 - Withdraw money");
            System.out.println("4 - Pay a bill");
            System.out.println("5 - View transaction history");
            System.out.println("6 - Undo last transaction");
            System.out.println("0 - Back");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Enter your name: ");
                    submitAccountRequest(sc.nextLine().trim());
                    break;
                case "2":
                    System.out.print("Enter username: ");
                    String du = sc.nextLine().trim();
                    System.out.print("Enter amount: ");
                    deposit(du, parseAmount());
                    break;
                case "3":
                    System.out.print("Enter username: ");
                    String wu = sc.nextLine().trim();
                    System.out.print("Enter amount: ");
                    withdraw(wu, parseAmount());
                    break;
                case "4":
                    System.out.print("Enter bill name: ");
                    addBill(sc.nextLine().trim());
                    break;
                case "5":
                    displayHistory();
                    break;
                case "6":
                    undoLastTransaction();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static void atmMenu() {
        System.out.print("\nEnter your username: ");
        String user = sc.nextLine().trim();
        BankAccount a = findByUsername(user);
        if (a == null) { System.out.println("Account not found."); return; }

        while (true) {
            System.out.println("\n=== ATM MENU – " + user + " ===");
            System.out.println("1 - Balance enquiry");
            System.out.println("2 - Withdraw");
            System.out.println("0 - Back");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("Current balance: " + (int) a.getBalance());
                    break;
                case "2":
                    System.out.print("Enter amount: ");
                    withdraw(user, parseAmount());
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1 - View pending account requests");
            System.out.println("2 - Process next account request");
            System.out.println("3 - View all accounts");
            System.out.println("4 - View bill payment queue");
            System.out.println("5 - Process next bill payment");
            System.out.println("6 - Search account by username");
            System.out.println("0 - Back");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": displayPendingRequests(); break;
                case "2": processNextRequest();     break;
                case "3": displayAccounts();        break;
                case "4": displayBillQueue();       break;
                case "5": processNextBill();        break;
                case "6":
                    System.out.print("Enter username: ");
                    BankAccount found = findByUsername(sc.nextLine().trim());
                    System.out.println(found != null ? found : "Account not found.");
                    break;
                case "0": return;
                default:  System.out.println("Invalid option.");
            }
        }
    }

    static double parseAmount() {
        try { return Double.parseDouble(sc.nextLine().trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid amount, using 0."); return 0; }
    }

    public static void main(String[] args) {
        accounts.add(new BankAccount("ACC1001", "Ali",  150000));
        accounts.add(new BankAccount("ACC1002", "Sara", 220000));

        demonstrateArray();

        while (true) {
            System.out.println("\n=== BANKING SYSTEM ===");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": bankMenu();  break;
                case "2": atmMenu();   break;
                case "3": adminMenu(); break;
                case "4": System.out.println("Goodbye!"); return;
                default:  System.out.println("Invalid option.");
            }
        }
    }
}
