package exceptions;

public class EmailAlreadyExistException extends Exception{
    public EmailAlreadyExistException() {
        super("This email already in use." +
                " Please try another");
    }
}
