package edu.icet.service;

import edu.icet.model.dto.ItemDto;
import javafx.collections.ObservableList;

public class ItemServiceImpl implements ItemFormService{
    @Override
    public ObservableList<ItemDto> getAllItems() {
        return null;
    }

    @Override
    public void addItem(String code, String description, String packSize, double unitPrice, int qtyOnHand) {

    }

    @Override
    public void updateItem(String code, String description, String packSize, double unitPrice, int qtyOnHand) {

    }

    @Override
    public void deleteItem(String code) {

    }
}
