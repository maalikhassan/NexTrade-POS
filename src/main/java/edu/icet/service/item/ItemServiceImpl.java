package edu.icet.service.item;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.ItemDto;
import edu.icet.repository.item.ItemRepository;
import edu.icet.repository.item.ItemRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemServiceImpl implements ItemFormService {
    @Override
    public ObservableList<ItemDto> getAllItems() {
        ObservableList<ItemDto>itemDtoObservableList = FXCollections.observableArrayList();

        try{
            ItemRepository itemRepository = new ItemRepositoryImpl();
            ResultSet resultSet = itemRepository.getAllItems();

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
        ItemRepository itemRepository = new ItemRepositoryImpl();
        itemRepository.addItem(code, description, packSize, unitPrice, qtyOnHand);
    }

    @Override
    public void updateItem(String code, String description, String packSize, double unitPrice, int qtyOnHand) {
        ItemRepository itemRepository = new ItemRepositoryImpl();
        itemRepository.updateItem(code, description, packSize, unitPrice, qtyOnHand);
    }

    @Override
    public void deleteItem(String code) {
        ItemRepository itemRepository = new ItemRepositoryImpl();
        itemRepository.deleteItem(code);
    }
}
