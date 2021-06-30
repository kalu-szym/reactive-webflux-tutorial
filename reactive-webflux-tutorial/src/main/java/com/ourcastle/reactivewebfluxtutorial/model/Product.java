package com.ourcastle.reactivewebfluxtutorial.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;

@Component
public class Product {

    //lombok

    @Id
    private int id;

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

    public Product(int id, String name, int numberOfProducts) {
        this.id = id;
        this.name = name;
        this.numberOfProducts = numberOfProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == product.id && numberOfProducts == product.numberOfProducts && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberOfProducts);
    }

    //key extractor
    private static final Comparator<Product> sortProductByNameDescendingOrder = Comparator.comparing(Product::getName).reversed();

}
