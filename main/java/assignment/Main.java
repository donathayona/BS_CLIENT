package assignment;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("The bank");

        CheckingAccount account1 = new CheckingAccount("123", "John Doe", 100.0, Account.Status.ACTIVE);
        SavingsAccount account2 = new SavingsAccount("456", "Jane Doe", 200.0, 0.05, Account.Status.ACTIVE);

        bank.addAccount(account1);
        bank.addAccount(account2);

        // Test deposit
        try {
            account1.deposit(50.0);
            System.out.println("Account 1 balance after deposit: " + account1.getBalance());
        } catch (AccountInactiveException | AccountClosedException e) {
            System.out.println(e.getMessage());
        }

        // Test addAccount
        CheckingAccount account3 = new CheckingAccount("789", "Aron", 300.0, Account.Status.ACTIVE);
        bank.addAccount(account3);
        System.out.println("Total number of accounts in the bank: " + bank.getAccounts().size());

        // Test writeCheck
        try {
            account1.writeCheck(300, 50.0);
            System.out.println("Account 1 balance after writing a check: " + account1.getBalance());
        } catch (InsufficientFundsException | AccountInactiveException | AccountClosedException | CheckNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Test applyInterest
        try {
            account2.applyInterest();
            System.out.println("Account 2 balance after applying interest: " + account2.getBalance());
        } catch (AccountInactiveException | AccountClosedException e) {
            System.out.println(e.getMessage());
        }

        // Perform getTransactionHistory test
        List<Transaction> transactionHistory = bank.getTransactionHistory();
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}
