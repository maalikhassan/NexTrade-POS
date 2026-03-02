package edu.icet.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderId;
    private LocalDate orderDate;
    private String customerId;
    private List<OrderDetailDto> orderDetails; // This holds my cart items, Adding this makes the Transaction logic easier to manage!
}
