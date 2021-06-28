package com.ourcastle.reactivewebfluxtutorial.service;

import com.ourcastle.reactivewebfluxtutorial.model.Product;
import reactor.core.publisher.Flux;

public interface ProductService {

    Flux<Product> getAllProducts_SortedByNameDescendingOrder();
}
