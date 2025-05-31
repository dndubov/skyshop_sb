package org.skypro.skyshop.model.product;

import java.util.UUID;

/**
 * Обычный товар без скидок и фиксированной цены.
 */
public class SimpleProduct extends Product {
private double price;

    public SimpleProduct(UUID id, String name, String description, double price) {
        super(id, name, description, price);
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "SimpleProduct{" +
                super.toString() +  // добавляем часть из Product
                ", price=" + price +
                '}';
    }

}
