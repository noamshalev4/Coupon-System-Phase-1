package services;

import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.InvalidUserInfoException;
import exceptions.TitleAlreadyExistException;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyService extends ClientService {
    private int companyID;

    public CompanyService() throws SQLException {
    }

    //Login as company
    @Override
    public boolean login(String email, String password) throws SQLException, InvalidUserInfoException {
        if (!companiesDAO.isCompanyExists(email, password)) {
            throw new InvalidUserInfoException();
        } else {
            System.out.println("Company login");
            return true;
        }
    }

    //Auxiliary function for addCoupon function
    public ArrayList<Category> getAllCategories() throws SQLException {
        return couponsDAO.getAllCategories();
    }

    //Add new coupon only if the title is unique from the same company
    public void addCoupon(Coupon coupon) throws SQLException, TitleAlreadyExistException {
        ArrayList<Coupon> allCompanyCoupons = couponsDAO.getAllCouponsByCompany(coupon.getCompanyId());
        for (Coupon c : allCompanyCoupons) {
            if (coupon.getTitle().equals(c.getTitle()) &&
                    coupon.getCompanyId() == c.getCompanyId()) {
                throw new TitleAlreadyExistException();
            }
        }
        couponsDAO.addCoupon(coupon);
        System.out.println("Coupon was added");
    }

    //Get one coupon as company
    public Coupon getOneCoupon(int couponId) throws SQLException {
        return couponsDAO.getOneCoupon(couponId);
    }

    //Update coupon as company
    public void updateCoupon(Coupon coupon) throws SQLException {
        couponsDAO.updateCoupon(coupon);
    }

    //Delete coupon as company
    public void deleteCoupon(int couponID) throws SQLException {
        couponsDAO.deleteAllCouponHistoryByCouponID(couponID);
        couponsDAO.deleteCoupon(couponID);
        System.out.println("The coupon number: " + couponID + " was deleted");
    }

    //Get all company coupons as company
    public ArrayList<Coupon> getCompanyCoupons(int companyID) throws SQLException {
        return couponsDAO.getAllCouponsByCompany(companyID);
    }

    //Get all company coupons by category as company
    public ArrayList<Coupon> getCompanyCouponsByCategory(int companyID, Category category) throws SQLException {
        return couponsDAO.getAllCouponsOfCompanyByCategory(companyID, category);
    }

    //Get all company coupons by set maximum price as company
    public ArrayList<Coupon> getCompanyCouponsByMaxPrice(int companyID, double maxPrice) throws SQLException {
        return couponsDAO.getAllCouponsOfCompanyByMaxPrice(companyID, maxPrice);
    }

    //Get one company detail as company
    public Company getCompanyDetails(int companyID) throws SQLException {
        return companiesDAO.getOneCompany(companyID);
    }
}
