package edu.icet.repository.customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public interface CustomerRepository {
    ResultSet getAllCustomers() throws SQLException;

    void addCustomer(String id, String title, String name, Date dob, Double salary,
                            String address, String city, String province, String postalCode)
            throws SQLException;

    void updateCustomer(String id, String title, String name, Date dob, Double salary,
                        String address, String city, String province, String postalCode)
            throws SQLException;

    void deleteCustomer(String id) throws SQLException;

    }
