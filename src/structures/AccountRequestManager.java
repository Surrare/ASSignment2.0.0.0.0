package structures;

import java.util.LinkedList;
import java.util.Queue;

public class AccountRequestManager {
    private Queue<AccountManager.AccountLike> accountRequests = new LinkedList<>();

    // Enqueues a new account opening request.
    public void submitRequest(AccountManager.AccountLike account) {
        accountRequests.add(account);
        System.out.println("Account request submitted for: " + account.getUsername());
    }

    // Processes the next pending request (FIFO).
    public AccountManager.AccountLike processNextRequest() {
        if (accountRequests.isEmpty()) { System.out.println("No pending account requests."); return null; }
        AccountManager.AccountLike processed = accountRequests.poll();
        System.out.println("Processing account request for: " + processed.getUsername());
        return processed;
    }

    // Displays all pending account requests.
    public void displayPendingRequests() {
        if (accountRequests.isEmpty()) { System.out.println("No pending account requests."); return; }
        System.out.println("Pending Account Requests:");
        int i = 1;
        for (AccountManager.AccountLike acc : accountRequests)
            System.out.println(i++ + ". " + acc.getUsername() + " (Acc#: " + acc.getAccountNumber() + ")");
    }
}
