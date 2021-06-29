package com.ourcastle.reactivewebfluxtutorial.controller;

import com.ourcastle.reactivewebfluxtutorial.dao.ProductDao;
import com.ourcastle.reactivewebfluxtutorial.model.Product;
import com.ourcastle.reactivewebfluxtutorial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @GetMapping("/{name}")
    private Mono<Product> getProductByName(@PathVariable String name) {
        return productDao.getProductByName(name);
    }

    //sort by name descending - modify inbetween (service layer) and test it
    @GetMapping
    private Flux<Product> getAllProducts_SortedByNameDescendingOrder() {
        return productService.getAllProducts_SortedByNameDescendingOrder();
    }

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> saveProduct(@RequestBody Product product) {
        return productDao.saveProduct(product);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable String name) {
        return productDao.deleteProduct(name);
    }
}
