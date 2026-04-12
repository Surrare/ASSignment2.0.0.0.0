import structures.AccountManager;
import structures.AccountRequestManager;
import structures.BillQueueManager;
import structures.TransactionManager;

public class BankSystem {
    public final AccountManager        accountManager;
    public final TransactionManager    transactionManager;
    public final BillQueueManager      billQueueManager;
    public final AccountRequestManager accountRequestManager;

    public BankSystem() {
        accountManager        = new AccountManager();
        transactionManager    = new TransactionManager();
        billQueueManager      = new BillQueueManager();
        accountRequestManager = new AccountRequestManager();
    }

    // Task 6 – Part 2: Physical Data Structures (Array)
    public void demonstratePhysicalArray() {
        System.out.println("\n===== Part 2: Physical Data Structures (Array) =====");
        BankAccount[] bankAccounts = new BankAccount[3];
        bankAccounts[0] = new BankAccount("001", "Alice", 100000);
        bankAccounts[1] = new BankAccount("002", "Bob",   250000);
        bankAccounts[2] = new BankAccount("003", "Carol", 75000);

        System.out.println("Predefined accounts stored in BankAccount[3] array:");
        for (BankAccount acc : bankAccounts) {
            System.out.println("  " + acc);
        }
    }
}
