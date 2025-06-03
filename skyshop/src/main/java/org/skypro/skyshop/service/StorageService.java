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
        UUID id1 = UUID.randomUUID();
        products.put(id1, new SimpleProduct(id1, "Product 1", "Description 1", 100));

        UUID id2 = UUID.randomUUID();
        products.put(id2, new DiscountedProduct(id2, "Product 2", "Description 2", 200, 0.1));

        UUID articleId = UUID.randomUUID();
        articles.put(articleId, new Article(articleId, "Article 1", "Content 1"));
    }

    private void printProductIds() {
        System.out.println("=== Товары с их ID ===");
        for (UUID id : products.keySet()) {
            System.out.println(id);
        }
        System.out.println("======================");
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
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
