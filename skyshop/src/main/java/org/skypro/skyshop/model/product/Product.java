package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private final String name;
    private final String description;
    private final double price;

    public Product(UUID id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String getSearchableText() {
        return name + " " + description;
    }

    @Override
    public UUID getId() {
        return id;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}