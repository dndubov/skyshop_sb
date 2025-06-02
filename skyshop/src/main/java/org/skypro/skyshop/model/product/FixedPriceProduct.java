package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixedPriceProduct extends Product {
    private final double fixedPrice;

    public FixedPriceProduct(UUID id, String name, String description, double price, double fixedPrice) {
        super(id, name, description, price);
        this.fixedPrice = fixedPrice;
    }

    public double getFixedPrice() {
        return fixedPrice;
    }
}
