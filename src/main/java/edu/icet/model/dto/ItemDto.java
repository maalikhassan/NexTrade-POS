package edu.icet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDto {
    private String code;
    private String description;
    private String packSize;
    private double unitPrice;
    private int qtyOnHand;
}
