package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;
    private final String title;
    private final String text;

    public Article(UUID id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    @Override
    public String getSearchableText() {
        return title + " " + text;
    }

    @Override
    public UUID getId() {
        return id;
    }

    // Геттеры
    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}