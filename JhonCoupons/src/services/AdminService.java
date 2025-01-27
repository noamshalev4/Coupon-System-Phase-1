package services;

import beans.Company;
import beans.Customer;
import db.ConnectionPool;
import exceptions.CompanyAlreadyExistException;
import exceptions.EmailAlreadyExistException;
import exceptions.InvalidUserInfoException;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminService extends ClientService {

    public AdminService() throws SQLException {
    }

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    //Login as admin
    @Override
    public boolean login(String email, String password) throws InvalidUserInfoException {
        String correctEmail = "admin@admin.com";
        String correctPassword = "admin";
        boolean isCorrect = false;
        if (email.equals(correctEmail) && password.equals(correctPassword)) {
            isCorrect = true;
            System.out.println("Admin login");
        } else {
            throw new InvalidUserInfoException();
        }
        return isCorrect;
    }

    //Add company as admin
    public void addCompany(Company company) throws SQLException, CompanyAlreadyExistException {
        ArrayList<Company> companies = companiesDAO.getAllCompanies();
        for (Company c : companies) {
            if (company.getEmail().equals(c.getEmail()) || company.getName().equals(c.getName())) {
                throw new CompanyAlreadyExistException();
            }
        }
        companiesDAO.addCompany(company);
        System.out.println("The: " + company.getName() + " company was added");
    }

    //Update company as admin
    public void updateCompany(Company company) throws SQLException, CompanyAlreadyExistException {
        companiesDAO.updateCompany(company);
        System.out.println("The: " + company.getName() + " company was updated");
    }

    //Delete company as admin
    public void deleteCompany(int companyID) throws SQLException {
        couponsDAO.deleteAllCompanyHistoryCoupons(companyID);
        couponsDAO.deleteAllCompanyCoupons(companyID);
        companiesDAO.deleteCompany(companyID);
        System.out.println("The company number: " + companyID + " was deleted");
    }

    //Get all companies as admin
    public ArrayList<Company> getAllCompanies() throws SQLException {
        return companiesDAO.getAllCompanies();
    }

    //Get one company as admin
    public Company getOneCompany(int companyID) throws SQLException {
        return companiesDAO.getOneCompany(companyID);
    }

    //Add customer as admin
    public void addCustomer(Customer customer) throws SQLException, EmailAlreadyExistException {
        if (customersDAO.isCustomerExistAddCustomer(customer.getEmail())) {
            throw new EmailAlreadyExistException();
        } else {
            customersDAO.addCustomer(customer);
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " customer was added");
        }
    }

    //Update customer as admin
    public void updateCustomer(Customer customer) throws SQLException {
        customersDAO.updateCustomer(customer);
        System.out.println("Customer number: " + customer.getId() + " was updated");
    }

    //Delete customer as admin
    public void deleteCustomer(int customerID) throws SQLException {
        couponsDAO.deleteAllCustomerHistoryCoupons(customerID);
        customersDAO.deleteCustomer(customerID);
        System.out.println("Customer number: " + customerID + " was deleted");
    }

    //Get all customers as admin
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        return customersDAO.getAllCustomers();
    }

    //Get one customer as admin
    public Customer getOneCustomer(int customerID) throws SQLException {
        return customersDAO.getOneCustomer(customerID);
    }
}
