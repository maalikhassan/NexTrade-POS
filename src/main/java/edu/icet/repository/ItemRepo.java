package edu.icet.repository;

import edu.icet.controller.item.ItemFormService;
import edu.icet.db.DBConnection;
import edu.icet.model.dto.CustomerDto;
import edu.icet.model.dto.ItemDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepo implements ItemFormService {
    @Override
    public ObservableList<ItemDto> getAllItems() {
        ObservableList<ItemDto>itemDtoObservableList = FXCollections.observableArrayList();

        try{
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT*FROM item");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ItemDto  itemDto = new ItemDto(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );

                itemDtoObservableList.add(itemDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDtoObservableList;
    }

    @Override
    public void addItem(String code, String description, String packSize, double unitPrice, int qtyOnHand) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO item VALUES (?,?,?,?,?)");
            preparedStatement.setObject(1, code);
            preparedStatement.setObject(2, description);
            preparedStatement.setObject(3, packSize);
            preparedStatement.setObject(4, unitPrice);
            preparedStatement.setObject(5, qtyOnHand);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(String code, String description, String packSize, double unitPrice, int qtyOnHand) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE item " +
                    "SET Description=?, PackSize=?, UnitPrice=?, QtyOnHand=? WHERE ItemCode=?");
            preparedStatement.setObject(1, description);
            preparedStatement.setObject(2, packSize);
            preparedStatement.setObject(3, unitPrice);
            preparedStatement.setObject(4, qtyOnHand);
            preparedStatement.setObject(5, code);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteItem(String code) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM item WHERE ItemCode= ?");
            preparedStatement.setObject(1,code);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
