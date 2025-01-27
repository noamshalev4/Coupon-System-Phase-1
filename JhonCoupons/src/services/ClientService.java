package services;

import dao.Companies.CompaniesDAO;
import dao.Companies.CompaniesDBDAO;
import dao.Coupons.CouponsDAO;
import dao.Coupons.CouponsDBDAO;
import dao.Customers.CustomersDAO;
import dao.Customers.CustomersDBDAO;
import exceptions.InvalidUserInfoException;

import java.sql.SQLException;

abstract class ClientService{
    protected CompaniesDAO companiesDAO = new CompaniesDBDAO();
    protected CustomersDAO customersDAO = new CustomersDBDAO();
    protected CouponsDAO couponsDAO = new CouponsDBDAO();

    public ClientService() throws SQLException {
    }

    public boolean login(String email, String password) throws InvalidUserInfoException, SQLException {
        return false;
    }
}
