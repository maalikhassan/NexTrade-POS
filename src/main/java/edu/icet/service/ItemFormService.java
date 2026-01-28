package edu.icet.service;

import edu.icet.model.dto.ItemDto;
import javafx.collections.ObservableList;

public interface ItemFormService {

    ObservableList<ItemDto> getAllItems();

    void addItem(
            String code, String description, String packSize, double unitPrice , int qtyOnHand
    );

    void updateItem(
            String code, String description, String packSize, double unitPrice , int qtyOnHand
    );

    void deleteItem(String code);

}
