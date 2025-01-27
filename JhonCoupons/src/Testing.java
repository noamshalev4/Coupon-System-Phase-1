
import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import exceptions.*;
import services.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Testing {
    public static void testAll() throws InvalidUserInfoException, SQLException, ParseException, TitleAlreadyExistException, UnablePurchaseException, CompanyAlreadyExistException, EmailAlreadyExistException {
        CouponExpirationDailyJob couponExpirationDailyJob = null;
        try {
            couponExpirationDailyJob = new CouponExpirationDailyJob();
            //Activate daile job
            couponExpirationDailyJob.start();

            //Login as admin
            AdminService adminService = null;
            adminService = (AdminService) LoginManager.getInstance().
                    login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);

            //Add company as admin
/*
            adminService.addCompany(new Company("Noam_Burger7",
                    "noamBurger7@walla.com", "0123456789", null));
*/
            //Update company as admin
/*
            Company company = adminService.getOneCompany(12);
            company.setPassword("99999999");
            adminService.updateCompany(company);
*/
            //Delete Company as admin
/*
            adminService.deleteCompany(16);
*/
            //Add 3 more companies to the next test
/*
            adminService.addCompany(new Company("Noam_Tech",
                    "NoamTech@NoamTech.com", "123456", null));
            adminService.addCompany(new Company("Joni_Tech",
                    "JoniTech@JoniTech.com", "112233", null));
            adminService.addCompany(new Company("Daya_Tech",
                    "DayaTech@DayaTech.com", "654321", null));
*/
            //Get a list of all companies as admin
/*
            ArrayList<Company> companiesList = new ArrayList<>();
            companiesList = adminService.getAllCompanies();
            for (Company c : companiesList) {
                System.out.println(c);
            }
*/
            //Get details on one company as admin
/*
            Company oneCompany = new Company();
            oneCompany = adminService.getOneCompany(8);
            System.out.println(oneCompany);
*/
            //Add customer as admin
/*
            adminService.addCustomer(new Customer("Yonatan", "Rapaport",
                    "YonatanRapaport@gmail.com", "400300200100", null));
*/
            //Update customer as admin
/*
            Customer customer = adminService.getOneCustomer(6);
            customer.setEmail("JuanIglasias10@gmail.com");
            adminService.updateCustomer(customer);
*/
            //Add more customers for the next test
/*
            adminService.addCustomer(new Customer("Noam", "Shalev",
                    "NoamsBest@gmail.com", "48265482654", null));
            adminService.addCustomer(new Customer("Yonatan", "Shalev",
                    "JoniSh3@gmail.com", "123456789", null));
            adminService.addCustomer(new Customer("Daya", "Shalev",
                    "DayaSh3@gmail.com", "987654321", null));
*/
            //Delete customer as admin
/*
            adminService.deleteCustomer(8);
*/
            //Get a list of customers as admin
/*
            ArrayList<Customer> customersList = adminService.getAllCustomers();
            for (Customer c : customersList) {
                System.out.println(c);
            }
*/
            //Get details on one customer as admin
/*
            Customer oneCustomer = adminService.getOneCustomer(5);
            System.out.println(oneCustomer);
*/
            //Login as company
            CompanyService companyService = null;
            companyService = (CompanyService) LoginManager.getInstance().
                    login("NoamTech@NoamTech.com", "123456", ClientType.COMPANY);
/*
            //Add coupon as company
            String format = "yyyy-MM-dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            String startDate = "2024-08-01";
            java.util.Date startDateAppUtil1 = dateFormat.parse(startDate);
            java.sql.Date startDateSql1 = new java.sql.Date(startDateAppUtil1.getTime());
            String endDate = "2024-10-31";
            java.util.Date endDateUtil1 = dateFormat.parse(endDate);
            java.sql.Date endDateSql1 = new java.sql.Date(endDateUtil1.getTime());

            companyService.addCoupon(new Coupon
                    (companyService.getCompanyDetails(7).getId(),
                            companyService.getAllCategories().get(3),
                            "Day in the spa",
                            "Allow you to enjoy all the spa attractions except massage",
                            startDateSql1, endDateSql1, 20, 120, "💆‍♂️"));
*/
            //Update coupon as company
/*
            String format = "yyyy-MM-dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            String startDate = "2024-05-01";
            java.util.Date startDateAppUtil2 = dateFormat.parse(startDate);
            java.sql.Date startDateSql2 = new java.sql.Date(startDateAppUtil2.getTime());
            String endDate = "2024-07-31";
            java.util.Date endDateUtil2 = dateFormat.parse(endDate);
            java.sql.Date endDateSql2 = new java.sql.Date(endDateUtil2.getTime());

            Coupon coupon = companyService.getOneCoupon(27);
            coupon.setAmount(30);
            companyService.updateCoupon(coupon);
*/
            //Delete coupon as company
/*
            companyService.deleteCoupon(23);
*/
            //Add more coupons for the next test
/*
            String format = "yyyy-MM-dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            String startDate = "2024-08-01";
            java.util.Date startDateAppUtil3 = dateFormat.parse(startDate);
            java.sql.Date startDateSql3 = new java.sql.Date(startDateAppUtil3.getTime());
            String endDate = "2024-10-31";
            java.util.Date endDateUtil3 = dateFormat.parse(endDate);
            java.sql.Date endDateSql3 = new java.sql.Date(endDateUtil3.getTime());

            companyService.addCoupon(new Coupon
                    (companyService.getCompanyDetails(8).getId(),
                            companyService.getAllCategories().get(2),
                            "1+1", "Allow you to purchase 2 tickets at the price of 1",
                            startDateSql3, endDateSql3, 10, 45, "haha"),8);

            String startDate4 = "2024-01-01";
            java.util.Date startDateAppUtil4 = dateFormat.parse(startDate4);
            java.sql.Date startDateSql4 = new java.sql.Date(startDateAppUtil4.getTime());
            String endDate4 = "2024-06-30";
            java.util.Date endDateUtil4 = dateFormat.parse(endDate4);
            java.sql.Date endDateSql4 = new java.sql.Date(endDateUtil4.getTime());

            companyService.addCoupon(new Coupon
                    (companyService.getCompanyDetails(7).getId(),
                            companyService.getAllCategories().get(9),
                            "1+1", "Allow you to purchase 2 packages of A4 papers at the price of 1",
                            startDateSql4, endDateSql4, 100, 20, "😏"),7);

            String startDate5 = "2024-08-01";
            java.util.Date startDateAppUtil5 = dateFormat.parse(startDate5);
            java.sql.Date startDateSql5 = new java.sql.Date(startDateAppUtil5.getTime());
            String endDate5 = "2024-10-31";
            java.util.Date endDateUtil5 = dateFormat.parse(endDate5);
            java.sql.Date endDateSql5 = new java.sql.Date(endDateUtil5.getTime());

            companyService.addCoupon(new Coupon
                    (companyService.getCompanyDetails(9).getId(),
                            companyService.getAllCategories().get(7),
                            "1+1", "Allow you to purchase 2 enters for Noam`s Gallery at the price of 1",
                            startDateSql5, endDateSql5, 5, 90, "😁"), 9);

            String startDate6 = "2024-01-01";
            java.util.Date startDateAppUtil6 = dateFormat.parse(startDate6);
            java.sql.Date startDateSql6 = new java.sql.Date(startDateAppUtil6.getTime());
            String endDate6 = "2024-06-30";
            java.util.Date endDateUtil6 = dateFormat.parse(endDate6);
            java.sql.Date endDateSql6 = new java.sql.Date(endDateUtil6.getTime());

            companyService.addCoupon(new Coupon
                    (companyService.getCompanyDetails(7).getId(),
                            companyService.getAllCategories().get(3),
                            "2+2=5", "Allow you to purchase 5 enters for Noam`s Spa at the price of 2",
                            startDateSql6, endDateSql6, 30, 360, "😊"), 7);
*/
            //Get all company coupons by company
/*
        ArrayList<Coupon> allCompanyCoupons = companyService.getCompanyCoupons(7);
        for (Coupon c: allCompanyCoupons){
            System.out.println(c);
        }
*/
            //Get all company coupons by category as company
/*
            ArrayList<Coupon> allCompanyCouponsByCategory = companyService.getCompanyCouponsByCategory(7, Category.ART);
            for (Coupon c : allCompanyCouponsByCategory) {
                System.out.println(c);
            }
*/
            //Get all company coupons by max price as company
/*
            ArrayList<Coupon> allCompanyCouponsByMaxPrice = companyService.getCompanyCouponsByMaxPrice
                    (7,9100.0);
            for (Coupon c: allCompanyCouponsByMaxPrice){
                System.out.println(c);
            }
*/
            //Get company details as company
/*
            Company company = companyService.getCompanyDetails(8);
            System.out.println(company);
*/
            //Add more coupon for the next test
/*
            String format = "yyyy-MM-dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            String startDate = "2024-10-01";
            java.util.Date startDateAppUtil3 = dateFormat.parse(startDate);
            java.sql.Date startDateSql3 = new java.sql.Date(startDateAppUtil3.getTime());
            String endDate = "2024-10-31";
            java.util.Date endDateUtil3 = dateFormat.parse(endDate);
            java.sql.Date endDateSql3 = new java.sql.Date(endDateUtil3.getTime());

            companyService.addCoupon(new Coupon
                    (companyService.getCompanyDetails(8).getId(),
                            companyService.getAllCategories().get(4),
                            "40%", "Allow you to purchase any computer from the sale at 40% discount",
                            startDateSql3, endDateSql3, 1000, 2500, "🖥️"),8);
*/
            //Login as customer
            CustomerService customerService = null;
            customerService = (CustomerService) LoginManager.getInstance().
                    login("NoamsBest@gmail.com", "48265482654", ClientType.CUSTOMER);

            //Purchase coupon as customer
/*
            Coupon coupon = customerService.getOneCoupon(45);
            customerService.purchaseCoupon(coupon, 7);
*/
            //Get all customer coupons by customer
/*
            ArrayList<Coupon> allCustomerCoupons = customerService.getCustomerCoupons(7);
            for (Coupon c: allCustomerCoupons){
                System.out.println(c);
            }
*/
            //Get all customer coupons by category by customer
/*
            ArrayList<Coupon> allCustomerCouponsByCategory = customerService.getCustomerCouponsByCategory(7, Category.FASHION);
            for (Coupon c: allCustomerCouponsByCategory){
                System.out.println(c);
            }
*/
            //Get all customer coupons by max price by customer
/*
            ArrayList<Coupon> allCustomerCouponsByMaxPrice = customerService.getCustomerCouponsByMaxPrice(7,9100);
            for (Coupon c: allCustomerCouponsByMaxPrice){
                System.out.println(c);
            }
*/
            //Get customer details by customer
/*
            Customer oneCustomer = customerService.getCustomerDetails(7);
            System.out.println(oneCustomer);
*/
        } catch (SQLException e/* | ParseException */
           /*  | TitleAlreadyExistException e /*| EmailAlreadyExistException */  /* | CompanyAlreadyExistException */) {
            //  System.out.println(e.getMessage());
            //  } catch (TitleAlreadyExistException e) {
            //   System.out.println(e.getMessage());
            //   } catch (ParseException e) {
            System.out.println(e.getMessage());
        } finally {
            if (couponExpirationDailyJob != null) {
                //Turn off daily job
                couponExpirationDailyJob.stopJob();
            }
        }
    }
}



