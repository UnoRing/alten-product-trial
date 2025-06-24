package com.james.alten_shop.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDTO {

    private long id;

    private String name;

    private String description;

    private String image;

    private String category;

    private double price;

    private int quantity;

    private String internalReference;

    private int shellId;

    private String inventoryStatus;

    private double rating;
}

