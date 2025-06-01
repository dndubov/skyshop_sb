package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.*;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();

    public StorageService() {
        initializeTestData();
    }

    private void initializeTestData() {
        // Добавление тестовых продуктов
        products.put(UUID.randomUUID(), new SimpleProduct(UUID.randomUUID(), "Product 1", "Description 1", 100));
        products.put(UUID.randomUUID(), new DiscountedProduct(UUID.randomUUID(), "Product 2", "Description 2", 200, 0.1));

        // Добавление тестовых статей
        articles.put(UUID.randomUUID(), new Article(UUID.randomUUID(), "Article 1", "Content 1"));
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public Collection<Article> getArticles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchables() {
        Collection<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }
}