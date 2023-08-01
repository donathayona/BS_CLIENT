package assignment;

public class Transaction {
    private Type type;
    private double amount;
    private String accountNumber;
    private String time;

    public enum Type {
        DEPOSIT, WITHDRAW, TRANSFER
    }

    public Transaction(Type type, double amount, String accountNumber, String time) {
        this.type = type;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.time = time;
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTime() {
        return time;
    }
}
