# Assignment #2 – Physical & Logical Data Structures (Banking System)

**Student:** Ulgaisyn Daulet 
**Group:** IT-2502

---

## Project Structure

```
assignment2/
└── src/
    ├── BankAccount.java   – Model class
    └── Main.java          – All tasks + menus
```

---

## How to Run

1. Open **IntelliJ IDEA** → *New Project* → *Java* (no build system needed).
2. Copy `BankAccount.java` and `Main.java` into the `src/` folder.
3. Right-click `Main.java` → **Run 'Main.main()'**.

---

## Part 1 – Logical Data Structures

### Task 1 – Bank Account Storage (LinkedList)

`BankAccount` stores `accountNumber`, `username`, and `balance`.  
A `LinkedList<BankAccount>` holds all accounts.  
Supported operations: **add**, **display all**, **search by username**.

```
Account added successfully
Accounts List:
1. Ali  – Balance: 150000
2. Sara – Balance: 220000
```

**Why LinkedList?** Accounts are added/removed dynamically. LinkedList allows O(1) insertion without pre-allocating size, unlike arrays.

---

### Task 2 – Deposit & Withdraw

Deposits and withdrawals update the balance **inside** the LinkedList node directly (object reference is mutated).

```
Enter username: Ali
Deposit: 50000
New balance: 200000
```

---

### Task 3 – Transaction History (Stack – LIFO)

`Stack<String> history` records every deposit/withdrawal as a string.  
Supports: **push** (add), **peek** (view last), **pop** (undo).

```
Deposit 50000 to Ali
Withdraw 20000 from Ali
Last transaction: Withdraw 20000 from Ali
Undo → Withdraw 20000 from Ali removed
```

**Why Stack?** LIFO (Last In First Out) matches "undo" semantics — the most recent action is the first to be undone.

---

### Task 4 – Bill Payment Queue (Queue – FIFO)

`Queue<String> billQueue` backed by `LinkedList`.  
Supports: **add** (enqueue), **process** (dequeue), **display**.

```
Added: Electricity Bill
Added: Internet Bill
Processing: Electricity Bill
Remaining: Internet Bill
```

**Why Queue?** Bills are processed in the order they were submitted — First In First Out, just like a real service queue.

---

### Task 5 – Account Opening Queue (Admin Simulation)

`Queue<String> accountRequests` collects new-account requests.  
Admin processes the queue → each dequeued name becomes a real `BankAccount` added to the LinkedList.

```
Request submitted for: Kamila
Pending Requests: Kamila
Account created for: Kamila
Account added successfully
```

This simulates a real bank workflow where a customer submits a form and an admin later approves it.

---

## Part 2 – Physical Data Structures

### Task 6 – Array Storage

`BankAccount[] arr = new BankAccount[3]` — fixed-size, contiguous memory.  
Three predefined accounts are stored and printed at program start-up.

```
--- Part 2: Physical Array (BankAccount[3]) ---
Stored accounts in array:
1. Account[ACC001] Ali   – Balance: 150000
2. Account[ACC002] Sara  – Balance: 220000
3. Account[ACC003] Damir – Balance: 95000
Memory note: Array occupies contiguous memory; size is fixed at 3.
```

**Physical vs Logical:**  
- **Array (physical):** fixed size, direct index access, O(1) lookup, stored contiguously in RAM.  
- **LinkedList (logical):** dynamic size, node pointers, O(n) traversal, nodes scattered in memory.

---

## Part 3 – Mini Banking Menu

```
╔══════════════════════════╗
║     BANKING SYSTEM       ║
╠══════════════════════════╣
║ 1 – Enter Bank           ║
║ 2 – Enter ATM            ║
║ 3 – Admin Area           ║
║ 4 – Exit                 ║
╚══════════════════════════╝
```

| Menu  | Features |
|-------|----------|
| **Bank** | Submit account request, Deposit, Withdraw, Pay bill, View/Undo history |
| **ATM** | Balance enquiry, Withdraw |
| **Admin** | Process account queue, View/process bill queue, View all accounts, Search |

All menus are integrated with the shared `LinkedList`, `Stack`, and `Queue` structures from Tasks 1–5.

---

## Data Structure Summary

| Structure | Java Class | Used For | Reason |
|-----------|-----------|----------|--------|
| `LinkedList<BankAccount>` | `java.util.LinkedList` | Account storage | Dynamic size, O(1) add |
| `Stack<String>` | `java.util.Stack` | Transaction history / undo | LIFO – last action undone first |
| `Queue<String>` (LinkedList) | `java.util.LinkedList` | Bill queue, account requests | FIFO – fair order processing |
| `BankAccount[]` | Java array | Physical storage demo | Fixed size, contiguous memory |

---

## Work Process & Challenges

1. **Started** with the `BankAccount` model class to define the data shape.
2. **Built Task 1–2** together since deposit/withdraw naturally extend account storage.
3. **Task 3 (Stack):** The `undo` feature required pushing a descriptive string *before* modifying the balance, so the log is always accurate.
4. **Tasks 4–5 (Queue):** Used the same `LinkedList` implementation for both queues; distinguished them by separate variable names.
5. **Task 6 (Array):** Demonstrated at startup to show the contrast between a fixed physical structure and the dynamic LinkedList used throughout.
6. **Mini Menu (Part 3):** Tied all helpers into three sub-menus using a `while(true)` loop with `switch` statements. Added input validation to avoid crashes on non-numeric amount input.

**No major issues encountered.** The main design decision was keeping all data structures as `static` fields in `Main.java` so every menu method shares the same state without passing parameters everywhere.
