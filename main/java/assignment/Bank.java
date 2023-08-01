package assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private String name;
    private Map<String, Account> accounts;

    public Bank(String name) {
        this.name = name;
        this.accounts = new HashMap<>();
    }

    
    public void addAccount(Account account) {
        String accountNumber = account.getAccountNumber();
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account already exists.");
        }

        accounts.put(accountNumber, account);
    }

    public void removeAccount(String accountNumber) throws AccountNotFoundException {
        if (!accounts.containsKey(accountNumber)) {
            throw new AccountNotFoundException("Account not found.");
        }

        accounts.remove(accountNumber);
    }

    public double getTotalAssets() {
        double totalAssets = 0;
        for (Account account : accounts.values()) {
            totalAssets += account.getBalance();
        }
        return totalAssets;
    }

    public List<Transaction> getTransactionHistory() {
        List<Transaction> transactionHistory = new ArrayList<>();

        for (Account account : accounts.values()) {
            List<Transaction> accountTransactions = account.getTransactions();
            if (!accountTransactions.isEmpty()) {
                for (Transaction transaction : accountTransactions) {
                    transactionHistory.add(transaction);
                }
            }
        }

        return transactionHistory;
    }





    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public String getBankName(){
        return this.name;
    }
}
