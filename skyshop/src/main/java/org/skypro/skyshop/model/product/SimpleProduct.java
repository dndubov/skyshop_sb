package org.skypro.skyshop.model.product;

import java.util.UUID;

/**
 * Обычный товар без скидок и фиксированной цены.
 */
public class SimpleProduct extends Product {

    public SimpleProduct(UUID id, String name, String description, double price) {
        super(id, name, description, price);
    }

    // getPrice() используем из Product, переопределять не нужно

    @Override
    public String toString() {
        return "SimpleProduct{" + super.toString() + "}";
    }

    @Override
    public String getType() {return "product";}
}
