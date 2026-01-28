package edu.icet.service.customer;

import edu.icet.model.dto.CustomerDto;
import edu.icet.repository.customer.CustomerRepository;
import edu.icet.repository.customer.CustomerRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerServiceImpl implements CustomerFormService{//should only communicate with the DB
    @Override
    public ObservableList<CustomerDto> getAllCustomers() {

        ObservableList<CustomerDto>customerDtoObservableList = FXCollections.observableArrayList();

        try{
            CustomerRepository customerRepository = new CustomerRepositoryImpl();
            ResultSet resultSet = customerRepository.getAllCustomers() ;

            while (resultSet.next()){
                CustomerDto customerDto = new CustomerDto(
                        resultSet.getString("CustId"),
                        resultSet.getString("CustTitle"),
                        resultSet.getString("CustName"),
                        resultSet.getDate("DOB"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("CustAddress"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );

                customerDtoObservableList.add(customerDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerDtoObservableList;
    }

    @Override
    public void addCustomer(String id, String title, String name, Date dob, Double salary, String address,
                            String city, String province, String postalCode) {
        try {
            CustomerRepository customerRepository = new CustomerRepositoryImpl();
            customerRepository.addCustomer(id, title, name, dob, salary, address, city, province, postalCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(String id, String title, String name, Date dob, Double salary, String address, String city, String province, String postalCode) {
        try {
            CustomerRepository customerRepository = new CustomerRepositoryImpl();
            customerRepository.updateCustomer(id, title, name, dob, salary, address, city, province, postalCode);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String id) {
        try {
            CustomerRepository customerRepository = new CustomerRepositoryImpl();
            customerRepository.deleteCustomer(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
