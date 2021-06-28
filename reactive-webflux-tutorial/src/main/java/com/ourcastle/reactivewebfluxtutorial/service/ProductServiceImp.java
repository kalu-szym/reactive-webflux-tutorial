package com.ourcastle.reactivewebfluxtutorial.service;

import com.ourcastle.reactivewebfluxtutorial.dao.ProductDaoImp;
import com.ourcastle.reactivewebfluxtutorial.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@Service
public class ProductServiceImp implements ProductService{

    private final ProductDaoImp productDaoImp;

    @Autowired
    public ProductServiceImp(ProductDaoImp productDaoImp) {
        this.productDaoImp = productDaoImp;
    }

    @Override
    public Flux<Product> getAllProducts_SortedByNameDescendingOrder() {
        Comparator<Product> sortProductByNameDescendingOrder = Comparator.comparing(Product::getName, (p1, p2) -> {return p2.compareTo(p1);});
        return productDaoImp.getAllProducts().sort(sortProductByNameDescendingOrder);
    }

}
