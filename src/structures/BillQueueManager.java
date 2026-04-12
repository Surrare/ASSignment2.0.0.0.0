package structures;

import java.util.LinkedList;
import java.util.Queue;

public class BillQueueManager {
    private Queue<String> billQueue = new LinkedList<>();

    // Adds a bill payment request to the queue.
    public void addBill(String billName) {
        billQueue.add(billName);
        System.out.println("Added: " + billName);
    }

    // Processes the next bill in FIFO order.
    public void processNextBill() {
        if (billQueue.isEmpty()) { System.out.println("No bills in queue."); return; }
        System.out.println("Processing: " + billQueue.poll());
    }

    // Displays remaining queued bills.
    public void displayQueue() {
        if (billQueue.isEmpty()) { System.out.println("Bill queue is empty."); return; }
        System.out.println("Remaining bills:");
        for (String bill : billQueue) System.out.println("  - " + bill);
    }
}
