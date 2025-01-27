package exceptions;

public class InvalidUserInfoException extends Exception {
    public InvalidUserInfoException() {
        super("Invalid email or password. Please try again");
    }
}
