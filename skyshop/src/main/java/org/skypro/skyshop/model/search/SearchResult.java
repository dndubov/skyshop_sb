package org.skypro.skyshop.model.search;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;

import java.util.UUID;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        String name = searchable instanceof Product
                ? ((Product) searchable).getName()
                : ((Article) searchable).getTitle();

        String contentType = searchable instanceof Product
                ? "product"
                : "article";

        return new SearchResult(
                searchable.getId().toString(),
                name,
                contentType
        );
    }

    // Геттеры
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }
}