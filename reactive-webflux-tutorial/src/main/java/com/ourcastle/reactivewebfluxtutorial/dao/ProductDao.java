package com.ourcastle.reactivewebfluxtutorial.dao;
import com.ourcastle.reactivewebfluxtutorial.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Flux<Product> getAllProducts();

    Mono<Product> getProductByName(String name);

    void saveProduct(Product product);

    void deleteProduct(String name);

}
