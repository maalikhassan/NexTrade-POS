package edu.icet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDto {
    private String itemCode;
    private  String description;
    private int qty;
    private  Double unitPrice;
    private  Double discount;
    private Double total;
}
