# Assignment #2 — Physical & Logical Data Structures (Banking System)

| | |
|---|---|
| **Student** |Ulgaisyn Daulet|
| **Group** |IT-2502|
| **Course** |Algorithms & Data Structures|
---

## 📋 Table of Contents

1. [Objective](#objective)
2. [Project Structure](#project-structure)
3. [Summary of Work](#summary-of-work)
4. [Part 1 — Logical Data Structures](#part-1--logical-data-structures)
   - [Task 1 — Bank Account Storage (LinkedList)](#task-1--bank-account-storage-linkedlist)
   - [Task 2 — Deposit & Withdraw Operations](#task-2--deposit--withdraw-operations)
   - [Task 3 — Transaction History (Stack / LIFO)](#task-3--transaction-history-stack--lifo)
   - [Task 4 — Bill Payment Queue (Queue / FIFO)](#task-4--bill-payment-queue-queue--fifo)
   - [Task 5 — Account Opening Queue (Admin Simulation)](#task-5--account-opening-queue-admin-simulation)
5. [Part 2 — Physical Data Structures](#part-2--physical-data-structures)
   - [Task 6 — Array of Bank Accounts](#task-6--array-of-bank-accounts)
6. [Part 3 — Mini Banking Menu](#part-3--mini-banking-menu)
7. [Issues & Solutions](#issues--solutions)

---

## Objective

By the end of this assignment, students are able to:

- Understand the difference between **physical** and **logical** data structures.
- Apply **LinkedList** and **Queue** to simulate real banking operations.
- Understand how memory organization relates to program behavior.
- Implement solutions for storing and processing banking data using logical structures.
- Improve problem-solving and algorithmic thinking using Java data structures.

---

## Project Structure

```
assignment2/
├── Main.java                              
├── BankAccount.java                       
├── BankSystem.java                        
└── structures/
    ├── AccountManager.java                
    ├── TransactionManager.java           
    ├── BillQueueManager.java              
    └── AccountRequestManager.java        
```

---

## Summary of Work

This project implements a simplified **Banking System** in Java that demonstrates the use of four core data structures:

| Data Structure | Used In | Purpose |
|---|---|---|
| `LinkedList` | `AccountManager` | Store and manage bank accounts dynamically |
| `Stack` | `TransactionManager` | Record transaction history with undo (LIFO) |
| `Queue` (LinkedList) | `BillQueueManager` | Process bill payments in order (FIFO) |
| `Queue` (LinkedList) | `AccountRequestManager` | Simulate admin account approval workflow (FIFO) |
| `Array` | `BankSystem` | Physical storage of 3 predefined accounts |

The program is split into three parts:
- **Part 1** runs automatic demos for Tasks 1–5 with sample data matching the expected output.
- **Part 2** demonstrates a fixed-size `BankAccount[3]` array (physical data structure).
- **Part 3** launches an interactive console menu with Bank, ATM, and Admin sections.

---

## Part 1 — Logical Data Structures

---

### Task 1 — Bank Account Storage (LinkedList)

**Description:** Created a `BankAccount` class with fields `accountNumber`, `username`, and `balance`. An `AccountManager` class uses a `LinkedList<BankAccount>` to store accounts. Supports adding, displaying all accounts, and searching by username.

**Key methods:**
- `addAccount(BankAccount)` — adds to the LinkedList
- `displayAccounts()` — iterates and prints all accounts
- `searchByUsername(String)` — linear search through the list

**Expected Output:**
```
Account added successfully.
Accounts List:
1. Ali - Balance: 150000
2. Sara - Balance: 220000
Search 'Ali': Ali (Acc#: ACC001) - Balance: 150000
```

**Screenshot:**
<img width="1915" height="575" alt="Снимок экрана_20260413_001955" src="https://github.com/user-attachments/assets/bee58f37-c62b-4564-b7aa-265e2648a4b0" />


---

### Task 2 — Deposit & Withdraw Operations

**Description:** Extended `AccountManager` with `deposit()` and `withdraw()` methods. Each method searches the LinkedList for the account by username, then updates the balance in-place.

**Key methods:**
- `deposit(String username, double amount)` — increases balance
- `withdraw(String username, double amount)` — decreases balance (with insufficient funds check)

**Expected Output:**
```
Enter username: Ali
Deposit: 50000
New balance: 200000
```

**Screenshot:**
<img width="580" height="663" alt="изображение" src="https://github.com/user-attachments/assets/bb1142af-0966-4b02-8217-668486baabdd" />

---

### Task 3 — Transaction History (Stack / LIFO)

**Description:** Created a `TransactionManager` class using Java's `Stack<String>`. Every deposit, withdrawal, or bill payment is recorded as a string entry on the stack. The user can peek at the last transaction or undo (pop) it.

**Key methods:**
- `addTransaction(String)` — `push()` onto the stack
- `undoLastTransaction()` — `pop()` from the stack
- `displayLastTransaction()` — `peek()` at the top

**Why Stack?** A Stack is LIFO (Last In, First Out), which is perfect for "undo" functionality — the most recent action is always the first to be reversed.

**Expected Output:**
```
Deposit 50000 to Ali
Withdraw 20000 from Ali
Last transaction: Withdraw 20000 from Ali
Undo -> Withdraw 20000 from Ali removed
```

**Screenshot:**
<img width="820" height="866" alt="изображение" src="https://github.com/user-attachments/assets/4902af6a-bc25-4c69-8518-369e89bd2b38" />


---

### Task 4 — Bill Payment Queue (Queue / FIFO)

**Description:** Created a `BillQueueManager` class using `Queue<String>` (backed by `LinkedList`). Bills are added to the end of the queue and processed from the front — simulating a real payment processing order.

**Key methods:**
- `addBill(String)` — `add()` to the queue
- `processNextBill()` — `poll()` from the front
- `displayQueue()` — iterates remaining bills

**Why Queue?** A Queue is FIFO (First In, First Out), which mirrors how banks process payments — the earliest submitted request is handled first.

**Expected Output:**
```
Added: Electricity Bill
Added: Internet Bill
Processing: Electricity Bill
Remaining bills:
  - Internet Bill
```

**Screenshot:**
<img width="671" height="922" alt="изображение" src="https://github.com/user-attachments/assets/d7da233b-fc73-467a-908d-3542ef0a1057" />


---

### Task 5 — Account Opening Queue (Admin Simulation)

**Description:** Created an `AccountRequestManager` class using `Queue<BankAccount>`. When a user submits a new account request, it is added to the queue. The admin processes requests one by one (FIFO), and approved accounts are moved into the main `AccountManager` LinkedList — simulating a real banking workflow.

**Key methods:**
- `submitRequest(BankAccount)` — user adds request to queue
- `processNextRequest()` — admin removes from front, returns account
- `displayPendingRequests()` — shows all pending requests

**Expected Output:**
```
Account request submitted for: Omar
Account request submitted for: Lena
Pending Account Requests:
1. Omar (Acc#: REQ001)
2. Lena (Acc#: REQ002)
Processing account request for: Omar
Account for Omar approved and added to system.
Pending Account Requests:
1. Lena (Acc#: REQ002)
```

**Screenshot:**
<img width="670" height="751" alt="изображение" src="https://github.com/user-attachments/assets/3dea9794-d705-485d-89c0-29a9db401639" />
]_

---

## Part 2 — Physical Data Structures

---

### Task 6 — Array of Bank Accounts

**Description:** Demonstrated a physical data structure by creating a fixed-size `BankAccount[3]` array inside `BankSystem`. Three predefined accounts are stored and printed. Unlike a LinkedList, arrays have a fixed size allocated in contiguous memory at runtime.

**Physical vs Logical:**
- **Physical (Array):** Fixed size, fast index access `O(1)`, memory allocated upfront.
- **Logical (LinkedList):** Dynamic size, sequential access `O(n)`, memory allocated per node.

**Expected Output:**
```
===== Part 2: Physical Data Structures (Array) =====
Predefined accounts stored in BankAccount[3] array:
  Alice (Acc#: 001) - Balance: 100000
  Bob (Acc#: 002) - Balance: 250000
  Carol (Acc#: 003) - Balance: 75000
```

**Screenshot:**
<img width="670" height="750" alt="изображение" src="https://github.com/user-attachments/assets/e98df95b-3efe-49ba-be75-d0f0505a878f" />

---

## Part 3 — Mini Banking Menu

**Description:** An interactive console menu integrates all tasks into a unified system. All managers share a single `BankSystem` instance so data persists across menu actions.

**Menu Structure:**
```
========== MAIN MENU ==========
1 - Enter Bank
2 - Enter ATM
3 - Admin Area
4 - Exit
```

| Menu | Available Actions |
|---|---|
| **Bank** | Submit account opening request, Deposit money, Withdraw money |
| **ATM** | Balance enquiry, Withdraw |
| **Admin** | Process account request queue, View/add/process bill queue, View all accounts, View transaction history, Undo last transaction |

**Data structures used across menus:**
- Bank → uses `LinkedList` (accounts) and `Stack` (transaction history)
- ATM → uses `LinkedList` (balance lookup and withdraw)
- Admin → uses both `Queue` instances and `Stack`

**Screenshot:**
<img width="670" height="498" alt="изображение" src="https://github.com/user-attachments/assets/5642b396-6d9c-4d25-99b3-eb2ed3b724e5" />


---

## Issues & Solutions

| Issue | Solution |
|---|---|
| `structures/` subfolder classes not recognized by IntelliJ | Added `package assignment2.structures;` to all structure files and `import assignment2.structures.*;` in `BankSystem` and `Main` |
| Withdraw allowed negative balance | Added `if (balance < amount)` guard in `withdraw()` method |
| Queue vs Stack confusion for bill payments | Confirmed Queue (FIFO) is correct — first bill submitted should be processed first, not last |
| Account approved in Task 5 not appearing in main list | Fixed by returning the `BankAccount` object from `processNextRequest()` and passing it to `accountManager.addApprovedAccount()` |

---

*Assignment #2 — Physical & Logical Data Structures | Astana IT University*
