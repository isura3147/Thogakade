package controller.customerController;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.*;

public class CustomerController implements CustomerService {
    @Override
    public void addCustomer(String id, String title, String name, String DOB, double salary, String address, String city, String province, String postalCode) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "isura1234");
            String SQL = "INSERT INTO customers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, title);
            preparedStatement.setObject(3, name);
            preparedStatement.setObject(4, DOB);
            preparedStatement.setObject(5, salary);
            preparedStatement.setObject(6, address);
            preparedStatement.setObject(7, city);
            preparedStatement.setObject(8, province);
            preparedStatement.setObject(9, postalCode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "DELETE FROM customers WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String id, String title, String name, String DOB, double salary, String address, String city, String province, String postalCode) {

    }

    @Override
    public ResultSet viewCustomer(String id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQL = "SELECT * FROM customers WHERE customer_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setObject(1, id);
        return preparedStatement.executeQuery();
    }

    @Override
    public ObservableList<Customer> loadDetails(ObservableList<Customer> customerInfos) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM customers;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customerInfo = new Customer(
                        resultSet.getString("customer_id"),
                        resultSet.getString("title"),
                        resultSet.getString("name"),
                        resultSet.getDate("date_of_birth").toLocalDate(),
                        resultSet.getDouble("salary"),
                        resultSet.getString("address"),
                        resultSet.getString("city"),
                        resultSet.getString("province"),
                        resultSet.getString("postal_code")
                );
                customerInfos.add(customerInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerInfos;
    }


}
