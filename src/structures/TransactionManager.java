package structures;

import java.util.Stack;

public class TransactionManager {
    private Stack<String> transactionHistory = new Stack<>();

    // Records a transaction.
    public void addTransaction(String transaction) {
        transactionHistory.push(transaction);
        System.out.println(transaction);
    }

    // Removes the most recent transaction (LIFO).
    public void undoLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("No transactions to undo."); return; }
        String removed = transactionHistory.pop();
        System.out.println("Undo -> " + removed + " removed");
    }

    // Shows the most recent transaction without removing it.
    public void displayLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("No transactions recorded."); return; }
        System.out.println("Last transaction: " + transactionHistory.peek());
    }

    // Displays the full transaction history.
    public void displayAllTransactions() {
        if (transactionHistory.isEmpty()) { System.out.println("Transaction history is empty."); return; }
        System.out.println("Transaction History:");
        for (String t : transactionHistory) System.out.println("  - " + t);
    }
}
