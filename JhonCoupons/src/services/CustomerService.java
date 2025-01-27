package services;

import beans.Category;
import beans.Coupon;
import beans.Customer;
import exceptions.InvalidUserInfoException;
import exceptions.UnablePurchaseException;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerService extends ClientService {
    private int customerID;

    public CustomerService() throws SQLException {
    }

    //Login as customer
    @Override
    public boolean login(String email, String password) throws SQLException, InvalidUserInfoException {
        if (!customersDAO.isCustomerExistLogin(email, password)) {
            throw new InvalidUserInfoException();
        } else {
            System.out.println("customer login");
            return true;
        }
    }

    //Get one coupon as customer
    public Coupon getOneCoupon(int couponId) throws SQLException {
        return couponsDAO.getOneCoupon(couponId);
    }

    //Auxiliary function for purchaseCoupon function
    private static CouponCondition getCouponCondition(Coupon coupon, java.sql.Date sqlToday, boolean isTheSameCoupon) {
        if (coupon.getAmount() == 0) {
            return CouponCondition.OUT_OF_STOCK;
        } else if (coupon.getEndDate().before(sqlToday)) {
            return CouponCondition.EXPIRED;
        } else if (isTheSameCoupon) {
            return CouponCondition.ALREADY_PURCHASED;
        } else {
            return CouponCondition.VALID;
        }
    }

    //Purchase coupon as customer
    public void purchaseCoupon(Coupon coupon, int customerID) throws SQLException, UnablePurchaseException {
        boolean isTheSameCoupon = false;
        LocalDate today = LocalDate.now();
        Date sqlToday = Date.valueOf(today);
        CouponCondition condition = getCouponCondition(coupon, sqlToday, isTheSameCoupon);
        ArrayList<Coupon> allCustomerCoupons = couponsDAO.getOneCoupon(coupon.getId());
        if (!allCustomerCoupons.isEmpty()) {
            isTheSameCoupon = true;
        }
        switch (condition) {
            case OUT_OF_STOCK -> throw new UnablePurchaseException("This coupon is out of stock");
            case EXPIRED -> throw new UnablePurchaseException("This coupon expired");
            case ALREADY_PURCHASED -> throw new UnablePurchaseException("This coupon already bought by this customer");
            case VALID -> {
                couponsDAO.addCouponPurchase(customerID, coupon.getId(),
                        sqlToday);
                System.out.println("The coupon: " + coupon.getId() + " was purchased by customer number: " + customerID);
            }
        }
    }

    //Get all customer coupons as customer
    public ArrayList<Coupon> getCustomerCoupons(int customerID) throws SQLException {
        return couponsDAO.getAllCouponsByCustomer(customerID);
    }

    //Get all customer coupons by category as customer
    public ArrayList<Coupon> getCustomerCouponsByCategory(int customerID, Category category) throws SQLException {
        return couponsDAO.getAllCouponsOfCustomerByCategory(customerID, category);
    }

    //Get all customer coupons by set maximum price as customer
    public ArrayList<Coupon> getCustomerCouponsByMaxPrice(int customerID, double maxPrice) throws SQLException {
        return couponsDAO.getAllCouponsOfCustomerByMaxPrice(customerID, maxPrice);
    }

    //Get one customer details as customer
    public Customer getCustomerDetails(int customerID) throws SQLException {
        return customersDAO.getOneCustomer(customerID);
    }
}

