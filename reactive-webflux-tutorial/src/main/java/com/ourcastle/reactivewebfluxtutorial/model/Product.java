package com.ourcastle.reactivewebfluxtutorial.model;

import org.springframework.stereotype.Component;

@Component
public class Product {
    private String name;
    private int numberOfProducts;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, int numberOfProducts) {
        this.name = name;
        this.numberOfProducts = numberOfProducts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
}
