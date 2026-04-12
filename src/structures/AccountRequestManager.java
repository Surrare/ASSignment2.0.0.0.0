package structures;

import java.util.LinkedList;
import java.util.Queue;

public class AccountRequestManager {
    private Queue<BankAccount> accountRequests = new LinkedList<>();

    // Task 5 – User submits account opening request
    public void submitRequest(BankAccount account) {
        accountRequests.add(account);
        System.out.println("Account request submitted for: " + account.getUsername());
    }

    // Task 5 – Admin processes next request (returns it to be added to AccountManager)
    public BankAccount processNextRequest() {
        if (accountRequests.isEmpty()) { System.out.println("No pending account requests."); return null; }
        BankAccount processed = accountRequests.poll();
        System.out.println("Processing account request for: " + processed.getUsername());
        return processed;
    }

    // Task 5 – Display all pending requests
    public void displayPendingRequests() {
        if (accountRequests.isEmpty()) { System.out.println("No pending account requests."); return; }
        System.out.println("Pending Account Requests:");
        int i = 1;
        for (BankAccount acc : accountRequests)
            System.out.println(i++ + ". " + acc.getUsername() + " (Acc#: " + acc.getAccountNumber() + ")");
    }
}
