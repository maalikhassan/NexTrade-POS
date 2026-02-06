package edu.icet.service.item;

import edu.icet.db.DBConnection;
import edu.icet.model.dto.ItemDto;
import edu.icet.repository.item.ItemRepository;
import edu.icet.repository.item.ItemRepositoryImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemServiceImpl implements ItemFormService {

    private final ItemRepository itemRepository = new ItemRepositoryImpl();

    @Override
    public ObservableList<ItemDto> getAllItems() {
        ObservableList<ItemDto>itemDtoObservableList = FXCollections.observableArrayList();

        try{

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
        //ItemRepository itemRepository = new ItemRepositoryImpl();
        try {
            itemRepository.addItem(code, description, packSize, unitPrice, qtyOnHand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(String code, String description, String packSize, double unitPrice, int qtyOnHand) {
        //ItemRepository itemRepository = new ItemRepositoryImpl();
        try {
            itemRepository.updateItem(code, description, packSize, unitPrice, qtyOnHand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItem(String code) {
        //ItemRepository itemRepository = new ItemRepositoryImpl();
        try {
            itemRepository.deleteItem(code);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
