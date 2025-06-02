package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@SessionScope
public class ProductBasket {

    private final Map<UUID, Integer> products = new HashMap<>();

    /**
     * Добавляет товар в корзину по его id.
     * Если товар уже есть — увеличивает количество.
     */
    public void addProduct(UUID id) {
        products.put(id, products.getOrDefault(id, 0) + 1);
    }

    /**
     * Возвращает все товары из корзины.
     * Обёрнутые в unmodifiableMap для безопасности.
     */
    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
}
