package assignment;


class AccountClosedException extends RuntimeException {
    public AccountClosedException(String message) {
        super(message);
    }
}