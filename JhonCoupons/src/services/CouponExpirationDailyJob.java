package services;

import beans.Coupon;
import dao.Coupons.CouponsDAO;
import dao.Coupons.CouponsDBDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CouponExpirationDailyJob extends Thread {
    private final CouponsDAO couponsDAO = new CouponsDBDAO();
    private boolean quit;

    public CouponExpirationDailyJob() throws SQLException {

    }

    //Active daily job
    @Override
    public void run() {
        LocalDate today = LocalDate.now();
        Date sqlToday = Date.valueOf(today);
        ArrayList<Coupon> allCoupons = new ArrayList<>();
        try {
            allCoupons = couponsDAO.getAllCoupons();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Coupon c : allCoupons) {
            if (c.getEndDate().before(sqlToday)) {
                try {
                    couponsDAO.deleteAllCouponHistoryByCouponID(c.getId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    couponsDAO.deleteCoupon(c.getId());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            sleep(1000 * 60 * 60 * 24);
            //  sleep(1000); //For testing
        } catch (InterruptedException ignored) {
        }
    }

    public void stopJob() {
        quit = true;
    }
}
