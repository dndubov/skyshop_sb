package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    StorageService storageService;

    @InjectMocks
    SearchService searchService;

    @Test
    void shouldReturnEmptyListWhenNoObjectsInStorage() {
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        var result = searchService.search("usb");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenNoMatchingObjects() {
        SimpleProduct product1 = new SimpleProduct(UUID.randomUUID(), "Keyboard", "Mechanical keyboard", 3500.0);
        SimpleProduct product2 = new SimpleProduct(UUID.randomUUID(), "Monitor", "24-inch LED monitor", 8000.0);
        Article article1 = new Article(UUID.randomUUID(), "Обзор мышки", "Подробный обзор беспроводной мышки");

        when(storageService.getAllSearchables()).thenReturn(List.of(product1, product2, article1));

        var result = searchService.search("usb");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnMatchingObjects() {
        SimpleProduct product1 = new SimpleProduct(UUID.randomUUID(), "USB Cable", "High-speed USB cable", 500.0);
        SimpleProduct product2 = new SimpleProduct(UUID.randomUUID(), "Keyboard", "Mechanical keyboard", 3500.0);

        when(storageService.getAllSearchables()).thenReturn(List.of(product1, product2));

        Collection<SearchResult> result = searchService.search("usb");

        assertNotNull(result);
        assertEquals(1, result.size());

        SearchResult firstResult = result.iterator().next();
        assertTrue(firstResult.getName().toLowerCase().contains("usb"));
    }

    @Test
    void shouldReturnMatchingObjectsWhenSeveralMatch() {
        SimpleProduct product1 = new SimpleProduct(UUID.randomUUID(), "USB Hub", "USB 3.0 hub device", 1200.0);
        SimpleProduct product2 = new SimpleProduct(UUID.randomUUID(), "USB Cable", "High speed USB cable", 500.0);
        SimpleProduct product3 = new SimpleProduct(UUID.randomUUID(), "HDMI Cable", "High quality video cable", 700.0);

        when(storageService.getAllSearchables()).thenReturn(List.of(product1, product2, product3));

        var result = searchService.search("usb");

        assertNotNull(result);
        assertEquals(2, result.size());

        List<String> names = result.stream().map(SearchResult::getName).toList();
        assertTrue(names.contains("USB Hub"));
        assertTrue(names.contains("USB Cable"));
    }
}
