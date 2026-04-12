package structures;

import java.util.LinkedList;

public class AccountManager {
    // Minimal account contract used so classes in this package do not depend
    // directly on default-package classes.
    public interface AccountLike {
        String getAccountNumber();
        String getUsername();
        double getBalance();
        void setBalance(double balance);
    }

    private LinkedList<AccountLike> accounts = new LinkedList<>();

    // Adds a new account to the main linked list.
    public void addAccount(AccountLike account) {
        accounts.add(account);
        System.out.println("Account added successfully.");
    }

    // Prints all registered accounts.
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

    // Finds an account by username (case-insensitive).
    public AccountLike searchByUsername(String username) {
        for (AccountLike acc : accounts) {
            if (acc.getUsername().equalsIgnoreCase(username)) return acc;
        }
        return null;
    }

    // Deposits money into the selected account.
    public void deposit(String username, double amount) {
        AccountLike acc = searchByUsername(username);
        if (acc == null) { System.out.println("Account not found."); return; }
        acc.setBalance(acc.getBalance() + amount);
        System.out.println("Deposit: " + (int) amount);
        System.out.println("New balance: " + (int) acc.getBalance());
    }

    // Withdraws money if the account has sufficient balance.
    public void withdraw(String username, double amount) {
        AccountLike acc = searchByUsername(username);
        if (acc == null) { System.out.println("Account not found."); return; }
        if (acc.getBalance() < amount) { System.out.println("Insufficient funds."); return; }
        acc.setBalance(acc.getBalance() - amount);
        System.out.println("Withdrawal: " + (int) amount);
        System.out.println("New balance: " + (int) acc.getBalance());
    }

    // Adds an approved account request into the main list.
    public void addApprovedAccount(AccountLike account) {
        accounts.add(account);
        System.out.println("Account for " + account.getUsername() + " approved and added to system.");
    }

    public LinkedList<AccountLike> getAccounts() { return accounts; }
}
