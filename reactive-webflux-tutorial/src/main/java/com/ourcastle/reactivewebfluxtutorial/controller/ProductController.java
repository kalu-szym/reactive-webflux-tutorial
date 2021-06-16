package com.ourcastle.reactivewebfluxtutorial.controller;

import com.ourcastle.reactivewebfluxtutorial.dao.ProductDaoImp;
import com.ourcastle.reactivewebfluxtutorial.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductDaoImp productDaoImp;

    @Autowired
    public ProductController(ProductDaoImp productDaoImp) {
        this.productDaoImp = productDaoImp;
    }

    @GetMapping("/{name}")
    private Mono<Product> getProductByName(@PathVariable String name) {
        return productDaoImp.getProductByName(name);
    }

    @GetMapping
    private Flux<Product> getAllEmployees() {
        return productDaoImp.getAllProducts();
    }

    @PostMapping(path = "/save", consumes = "application/json")
    public void saveProduct(@RequestBody Product product){
        productDaoImp.saveProduct(product);
    }

    @DeleteMapping("/{name}")
        public void deleteProduct (@RequestBody String name){
            productDaoImp.deleteProduct(name);
        }
}
