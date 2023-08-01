package assignment;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate, Status accountStatus) {
        super(accountNumber, accountHolder, balance, accountStatus);
        this.interestRate = interestRate;
    }

    public void applyInterest() throws AccountInactiveException, AccountClosedException {
        if (getAccountStatus() != Status.ACTIVE) {
            if (getAccountStatus() == Status.INACTIVE) {
                throw new AccountInactiveException("Account is inactive.");
            } else {
                throw new AccountClosedException("Account is closed.");
            }
        }else{
            double interestAmount = getBalance() * interestRate;
            deposit(interestAmount); // Add the interest amount to the balance
        }
    }

}
