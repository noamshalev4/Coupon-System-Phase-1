package exceptions;

public class ExpiredCouponException extends Exception{
    public ExpiredCouponException (){
        super("There was a problem with deleting expired coupon");
    }
}
