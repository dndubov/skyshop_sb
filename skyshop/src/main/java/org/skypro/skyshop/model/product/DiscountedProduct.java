package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {

    private final double discount;

    public DiscountedProduct(UUID id, String name, String description, double price, double discount) {
        super(id, name, description, price);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getDiscountedPrice() {
        return getPrice() * (1 - discount);
    }

    @Override
    public String getType() {
        return "product";
    }
}
