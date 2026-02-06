package edu.icet.service.order;

import edu.icet.model.dto.ItemDto;

public interface OrderFormService {

    public String getCustomerNameById(String id);

    ItemDto searchItem(String itemCode);
}
