package com.ourcastle.reactivewebfluxtutorial.dao;

import com.ourcastle.reactivewebfluxtutorial.model.Product;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class ProductDaoImp implements ProductDao {

    Product product1 = new Product("product1", 1);
    Product product2 = new Product("product2", 2);
    Product product3 = new Product("product3", 3);

    List<Product> productRepo = new ArrayList<Product>(List.of(product2, product1, product3));

    @Override
    public Flux<Product> getAllProducts() {
        return Flux.fromIterable(productRepo);
    }

    @Override
    public Mono<Product> getProductByName(String name) {
        return Mono.just(productRepo.stream().filter(Product -> Product.getName().equals(name)).findAny().get());
    }

    @Override
    public Mono<Void> saveProduct(Product product) {
        productRepo.add(product);
        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteProduct(String name) {
        productRepo.removeIf(product -> product.getName().equals("name"));
        return Mono.empty();
    }
}
