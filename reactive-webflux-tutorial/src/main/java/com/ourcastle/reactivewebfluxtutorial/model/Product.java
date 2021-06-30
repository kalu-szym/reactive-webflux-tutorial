package com.ourcastle.reactivewebfluxtutorial.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.Comparator;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Component
public class Product {

    //lombok

    @Id
    private int id;

    @NonNull
    private String name;

    @NonNull
    private int numberOfProducts;

    //key extractor
    private static final Comparator<Product> sortProductByNameDescendingOrder = Comparator.comparing(Product::getName).reversed();

}
