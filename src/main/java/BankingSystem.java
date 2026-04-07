import java.util.*;

public class BankingSystem {
    private LinkedList<BankAccount> accounts = new LinkedList<>();
    private Stack<String> history = new Stack<>();
    private Scanner sc = new Scanner(System.in);

    public void run() {
        accounts.add(new BankAccount("ACC101", "Ali", 5000));
        accounts.add(new BankAccount("ACC102", "Sara", 7500));
        accounts.add(new BankAccount("ACC103", "John", 3000));

        while (true) {
            System.out.println("\n--- BANK SYSTEM ---");
            System.out.println("1. View Accounts (Sorted by Balance)");
            System.out.println("2. Search Account (Recursive)");
            System.out.println("3. ATM Actions");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            String choice = sc.nextLine();
            if (choice.equals("0")) break;

            switch (choice) {
                case "1" -> {
                    sortAccountsByBalance(); // New Sorting Logic
                    accounts.forEach(System.out::println);
                }
                case "2" -> {
                    System.out.print("Enter username to search: ");
                    String name = sc.nextLine();
                    // Task: Recursive Search call
                    BankAccount result = recursiveSearch(0, name);
                    System.out.println(result != null ? "Found: " + result : "User not found.");
                }
                case "3" -> atmMenu();
            }
        }
    }

    // --- RECURSIVE SEARCH (Task from screenshot) ---
    private BankAccount recursiveSearch(int index, String targetName) {
        // Base Case 1: End of list
        if (index >= accounts.size()) return null;

        // Base Case 2: Match found
        if (accounts.get(index).getUsername().equalsIgnoreCase(targetName)) {
            return accounts.get(index);
        }

        // Recursive Step: Move to next index
        return recursiveSearch(index + 1, targetName);
    }

    // --- BUBBLE SORT (Physical DS Logic) ---
    private void sortAccountsByBalance() {
        int n = accounts.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (accounts.get(j).getBalance() < accounts.get(j + 1).getBalance()) {
                    // Swap elements in the LinkedList
                    BankAccount temp = accounts.get(j);
                    accounts.set(j, accounts.get(j + 1));
                    accounts.set(j + 1, temp);
                }
            }
        }
        System.out.println("Accounts sorted by highest balance.");
    }

    private void atmMenu() {
        System.out.print("Username: ");
        String name = sc.nextLine();
        BankAccount acc = recursiveSearch(0, name); // Using our new recursive search

        if (acc == null) return;

        System.out.print("1.Deposit 2.Withdraw: ");
        String op = sc.nextLine();
        System.out.print("Amount: ");
        double amt = Double.parseDouble(sc.nextLine());

        if (op.equals("1")) {
            acc.deposit(amt);
            history.push("Added $" + amt + " to " + name);
        } else {
            if (acc.withdraw(amt)) history.push("Took $" + amt + " from " + name);
        }
    }
}