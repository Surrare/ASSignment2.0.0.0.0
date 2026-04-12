package structures;

import java.util.Stack;

public class TransactionManager {
    private Stack<String> transactionHistory = new Stack<>();

    // Task 3 – Add transaction to stack
    public void addTransaction(String transaction) {
        transactionHistory.push(transaction);
        System.out.println(transaction);
    }

    // Task 3 – Undo last transaction (pop)
    public void undoLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("No transactions to undo."); return; }
        String removed = transactionHistory.pop();
        System.out.println("Undo -> " + removed + " removed");
    }

    // Task 3 – Display last transaction (peek)
    public void displayLastTransaction() {
        if (transactionHistory.isEmpty()) { System.out.println("No transactions recorded."); return; }
        System.out.println("Last transaction: " + transactionHistory.peek());
    }

    // Display full history
    public void displayAllTransactions() {
        if (transactionHistory.isEmpty()) { System.out.println("Transaction history is empty."); return; }
        System.out.println("Transaction History:");
        for (String t : transactionHistory) System.out.println("  - " + t);
    }
}
