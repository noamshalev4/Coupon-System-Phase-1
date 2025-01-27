package services;

import exceptions.InvalidUserInfoException;

import java.sql.SQLException;

public class LoginManager {
    private static LoginManager instance;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public ClientService login(String email, String password, ClientType clientType) throws SQLException, InvalidUserInfoException {
        switch (clientType) {
            case ADMINISTRATOR -> {
                AdminService admin = new AdminService();
                if (admin.login(email, password)) {
                    return admin;
                }
            }
            case COMPANY -> {
                CompanyService company = new CompanyService();
                if (company.login(email, password)) {
                    return company;
                }
            }
            case CUSTOMER -> {
                CustomerService customer = new CustomerService();
                if (customer.login(email, password)) {
                    return customer;
                }
            }
        }
        return null;
    }
}
