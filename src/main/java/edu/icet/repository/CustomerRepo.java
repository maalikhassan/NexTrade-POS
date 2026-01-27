package edu.icet.repository;

import edu.icet.controller.customer.CustomerFormService;
import edu.icet.model.dto.CustomerDto;
import javafx.collections.ObservableList;

import java.util.Date;

public class CustomerRepo implements CustomerFormService {
    @Override
    public ObservableList<CustomerDto> getAllCustomers() {
        return null;
    }

    @Override
    public void addCustomer(String id, String title, String name, Date dob, Double salary, String address, String city, String province, String postalCode) {

    }

    @Override
    public void updateCustomer(String id, String title, String name, Date dob, Double salary, String address, String city, String province, String postalCode) {

    }

    @Override
    public void deleteCustomer(String id) {

    }
}
