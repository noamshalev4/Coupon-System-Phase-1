package dao.Coupons;

import beans.Category;
import beans.Coupon;
import db.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;

public class CouponsDBDAO implements CouponsDAO {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    public CouponsDBDAO() throws SQLException {
    }

    //Add coupon
    @Override
    public void addCoupon(Coupon coupon) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("INSERT INTO coupons (company_id, category_id," +
                            " title, description, start_date, end_date," +
                            " amount, price, image) VALUES (?, ?, ?, ?, ?, ?," +
                            " ?, ?, ?);");
            statement.setInt(1, coupon.getCompanyId());
            statement.setInt(2, (coupon.getCategory().ordinal() - 1));
            statement.setString(3, coupon.getTitle());
            statement.setString(4, coupon.getDescription());
            statement.setDate(5, coupon.getStartDate());
            statement.setDate(6, coupon.getEndDate());
            statement.setInt(7, coupon.getAmount());
            statement.setDouble(8, coupon.getPrice());
            statement.setString(9, coupon.getImage());
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    @Override
    public ArrayList<Category> getAllCategories() throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM categories;");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Category> allCategories = new ArrayList<>();
            while (resultSet.next()) {
                int categoryId = resultSet.getInt(1);
                String categoryName = resultSet.getString(2);
                Category category = Category.valueOf(categoryName.toUpperCase());
                allCategories.add(category);
            }
            return allCategories;

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Set a coupon
    @Override
    public void updateCoupon(Coupon coupon) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE coupons SET category_id = ?, " +
                            "title = ?, description = ?, start_date = ?, " +
                            "end_date = ?, amount = ?, price = ?, image = ? WHERE id = ?");

            statement.setInt(1, coupon.getCategory().ordinal() + 1);
            statement.setString(2, coupon.getTitle());
            statement.setString(3, coupon.getDescription());
            statement.setDate(4, coupon.getStartDate());
            statement.setDate(5, coupon.getEndDate());
            statement.setInt(6, coupon.getAmount());
            statement.setDouble(7, coupon.getPrice());
            statement.setString(8, coupon.getImage());
            statement.setInt(9, coupon.getId());
            int rowsUpdate = statement.executeUpdate();

            if (rowsUpdate > 0) {
                System.out.println("Coupon updated successfully!");
            } else {
                System.out.println("Something went wrong...");
            }
        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Delete coupon
    @Override
    public void deleteCoupon(int couponID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("DELETE FROM coupons WHERE id = ?;");
            statement.setInt(1, couponID);
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Get all coupons
    @Override
    public ArrayList<Coupon> getAllCoupons() throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM coupons;");
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

    //Get all the data of all coupons by company
    @Override
    public ArrayList<Coupon> getAllCouponsByCompany(int companyID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM coupons WHERE company_id = ?;");
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

    //Get all the data of all coupons by company by selected category
    @Override
    public ArrayList<Coupon> getAllCouponsOfCompanyByCategory(int companyID, Category category) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM coupons WHERE company_id = ? AND category_id = ?;");
            statement.setInt(1, companyID);
            statement.setObject(2, category.ordinal() + 1);
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

    //Get all the data of all coupons by company by selected maximum price
    @Override
    public ArrayList<Coupon> getAllCouponsOfCompanyByMaxPrice(int companyID, double maxPrice) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * " +
                            "FROM coupons " +
                            "WHERE company_id = ? " +
                            "AND price <= (SELECT MAX(price) FROM coupons WHERE price = ?);");
            statement.setInt(1, companyID);
            statement.setDouble(2, maxPrice);
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

    //Get all customer coupons
    @Override
    public ArrayList<Coupon> getAllCouponsByCustomer(int customerId) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT coupons.id, coupons.company_id, coupons.category_id, coupons.title, coupons.description," +
                            " coupons.start_date, coupons.end_date, coupons.amount, coupons.price, coupons.image" +
                            " FROM coupons JOIN customers_vs_coupons ON coupons.id = " +
                            "customers_vs_coupons.coupon_id WHERE customers_vs_coupons.customer_id = ?;");
            statement.setInt(1, customerId);
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

    //Get all the data of all coupons by customer by selected category
    @Override
    public ArrayList<Coupon> getAllCouponsOfCustomerByCategory(int customerID, Category category) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT coupons.*" +
                            " FROM coupons" +
                            " JOIN customers_vs_coupons ON coupons.id = customers_vs_coupons.coupon_id" +
                            " WHERE customers_vs_coupons.customer_id = ? AND coupons.category_id = ?;");
            statement.setInt(1, customerID);
            statement.setObject(2, category.ordinal() + 1);
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

    //Get all the data of all coupons by customer by selected maximum price
    @Override
    public ArrayList<Coupon> getAllCouponsOfCustomerByMaxPrice(int customerID, double maxPrice) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT coupons.*" +
                            " FROM coupons" +
                            " JOIN customers_vs_coupons ON coupons.id = customers_vs_coupons.coupon_id" +
                            " WHERE customers_vs_coupons.customer_id = ?" +
                            " AND coupons.price <= ?;");
            statement.setInt(1, customerID);
            statement.setDouble(2, maxPrice);
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

    //Get all data of one coupon
    @Override
    public Coupon getOneCoupon(int couponID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM coupons WHERE id = ?;");
            statement.setInt(1, couponID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            int companyId = resultSet.getInt(2);
            Category categoryId = Category.values()[resultSet.getInt(3) - 1];
            String title = resultSet.getString(4);
            String description = resultSet.getString(5);
            Date startDate = resultSet.getDate(6);
            Date endDate = resultSet.getDate(7);
            int amount = resultSet.getInt(8);
            double price = resultSet.getInt(9);
            String image = resultSet.getString(10);

            return new Coupon(id, companyId, categoryId, title, description, startDate, endDate, amount, price, image);

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Purchase coupon
    @Override
    public void addCouponPurchase(int customerID, int couponID, Date dateOfPurchase) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE coupons SET amount = amount-1 WHERE id = ?;");
            statement.setInt(1, couponID);
            statement.execute();

            PreparedStatement statement1 = connection.prepareStatement("INSERT INTO " +
                    "customers_vs_coupons" +
                    " (customer_id, coupon_id)" +
                    " VALUES (? ,?);");
            statement1.setInt(1, customerID);
            statement1.setInt(2, couponID);
            statement1.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Delete all coupons of one company
    @Override
    public void deleteAllCompanyCoupons(int companyID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("DELETE FROM coupons WHERE company_id = ?;");
            statement.setInt(1, companyID);
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Delete all history of purchase coupons from a company by her customers
    @Override
    public void deleteAllCompanyHistoryCoupons(int companyID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("DELETE customers_vs_coupons " +
                            "FROM customers_vs_coupons " +
                            "JOIN coupons ON customers_vs_coupons.coupon_id = coupons.id " +
                            "WHERE coupons.company_id = ?;");
            statement.setInt(1, companyID);
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Delete all history of purchase coupons from customers
    @Override
    public void deleteAllCustomerHistoryCoupons(int customerID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("DELETE FROM customers_vs_coupons " +
                            "WHERE customer_id = ?;");
            statement.setInt(1, customerID);
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }

    //Delete all customer coupons history by couponId
    @Override
    public void deleteAllCouponHistoryByCouponID(int couponID) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement
                    ("DELETE FROM customers_vs_coupons " +
                            "WHERE coupon_id = ?;");
            statement.setInt(1, couponID);
            statement.execute();

        } finally {
            connectionPool.restoreConnection(connection);
        }
    }
}


