package com.ourcastle.reactivewebfluxtutorial;

import com.ourcastle.reactivewebfluxtutorial.controller.ProductController;
import com.ourcastle.reactivewebfluxtutorial.dao.ProductDaoImp;
import com.ourcastle.reactivewebfluxtutorial.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductController.class)
@Import(ProductDaoImp.class)
public class TestProductController {

    @MockBean
    ProductDaoImp productDaoImp;

    @Autowired
    private static WebClient webClient;

    @BeforeAll
    private static void setWebClient() {
        webClient = WebClient.create("http://localhost:8080");
    }

    @Test
    public void TestGetAllProducts() {
        Product product1 = new Product("product1", 1);
        Product product2 = new Product("product2", 2);
        Product product3 = new Product("product3", 3);

        Mockito.when(productDaoImp.getAllProducts()).thenReturn(Flux.just(product1, product2, product3));

       Flux<Product> allProducts = webClient.get()
               .uri("/products")
               .retrieve()
               .onStatus(HttpStatus::isError, response -> Mono.error(new Exception("Exception")))
               .bodyToFlux(Product.class);

    }

    @Test

    public void TestGetProductByName() {
        Product product1 = new Product("product1", 1);

        Mockito.when(productDaoImp.getProductByName("product1")).thenReturn(Mono.just(product1));

        webClient.get()
                .uri("/products/product1")
            .retrieve()
                .onStatus(HttpStatus::isError, response -> Mono.error(new Exception("Exception")))
                .bodyToMono(Product.class);

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
