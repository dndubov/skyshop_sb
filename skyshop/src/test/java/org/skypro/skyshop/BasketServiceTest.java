package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    ProductBasket productBasket;

    @Mock
    StorageService storageService;

    @InjectMocks
    BasketService basketService;

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(id));
    }

    @Test
    void shouldCallAddProductWhenProductExists() {
        UUID id = UUID.randomUUID();
        Product mockProduct = mock(Product.class);
        when(storageService.getProductById(id)).thenReturn(Optional.of(mockProduct));

        basketService.addProductToBasket(id);

        verify(productBasket).addProduct(id);
    }

    @Test
    void shouldReturnEmptyUserBasketWhenProductBasketIsEmpty() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        var result = basketService.getUserBasket();

        assertNotNull(result);
        assertTrue(result.getItems().isEmpty());
    }

    @Test
    void shouldReturnUserBasketWithItems() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);

        Map<UUID, Integer> basketMap = new HashMap<>();
        basketMap.put(id1, 2);
        basketMap.put(id2, 1);

        when(productBasket.getProducts()).thenReturn(basketMap);
        when(storageService.getProductById(id1)).thenReturn(Optional.of(product1));
        when(storageService.getProductById(id2)).thenReturn(Optional.of(product2));

        var result = basketService.getUserBasket();

        assertEquals(2, result.getItems().size());
    }

    @Test
    void shouldThrowIllegalStateIfProductInBasketNotFoundInStorage() {
        UUID id = UUID.randomUUID();
        Map<UUID, Integer> basketMap = Map.of(id, 1);
        when(productBasket.getProducts()).thenReturn(basketMap);
        when(storageService.getProductById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> basketService.getUserBasket());
    }

    @Test
    void shouldCallStorageServiceOncePerProduct() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        Map<UUID, Integer> basketMap = Map.of(id, 1);

        when(productBasket.getProducts()).thenReturn(basketMap);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));

        basketService.getUserBasket();

        verify(storageService, times(1)).getProductById(id);
    }

}
