package edu.icet.repository.order;

import edu.icet.model.dto.OrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderRepositoryImpl implements OrderRepository{

    @Override
    public boolean save(OrderDto orderDto, Connection connection) throws SQLException {
        return false;
    }
}
