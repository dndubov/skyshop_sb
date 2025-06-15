package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
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

        assertNotNull(result); // не null
        assertTrue(result.isEmpty()); // но пустой
    }

    @Test
    void shouldReturnEmptyListWhenNoMatchingObjects() {
        Searchable item1 = new Searchable() {
            @Override
            public String getSearchableText() {
                return "keyboard";
            }
            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }
            @Override
            public String getName() {
                return "Keyboard";
            }
            @Override
            public String getType() {
                return "product";
            }
        };

        Searchable item2 = new Searchable() {
            @Override
            public String getSearchableText() {
                return "monitor";
            }
            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }
            @Override
            public String getName() {
                return "Monitor";
            }
            @Override
            public String getType() {
                return "product";
            }
        };

        Searchable item3 = new Searchable() {
            @Override
            public String getSearchableText() {
                return "mouse";
            }
            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }
            @Override
            public String getName() {
                return "Mouse";
            }
            @Override
            public String getType() {
                return "product";
            }
        };

        when(storageService.getAllSearchables()).thenReturn(List.of(item1, item2, item3));

        var result = searchService.search("usb");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnMatchingObjects() {
        // Создаем объект, в котором содержится слово "usb"
        Searchable item1 = new Searchable() {
            @Override
            public String getSearchableText() {
                return "usb cable";
            }

            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }

            @Override
            public String getName() {
                return "USB Cable";
            }

            @Override
            public String getType() {
                return "product";
            }
        };

        // Создаем объект, который не должен попасть в результат
        Searchable item2 = new Searchable() {
            @Override
            public String getSearchableText() {
                return "keyboard";
            }

            @Override
            public UUID getId() {
                return UUID.randomUUID();
            }

            @Override
            public String getName() {
                return "Keyboard";
            }

            @Override
            public String getType() {
                return "product";
            }
        };

        // Мокаем результат метода getAllSearchables()
        when(storageService.getAllSearchables()).thenReturn(List.of(item1, item2));

        // Выполняем поиск
        Collection<SearchResult> result = searchService.search("usb");

        // Проверяем, что результат не null и содержит ровно один элемент
        assertNotNull(result);
        assertEquals(1, result.size());

        // Преобразуем результат в список для доступа по индексу
        List<SearchResult> resultList = new ArrayList<>(result);

        // Проверяем, что имя найденного объекта содержит "USB"
        assertTrue(resultList.get(0).getName().toLowerCase().contains("usb"));
    }

    @Test
    void shouldReturnMatchingObjectsWhenSeveralMatch() {
        Searchable item1 = createSearchable(UUID.randomUUID(), "USB Hub", "usb hub device", "product");
        Searchable item2 = createSearchable(UUID.randomUUID(), "USB Cable", "high speed usb cable", "product");
        Searchable item3 = createSearchable(UUID.randomUUID(), "HDMI Cable", "high quality video", "product");

        when(storageService.getAllSearchables()).thenReturn(List.of(item1, item2, item3));

        var result = searchService.search("usb");

        assertNotNull(result);
        assertEquals(2, result.size());

        List<String> names = result.stream().map(SearchResult::getName).toList();
        assertTrue(names.contains("USB Hub"));
        assertTrue(names.contains("USB Cable"));
    }

    private Searchable createSearchable(UUID id, String name, String searchableText, String type) {
        return new Searchable() {
            @Override
            public UUID getId() {
                return id;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getSearchableText() {
                return searchableText;
            }

            @Override
            public String getType() {
                return type;
            }
        };
    }

}
