package edu.icet.repository.item;

import edu.icet.model.dto.ItemDto;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ItemRepository {
    ResultSet getAllItems() throws SQLException;

    void addItem(String code, String description, String packSize, double unitPrice, int qtyOnHand)
            throws SQLException;

    void updateItem(String code, String description, String packSize, double unitPrice, int qtyOnHand)
            throws SQLException;

    void deleteItem(String code) throws SQLException;

    public ResultSet getItembyCode(String code) throws SQLException;
}
