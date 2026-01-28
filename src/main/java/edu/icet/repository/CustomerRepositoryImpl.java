package edu.icet.repository;

import edu.icet.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerRepositoryImpl implements CustomerRepository{ //should only communicate with the DB

    public ResultSet getAllCustomers() throws SQLException {

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT*FROM customer");

            ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    @Override
    public void addCustomer(String id, String title, String name, Date dob, Double salary,
                            String address, String city, String province, String postalCode)
            throws SQLException {

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer " +
                    "VALUES (?,?,?,?,?,?,?,?,?)");

            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, title);
            preparedStatement.setObject(3, name);
            preparedStatement.setObject(4, dob);
            preparedStatement.setObject(5, salary);
            preparedStatement.setObject(6, address);
            preparedStatement.setObject(7, city);
            preparedStatement.setObject(8, province);
            preparedStatement.setObject(9, postalCode);

            preparedStatement.execute();
    }

    @Override
    public void updateCustomer(String id, String title, String name, Date dob, Double salary,
                               String address, String city, String province, String postalCode)
            throws SQLException {

            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer " +
                    "SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? " +
                    "WHERE CustId=?");

            preparedStatement.setObject(1, title);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, dob);
            preparedStatement.setObject(4, salary);
            preparedStatement.setObject(5, address);
            preparedStatement.setObject(6, city);
            preparedStatement.setObject(7, province);
            preparedStatement.setObject(8, postalCode);
            preparedStatement.setObject(9, id);

            preparedStatement.executeUpdate();
    }

    @Override
    public void deleteCustomer(String id) throws SQLException {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE CustId=?");

            preparedStatement.setObject(1, id);

            preparedStatement.execute();
    }
}
