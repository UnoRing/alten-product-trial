package com.james.alten_shop.entities;

import com.james.alten_shop.enums.InventoryStatusEnum;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Entity
@Data
@EqualsAndHashCode(callSuper = false) // because we have mapped superclass @EntityBase
@Accessors(chain = true)
@Table(name = "products")
public class Product extends EntityBase {

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
