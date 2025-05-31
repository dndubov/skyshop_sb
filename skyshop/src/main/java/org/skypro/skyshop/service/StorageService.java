package org.skypro.skyshop.service;

import jakarta.annotation.PostConstruct;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> products = new LinkedHashMap<>();
    private final Map<UUID, Article> articles = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        // Продукты
        addProduct(new SimpleProduct(UUID.randomUUID(), "Наушники", "Беспроводные наушники", 5000));
        addProduct(new DiscountedProduct(UUID.randomUUID(), "Телефон", "Смартфон с хорошей камерой", 30000, 0.1));
        addProduct(new FixedPriceProduct(UUID.randomUUID(), "Планшет", "Планшет для чтения и серфинга", 25000, 1000));

        addProduct(new SimpleProduct(UUID.randomUUID(), "Монитор", "27 дюймов, 4K", 20000));
        addProduct(new DiscountedProduct(UUID.randomUUID(), "Ноутбук", "Мощный игровой ноутбук", 70000, 0.15));
        addProduct(new FixedPriceProduct(UUID.randomUUID(), "Принтер", "МФУ лазерный", 12000, 5000));

        // Статьи
        addArticle(new Article(UUID.randomUUID(), "Выбор смартфона", "Как выбрать лучший смартфон в 2025 году"));
        addArticle(new Article(UUID.randomUUID(), "Обзор ноутбуков", "Топ-5 ноутбуков для работы и игр"));
        addArticle(new Article(UUID.randomUUID(), "Советы по выбору монитора", "Что важно при покупке монитора"));
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public Optional<Product> findProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public void addArticle(Article article) {
        articles.put(article.getId(), article);
    }

    public Collection<Article> getArticles() {
        return articles.values();
    }

    public Optional<Article> findArticleById(UUID id) {
        return Optional.ofNullable(articles.get(id));
    }
}
