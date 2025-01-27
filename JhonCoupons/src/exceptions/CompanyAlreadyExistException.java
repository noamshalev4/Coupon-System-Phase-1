package exceptions;

public class CompanyAlreadyExistException extends Exception{
    public CompanyAlreadyExistException(){
        super("The company you try to add already exist by name or email." +
                " Please try again");
    }
}
