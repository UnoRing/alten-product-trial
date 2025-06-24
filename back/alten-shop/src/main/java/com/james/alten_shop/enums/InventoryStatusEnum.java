package com.james.alten_shop.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InventoryStatusEnum {

    INSTOCK("INSTOCK"),
    LOWSTOCK("LOWSTOCK"),
    OUTOFSTOCK("OUTOFSTOCK");

    private final String value;

    public String getValue() {
        return value;
    }

    public static InventoryStatusEnum fromValue(final String value) {
        InventoryStatusEnum result = null;

        for(InventoryStatusEnum inventoryStatus: InventoryStatusEnum.values()) {
            if (inventoryStatus.getValue().equals(value)) {
                result = inventoryStatus;
                break;
            }
        }

        return result;
    }
}
