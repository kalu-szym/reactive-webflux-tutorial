package com.ourcastle.reactivewebfluxtutorial.controller;

import com.ourcastle.reactivewebfluxtutorial.dao.ProductDaoImp;
import com.ourcastle.reactivewebfluxtutorial.model.Product;
import com.ourcastle.reactivewebfluxtutorial.service.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductServiceImp productServiceImp;

    private final ProductDaoImp productDaoImp;

    @Autowired
    public ProductController(ProductDaoImp productDaoImp, ProductServiceImp productServiceImp) {
        this.productDaoImp = productDaoImp;
        this.productServiceImp = productServiceImp;
    }

    @GetMapping("/{name}")
    private Mono<Product> getProductByName(@PathVariable String name) {
        return productDaoImp.getProductByName(name);
    }


    //sort by name descending - modify inbetween (service layer) and test it
    @GetMapping
    private Flux<Product> getAllProducts_SortedByNameDescendingOrder() {
        return productServiceImp.getAllProducts_SortedByNameDescendingOrder();
    }

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> saveProduct(@RequestBody Product product) {
        return productDaoImp.saveProduct(product);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable String name) {
        return productDaoImp.deleteProduct(name);
    }
}
