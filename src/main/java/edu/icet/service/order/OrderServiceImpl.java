package edu.icet.service.order;

import edu.icet.model.dto.ItemDto;
import edu.icet.repository.customer.CustomerRepository;
import edu.icet.repository.customer.CustomerRepositoryImpl;
import edu.icet.repository.item.ItemRepository;
import edu.icet.repository.item.ItemRepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderFormService{

    private final CustomerRepository customerRepository = new CustomerRepositoryImpl();
    private final ItemRepository itemRepository = new ItemRepositoryImpl();

    @Override
    public String getCustomerNameById(String id) {
        try {
            ResultSet resultSet = customerRepository.getCustomerById(id);
            if (resultSet.next()){
                return resultSet.getString("CustName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ItemDto searchItem(String itemCode) {
        try {;
            ResultSet resultSet = itemRepository.getItembyCode(itemCode);

            if (resultSet.next()) {
                return new ItemDto(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
            } else {
                return null; // Item not found
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
