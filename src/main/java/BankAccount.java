public class BankAccount {
    private String accountNumber;
    private String username;
    private double balance;

    public BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    public void deposit(double amount) { this.balance += amount; }
    public boolean withdraw(double amount) {
        if (amount <= balance) { this.balance -= amount; return true; }
        return false;
    }

    public String getUsername() { return username; }
    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return String.format("[%s] %-10s | Balance: $%.2f", accountNumber, username, balance);
    }
}