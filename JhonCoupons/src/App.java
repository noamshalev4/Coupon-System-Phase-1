import beans.Company;
import dao.Companies.CompaniesDAO;
import exceptions.*;
import services.AdminService;
import services.CouponExpirationDailyJob;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) throws SQLException, InvalidUserInfoException, TitleAlreadyExistException, ParseException, UnablePurchaseException, CompanyAlreadyExistException, EmailAlreadyExistException {

        Testing.testAll();
        System.out.println("server is running");

    }
}
