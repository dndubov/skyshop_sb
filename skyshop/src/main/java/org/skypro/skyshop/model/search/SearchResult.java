package org.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    private SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        String name = searchable.getName();
        String contentType = searchable.getClass().getSimpleName().toLowerCase();

        return new SearchResult(
                searchable.getId().toString(),
                name,
                contentType
        );
    }

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
