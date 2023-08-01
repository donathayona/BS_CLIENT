package assignment;

class CheckNotFoundException extends RuntimeException {
    public CheckNotFoundException(String message) {
        super(message);
    }
}