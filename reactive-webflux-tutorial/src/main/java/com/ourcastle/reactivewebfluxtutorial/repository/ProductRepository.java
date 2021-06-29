package com.ourcastle.reactivewebfluxtutorial.repository;

import com.ourcastle.reactivewebfluxtutorial.model.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

    @Query("select id, name, product_number from products p where p.name = :name")
    Mono<Product> findByName(String name);

    @Query("delete from products p where p.name = :name")
    Mono<Void> deleteByName(String name);
}
