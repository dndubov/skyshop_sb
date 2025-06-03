package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String getSearchableText();

    UUID getId();

    String getName();

    String getType();
}


