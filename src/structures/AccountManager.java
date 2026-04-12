package structures;

import java.util.LinkedList;

public class AccountManager {
    public interface AccountLike {
        String getAccountNumber();
        String getUsername();
        double getBalance();
        void setBalance(double balance);
    }

    private LinkedList<AccountLike> accounts = new LinkedList<>();

    // Task 1 – Add a new account
    public void addAccount(AccountLike account) {
        accounts.add(account);
        System.out.println("Account added successfully.");
    }

    // Task 1 – Display all accounts
    public void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("Accounts List:");
        int i = 1;
        for (AccountLike acc : accounts) {
            System.out.println(i++ + ". " + acc.getUsername() + " - Balance: " + (int) acc.getBalance());
        }
    }

    // Task 1 – Search account by username
    public AccountLike searchByUsername(String username) {
        for (AccountLike acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(username)) return acc;
        }
        return null;
    }

    // Task 2 – Deposit money
    public void deposit(String username, double amount) {
        AccountLike acc = searchByUsername(username);
        if (acc == null) { System.out.println("Account not found."); return; }
        acc.setBalance(acc.getBalance() + amount);
        System.out.println("Deposit: " + (int) amount);
        System.out.println("New balance: " + (int) acc.getBalance());
    }

    // Task 2 – Withdraw money
    public void withdraw(String username, double amount) {
        AccountLike acc = searchByUsername(username);
        if (acc == null) { System.out.println("Account not found."); return; }
        if (acc.getBalance() < amount) { System.out.println("Insufficient funds."); return; }
        acc.setBalance(acc.getBalance() - amount);
        System.out.println("Withdrawal: " + (int) amount);
        System.out.println("New balance: " + (int) acc.getBalance());
    }

    // Task 5 – Move approved request into main LinkedList
    public void addApprovedAccount(AccountLike account) {
        accounts.add(account);
        System.out.println("Account for " + account.getUsername() + " approved and added to system.");
    }

    public LinkedList<AccountLike> getAccounts() { return accounts; }
}
