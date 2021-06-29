package com.ourcastle.reactivewebfluxtutorial.dao;

import com.ourcastle.reactivewebfluxtutorial.model.Product;
import com.ourcastle.reactivewebfluxtutorial.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class ProductDaoImp implements ProductDao {

    @Autowired
    private final ProductRepository productRepository;

    public ProductDaoImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Mono<Void> saveProduct(Product product) {
        productRepository.save(product);
        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteProduct(String name) {
        productRepository.deleteByName(name);
        return Mono.empty();
    }
}
