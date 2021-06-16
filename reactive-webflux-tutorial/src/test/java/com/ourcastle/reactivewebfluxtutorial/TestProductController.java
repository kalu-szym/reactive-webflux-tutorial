package com.ourcastle.reactivewebfluxtutorial;

import com.ourcastle.reactivewebfluxtutorial.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
public class TestProductController {

    private static WebClient webClient;

    @BeforeAll
    private static void setWebClient() {
        webClient = WebClient.create("http://localhost:8080");
    }

    @Test
    public void TestGetAllEndpoint() {
        Flux<Product> allProducts = webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class);
        allProducts.subscribe(System.out::println);
    }

    @Test
    public void TestGetProductByName() {
        Mono<Product> productMono = webClient.get()
                .uri("/products/{name}", "product1")
                .retrieve()
                .bodyToMono(Product.class);

        productMono.subscribe(System.out::println);
    }

    @Test
    public void TestPostProduct() {
        Product product4 = new Product("product4", 4);

        Mono<Void> result = webClient.post()
                .uri("/products/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product4)
                .retrieve()
                .bodyToMono(Void.class);
        result.subscribe(System.out::println);
    }

    @Test
    public void TestDeleteProduct() {
        Mono<Void> result = webClient.delete()
                .uri("/products/{name}", "product1")
                .retrieve()
                .bodyToMono(Void.class);
        result.subscribe(System.out::println);
    }
}
