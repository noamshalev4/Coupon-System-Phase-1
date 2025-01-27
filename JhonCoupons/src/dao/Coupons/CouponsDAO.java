package dao.Coupons;

import beans.Category;
import beans.Coupon;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO {
    public void addCoupon(Coupon coupon) throws SQLException;
    public ArrayList<Category> getAllCategories() throws SQLException;
    public void updateCoupon(Coupon coupon) throws SQLException;
    public void deleteCoupon(int couponID) throws SQLException;
    public ArrayList<Coupon> getAllCoupons() throws SQLException;
    public ArrayList<Coupon> getAllCouponsByCompany(int companyID) throws SQLException;
    public ArrayList<Coupon> getAllCouponsOfCompanyByCategory(int companyID, Category category) throws SQLException;
    public ArrayList<Coupon> getAllCouponsOfCompanyByMaxPrice(int companyID, double maxPrice) throws SQLException;
    public ArrayList<Coupon> getAllCouponsByCustomer(int customerId) throws SQLException;
    public ArrayList<Coupon> getAllCouponsOfCustomerByCategory(int customerID, Category category) throws SQLException;
    public ArrayList<Coupon> getAllCouponsOfCustomerByMaxPrice(int companyID, double maxPrice) throws SQLException;
    public Coupon getOneCoupon(int couponID) throws SQLException;
    public void addCouponPurchase(int customerID, int couponID, Date dateOfPurchase) throws SQLException;
    public void deleteAllCompanyCoupons(int companyID) throws SQLException;
    public void deleteAllCompanyHistoryCoupons(int companyID) throws SQLException;
    public void deleteAllCustomerHistoryCoupons(int customerID) throws SQLException;
    public void deleteAllCouponHistoryByCouponID(int couponID) throws SQLException;


}
