package com.ourcastle.reactivewebfluxtutorial.model;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return numberOfProducts == product.numberOfProducts && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, numberOfProducts);
    }

    Comparator<Product> sortProductByNameDescendingOrder = Comparator.comparing(Product::getName, (p1, p2) -> {return p2.compareTo(p1);});
}
