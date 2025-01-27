package dao.Companies;

import beans.Category;
import beans.Company;
import beans.Coupon;
import exceptions.CompanyAlreadyExistException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CompaniesDAO {
    public boolean isCompanyExists(String email, String password) throws SQLException;
    public void addCompany(Company company) throws SQLException;
    public void updateCompany(Company company) throws SQLException, CompanyAlreadyExistException;
    public void deleteCompany(int companyID) throws SQLException;
    public ArrayList<Company> getAllCompanies() throws SQLException;
    public ArrayList<Coupon> getAllCouponsByID(int companyID) throws SQLException;
    public Company getOneCompany(int companyID) throws SQLException;
}
