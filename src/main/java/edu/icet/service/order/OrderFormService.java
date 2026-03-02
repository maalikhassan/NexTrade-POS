package edu.icet.service.order;

import edu.icet.model.dto.ItemDto;
import edu.icet.model.dto.OrderDto;

public interface OrderFormService {

    public String getCustomerNameById(String id);

    ItemDto searchItem(String itemCode);

    boolean placeOrder(OrderDto orderDto);
}
