package com.james.alten_shop.dtos;

import com.james.alten_shop.enums.InventoryStatusEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private long id;

    private String name;

    private String code;

    private String description;

    private String image;

    private String category;

    private double price;

    private int quantity;

    private String internalReference;

    private int shellId;

    private InventoryStatusEnum inventoryStatus;

    private double rating;

}

