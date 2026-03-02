package edu.icet.repository.order;

import edu.icet.model.dto.OrderDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderRepository {
    boolean save(OrderDto orderDto, Connection connection) throws SQLException;
}
