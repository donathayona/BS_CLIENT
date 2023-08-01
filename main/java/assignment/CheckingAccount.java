package assignment;

import java.util.HashMap;
import java.util.Map;

public class CheckingAccount extends Account {
    private Map<Integer, Double> uncashedChecks;

    public CheckingAccount(String accountNumber, String accountHolder, double balance, Status accountStatus) {
        super(accountNumber, accountHolder, balance, accountStatus);
        uncashedChecks = new HashMap<>();
    }

    public void writeCheck(int checkNumber, double amount) throws AccountInactiveException, AccountClosedException, InsufficientFundsException {
        if (getAccountStatus() != Status.ACTIVE) {
            if (getAccountStatus() == Status.INACTIVE) {
                throw new AccountInactiveException("Account is inactive.");
            } else {
                throw new AccountClosedException("Account is closed.");
            }
        }

        if (getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in the account.");
        }

        withdraw(amount);
        uncashedChecks.put(checkNumber, amount);
    }

    public void cashCheck(int checkNumber, CheckingAccount recipientAccount) throws AccountInactiveException, AccountClosedException, CheckNotFoundException {
        if (getAccountStatus() != Status.ACTIVE) {
            if (getAccountStatus() == Status.INACTIVE) {
                throw new AccountInactiveException("Account is inactive.");
            } else {
                throw new AccountClosedException("Account is closed.");
            }
        }

        Double checkAmount = uncashedChecks.remove(checkNumber);
        if (checkAmount == null) {
            throw new CheckNotFoundException("Check not found.");
        }

        recipientAccount.deposit(checkAmount);
    }

}
