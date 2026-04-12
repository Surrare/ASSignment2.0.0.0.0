import java.util.Scanner;
import structures.AccountManager;

public class Main {

    static BankSystem system        = new BankSystem();
    static Scanner    scanner       = new Scanner(System.in);
    static int        accountCounter = 100;

    public static void main(String[] args) {

        // ── Part 1: Auto-demo of all Tasks ──────────────────────────────
        demonstrateTasks();

        // ── Part 2: Physical Array Demo ──────────────────────────────────
        system.demonstratePhysicalArray();

        // ── Part 3: Interactive Mini Banking Menu ─────────────────────────
        System.out.println("\n===== Part 3: Mini Banking Menu =====");
        mainMenu();
    }

    // ─────────────────────────────────────────────────────────────────────
    // PART 1 – Demo all Tasks 1-5 with sample data
    // ─────────────────────────────────────────────────────────────────────
    private static void demonstrateTasks() {
        System.out.println("===== Part 1: Logical Data Structures =====\n");

        // Task 1 – LinkedList: add & display accounts
        System.out.println("--- Task 1: Bank Account Storage (LinkedList) ---");
        system.accountManager.addAccount(new BankAccount("ACC001", "Ali",  150000));
        system.accountManager.addAccount(new BankAccount("ACC002", "Sara", 220000));
        system.accountManager.displayAccounts();
        AccountManager.AccountLike found = system.accountManager.searchByUsername("Ali");
        System.out.println("Search 'Ali': " + (found != null ? found : "Not found"));

        // Task 2 – Deposit & Withdraw
        System.out.println("\n--- Task 2: Deposit & Withdraw ---");
        System.out.println("Enter username: Ali");
        system.accountManager.deposit("Ali", 50000);

        // Task 3 – Stack (LIFO): Transaction History
        System.out.println("\n--- Task 3: Transaction History (Stack / LIFO) ---");
        system.transactionManager.addTransaction("Deposit 50000 to Ali");
        system.transactionManager.addTransaction("Withdraw 20000 from Ali");
        system.transactionManager.displayLastTransaction();
        system.transactionManager.undoLastTransaction();

        // Task 4 – Queue (FIFO): Bill Payment
        System.out.println("\n--- Task 4: Bill Payment Queue (Queue / FIFO) ---");
        system.billQueueManager.addBill("Electricity Bill");
        system.billQueueManager.addBill("Internet Bill");
        system.billQueueManager.processNextBill();
        system.billQueueManager.displayQueue();

        // Task 5 – Account Opening Queue (Admin Simulation)
        System.out.println("\n--- Task 5: Account Opening Queue (Admin Simulation) ---");
        system.accountRequestManager.submitRequest(new BankAccount("REQ001", "Omar", 0));
        system.accountRequestManager.submitRequest(new BankAccount("REQ002", "Lena", 0));
        system.accountRequestManager.displayPendingRequests();
        AccountManager.AccountLike approved = system.accountRequestManager.processNextRequest();
        if (approved != null) system.accountManager.addApprovedAccount(approved);
        system.accountRequestManager.displayPendingRequests();
    }

    // ─────────────────────────────────────────────────────────────────────
    // PART 3 – Interactive Menus
    // ─────────────────────────────────────────────────────────────────────
    private static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            System.out.print("Choose: ");
            switch (scanner.nextLine().trim()) {
                case "1": bankMenu();  break;
                case "2": atmMenu();   break;
                case "3": adminMenu(); break;
                case "4": running = false; System.out.println("Goodbye!"); break;
                default:  System.out.println("Invalid option, try again.");
            }
        }
    }

    // ── Bank Menu ─────────────────────────────────────────────────────────
    private static void bankMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n====== BANK MENU ======");
            System.out.println("1 - Open new account (submit request)");
            System.out.println("2 - Deposit money");
            System.out.println("3 - Withdraw money");
            System.out.println("4 - Back");
            System.out.print("Choose: ");
            switch (scanner.nextLine().trim()) {
                case "1":
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Initial deposit: ");
                    double init = parseDouble(scanner.nextLine());
                    BankAccount newAcc = new BankAccount("ACC" + (++accountCounter), name, init);
                    system.accountRequestManager.submitRequest(newAcc);
                    system.transactionManager.addTransaction("Account request submitted for " + name);
                    break;
                case "2":
                    System.out.print("Enter username: ");
                    String du = scanner.nextLine().trim();
                    System.out.print("Amount to deposit: ");
                    double da = parseDouble(scanner.nextLine());
                    system.accountManager.deposit(du, da);
                    system.transactionManager.addTransaction("Deposit " + (int) da + " to " + du);
                    break;
                case "3":
                    System.out.print("Enter username: ");
                    String wu = scanner.nextLine().trim();
                    System.out.print("Amount to withdraw: ");
                    double wa = parseDouble(scanner.nextLine());
                    system.accountManager.withdraw(wu, wa);
                    system.transactionManager.addTransaction("Withdraw " + (int) wa + " from " + wu);
                    break;
                case "4": back = true; break;
                default:  System.out.println("Invalid option.");
            }
        }
    }

    // ── ATM Menu ──────────────────────────────────────────────────────────
    private static void atmMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n====== ATM MENU ======");
            System.out.println("1 - Balance enquiry");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Back");
            System.out.print("Choose: ");
            switch (scanner.nextLine().trim()) {
                case "1":
                    System.out.print("Enter username: ");
                    String u = scanner.nextLine().trim();
                    AccountManager.AccountLike acc = system.accountManager.searchByUsername(u);
                    if (acc != null) System.out.println("Balance for " + u + ": " + (int) acc.getBalance());
                    else             System.out.println("Account not found.");
                    break;
                case "2":
                    System.out.print("Enter username: ");
                    String wu = scanner.nextLine().trim();
                    System.out.print("Amount: ");
                    double wa = parseDouble(scanner.nextLine());
                    system.accountManager.withdraw(wu, wa);
                    system.transactionManager.addTransaction("ATM Withdraw " + (int) wa + " from " + wu);
                    break;
                case "3": back = true; break;
                default:  System.out.println("Invalid option.");
            }
        }
    }

    // ── Admin Menu ────────────────────────────────────────────────────────
    private static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n====== ADMIN MENU ======");
            System.out.println("1 - View & process account requests queue");
            System.out.println("2 - View bill payment queue");
            System.out.println("3 - Add bill to queue");
            System.out.println("4 - Process next bill");
            System.out.println("5 - View all accounts (LinkedList)");
            System.out.println("6 - View transaction history (Stack)");
            System.out.println("7 - Undo last transaction");
            System.out.println("8 - Back");
            System.out.print("Choose: ");
            switch (scanner.nextLine().trim()) {
                case "1":
                    system.accountRequestManager.displayPendingRequests();
                    System.out.print("Process next request? (y/n): ");
                    if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                        AccountManager.AccountLike approved = system.accountRequestManager.processNextRequest();
                        if (approved != null) system.accountManager.addApprovedAccount(approved);
                    }
                    break;
                case "2": system.billQueueManager.displayQueue();            break;
                case "3":
                    System.out.print("Bill name: ");
                    system.billQueueManager.addBill(scanner.nextLine().trim());
                    break;
                case "4": system.billQueueManager.processNextBill();         break;
                case "5": system.accountManager.displayAccounts();           break;
                case "6": system.transactionManager.displayAllTransactions(); break;
                case "7": system.transactionManager.undoLastTransaction();   break;
                case "8": back = true;                                       break;
                default:  System.out.println("Invalid option.");
            }
        }
    }

    private static double parseDouble(String s) {
        try   { return Double.parseDouble(s.trim()); }
        catch (NumberFormatException e) { System.out.println("Invalid number, using 0."); return 0; }
    }
}
