package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Service
public class SearchService {

    /**
     * Поиск среди коллекции Searchable по тексту.
     * Поиск нечувствителен к регистру, ищет подстроку в getSearchableText().
     *
     * @param items коллекция объектов, реализующих Searchable
     * @param text  текст поиска
     * @return список подходящих объектов
     */
    public <T extends Searchable> List<T> search(Collection<T> items, String text) {
        List<T> results = new ArrayList<>();
        String lowerText = text.toLowerCase(Locale.ROOT);

        for (T item : items) {
            if (item.getSearchableText().toLowerCase(Locale.ROOT).contains(lowerText)) {
                results.add(item);
            }
        }
        return results;
    }
}
