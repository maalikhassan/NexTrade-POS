package edu.icet.controller.customer;

import edu.icet.model.dto.CustomerDto;
import javafx.collections.ObservableList;

import java.util.Date;

public interface CustomerFormService {
    ObservableList<CustomerDto> getAllCustomers();

    void addCustomer(
            String id,String title,String name,
            Date dob,Double salary,
            String address,String city,
            String province, String postalCode
    );

    void updateCustomer(
            String id,String title,String name,
            Date dob,Double salary,
            String address,String city,
            String province, String postalCode
    );

    void deleteCustomer(String id);

}
