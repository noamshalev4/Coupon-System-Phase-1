package dao.Customers;

import beans.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDAO {
    public boolean isCustomerExistLogin(String email, String password) throws SQLException;
    public boolean isCustomerExistAddCustomer(String email) throws SQLException;
    public void addCustomer(Customer customer) throws SQLException;
    public void updateCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int customerID) throws SQLException;
    public ArrayList<Customer> getAllCustomers() throws SQLException;
    public Customer getCustomerFromResultSet(ResultSet resultSet) throws SQLException;
    public Customer getOneCustomer(int customerID) throws SQLException;
}
