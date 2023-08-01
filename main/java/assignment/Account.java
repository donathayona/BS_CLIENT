package assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private String accountHolder;
    private Status accountStatus;
    private List<Transaction> transactions;
    String time;

    public enum Status {
        ACTIVE, INACTIVE, CLOSED
    }

    public Account(String accountNumber, String accountHolder, double balance, Status accountStatus) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
        this.accountStatus = accountStatus;
        this.transactions = new ArrayList<>();
        this.time = HelperFunctions.getCurrentTime();

        // make initial deposit transaction
        Transaction initialDeposit = new Transaction(Transaction.Type.DEPOSIT, balance, accountNumber, time);
        transactions.add(initialDeposit);
    }

    public void deposit(double amount) throws AccountInactiveException, AccountClosedException {
        if (getAccountStatus() != Status.ACTIVE) {
            if (getAccountStatus() == Status.INACTIVE) {
                throw new AccountInactiveException("Account is inactive.");
            } else {
                throw new AccountClosedException("Account is closed.");
            }
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        balance += amount;
        Transaction depositTransaction = new Transaction(Transaction.Type.DEPOSIT, amount, getAccountNumber(), HelperFunctions.getCurrentTime());
        transactions.add(depositTransaction);
    }

    public void withdraw(double amount) throws AccountInactiveException, AccountClosedException, InsufficientFundsException {
        if (getAccountStatus() != Status.ACTIVE) {
            if (getAccountStatus() == Status.INACTIVE) {
                throw new AccountInactiveException("Account is inactive.");
            } else {
                throw new AccountClosedException("Account is closed.");
            }
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        if (balance < amount) {
            throw new InsufficientFundsException("Insufficient funds in the account.");
        }

        balance -= amount;
        Transaction withdrawTransaction = new Transaction(Transaction.Type.WITHDRAW, amount, getAccountNumber(), HelperFunctions.getCurrentTime());
        transactions.add(withdrawTransaction);
    }


    public void transfer(double amount, Account destinationAccount) throws AccountInactiveException, AccountClosedException, InsufficientFundsException {
        if (accountStatus != Status.ACTIVE) {
            if (accountStatus == Status.INACTIVE) {
                throw new AccountInactiveException("Account is inactive.");
            } else {
                throw new AccountClosedException("Account is closed.");
            }
        }

        withdraw(amount);
        destinationAccount.deposit(amount);
        transactions.add(new Transaction(Transaction.Type.TRANSFER, amount, accountNumber, HelperFunctions.getCurrentTime()));
    }

    public void transfer(Account destinationAccount, double amount) throws AccountInactiveException, AccountClosedException, InsufficientFundsException {
        if (accountStatus != Status.ACTIVE) {
            if (accountStatus == Status.INACTIVE) {
                throw new AccountInactiveException("Account is inactive.");
            } else {
                throw new AccountClosedException("Account is closed.");
            }
        }

        withdraw(amount);
        destinationAccount.deposit(amount);
        transactions.add(new Transaction(Transaction.Type.TRANSFER, amount, accountNumber, HelperFunctions.getCurrentTime()));
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public Status getAccountStatus() {
        return accountStatus;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setStatus(Status accountStatus) {
        this.accountStatus = accountStatus;
    }
}
