package dao.Companies;

import beans.Category;
import beans.Company;
import beans.Coupon;
import db.ConnectionPool;
import exceptions.CompanyAlreadyExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompaniesDBDAO implements CompaniesDAO {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public CompaniesDBDAO() throws SQLException {
    }

    //Check if company exist
    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from" +
                    " companies where email = ? and password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Add a new company
    @Override
    public void addCompany(Company company) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into" +
                    " companies (name, email, password) values (?, ?, ?)");
            statement.setString(1, company.getName());
            statement.setString(2, company.getEmail());
            statement.setString(3, company.getPassword());
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Set a company
    @Override
    public void updateCompany(Company company) throws CompanyAlreadyExistException, SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE companies" +
                    " SET email = ?, password = ? WHERE id = ?;");
            statement.setString(1, company.getEmail());
            statement.setString(2, company.getPassword());
            statement.setInt(3, company.getId());
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Delete a company
    @Override
    public void deleteCompany(int companyID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE from" +
                    " companies WHERE id = ?");
            statement.setInt(1, companyID);
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Get list of details of all companies
    @Override
    public ArrayList<Company> getAllCompanies() throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " +
                    "companies");
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Company> companiesList = new ArrayList<>();
            while (resultSet.next()) {
                companiesList.add(new Company(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        null));
            }

            return companiesList;
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Get all company coupons by companyID
    @Override
    public ArrayList<Coupon> getAllCouponsByID(int companyID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM" +
                    " coupons WHERE company_id = ?;");
            statement.setInt(1, companyID);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Coupon> allCoupons = new ArrayList<>();
            while (resultSet.next()) {
                allCoupons.add(new Coupon(resultSet.getInt(1),
                        resultSet.getInt(2),
                        Category.values()[resultSet.getInt(3) - 1],
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getString(10)));
            }
            return allCoupons;

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Get details about one company
    @Override
    public Company getOneCompany(int companyID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM" +
                    " companies WHERE id = ?");
            statement.setInt(1, companyID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String password = resultSet.getString(4);

            return new Company(id,name,email,password,getAllCouponsByID(id));
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }
}
