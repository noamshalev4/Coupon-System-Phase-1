package dao.Customers;

import beans.Customer;
import db.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomersDBDAO implements CustomersDAO {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public CustomersDBDAO() throws SQLException {
    }

    //Check if customer exist
    @Override
    public boolean isCustomerExistLogin(String email, String password) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM customers WHERE email = ? AND password = ?;");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Check if customer exit while using the add customer function
    @Override
    public boolean isCustomerExistAddCustomer(String email) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM customers WHERE email = ? AND password;");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Add a new customer
    @Override
    public void addCustomer(Customer customer) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO customers (first_name, last_name, " +
                            "email, password) VALUES (?, ?, ?, ?)");
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPassword());
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Set a customer
    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE customers SET first_name = ?, last_name = ?," +
                            " email = ?, password = ? WHERE id = ?");
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPassword());
            statement.setInt(5, customer.getId());
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Delete a customer
    @Override
    public void deleteCustomer(int customerID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("DELETE FROM customers WHERE id = ?");
            statement.setInt(1, customerID);
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Get list of details of all customers
    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM customers");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Customer> customersList = new ArrayList<>();
            while (resultSet.next()) {
                customersList.add(getCustomerFromResultSet(resultSet));
            }
            return customersList;
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Auxiliary function to getAllCustomer function
    @Override
    public Customer getCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            int id = resultSet.getInt(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            String email = resultSet.getString(4);
            String password = resultSet.getString(5);

            return new Customer(id, firstName, lastName, email, password, null);
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Get details about one customer
    @Override
    public Customer getOneCustomer(int customerID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM customers WHERE id = ?");
            statement.setInt(1, customerID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(5);
                return new Customer(id, firstName, lastName, email, password, null);
            }else {
                throw new SQLException("Customer with ID " + customerID + " not found");
            }

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }
}
