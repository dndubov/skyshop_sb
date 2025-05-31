package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ShopController {

    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    // Возвращаем JSON список продуктов
    @GetMapping("/products")
    public List<? extends Searchable> getProducts() {
        return new ArrayList<>(storageService.getProducts());
    }

    // Новый метод: возвращаем HTML таблицу продуктов
    @GetMapping(value = "/products/html", produces = "text/html")
    public String getProductsHtml() {
        StringBuilder html = new StringBuilder();
        html.append("<html><head><title>Список продуктов</title></head><body>");
        html.append("<h1>Список продуктов</h1>");
        html.append("<table border='1' cellpadding='8' cellspacing='0'>");
        html.append("<tr><th>Название</th><th>Описание</th><th>Цена</th></tr>");

        for (Product product : storageService.getProducts()) {
            html.append("<tr>")
                    .append("<td>").append(product.getName()).append("</td>")
                    .append("<td>").append(product.getDescription()).append("</td>")
                    .append("<td>").append(product.getPrice()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table></body></html>");
        return html.toString();
    }

    // Возвращаем JSON список статей
    @GetMapping("/articles")
    public List<? extends Searchable> getArticles() {
        return new ArrayList<>(storageService.getArticles());
    }

    // Поиск по продуктам и статьям, возвращаем JSON
    @GetMapping("/search")
    public List<Searchable> search(@RequestParam String text) {
        List<Searchable> combined = new ArrayList<>(storageService.getProducts());
        combined.addAll(storageService.getArticles());
        return searchService.search(combined, text);
    }
}
