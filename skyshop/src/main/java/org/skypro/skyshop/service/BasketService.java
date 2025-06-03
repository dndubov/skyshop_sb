package org.skypro.skyshop.service;

import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        if (!storageService.getProductById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + id + " not found");
        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        return new UserBasket(
                productBasket.getProducts().entrySet().stream()
                        .map(entry -> {
                            Product product = storageService.getProductById(entry.getKey())
                                    .orElseThrow(() -> new IllegalStateException("Product in basket not found in storage"));
                            return new BasketItem(product, entry.getValue());
                        })
                        .collect(Collectors.toList())
        );
    }
}
