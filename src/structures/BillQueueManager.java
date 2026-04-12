package structures;

import java.util.LinkedList;
import java.util.Queue;

public class BillQueueManager {
    private Queue<String> billQueue = new LinkedList<>();

    // Task 4 – Add bill payment request
    public void addBill(String billName) {
        billQueue.add(billName);
        System.out.println("Added: " + billName);
    }

    // Task 4 – Process next bill (FIFO)
    public void processNextBill() {
        if (billQueue.isEmpty()) { System.out.println("No bills in queue."); return; }
        System.out.println("Processing: " + billQueue.poll());
    }

    // Task 4 – Display remaining queue
    public void displayQueue() {
        if (billQueue.isEmpty()) { System.out.println("Bill queue is empty."); return; }
        System.out.println("Remaining bills:");
        for (String bill : billQueue) System.out.println("  - " + bill);
    }
}
