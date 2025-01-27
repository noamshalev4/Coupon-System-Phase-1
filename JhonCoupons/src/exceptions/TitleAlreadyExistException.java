package exceptions;

public class TitleAlreadyExistException extends Exception {
    public TitleAlreadyExistException() {
        super("There is already coupon with this title at your company." +
                " Please try again");
    }
}
