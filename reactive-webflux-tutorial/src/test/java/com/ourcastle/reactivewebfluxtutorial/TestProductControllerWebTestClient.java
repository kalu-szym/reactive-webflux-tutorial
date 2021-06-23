package com.ourcastle.reactivewebfluxtutorial;

import com.ourcastle.reactivewebfluxtutorial.config.WebConfig;
import com.ourcastle.reactivewebfluxtutorial.dao.ProductDaoImp;
import com.ourcastle.reactivewebfluxtutorial.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringJUnitConfig(WebConfig.class)
public class TestProductControllerWebTestClient {

    @MockBean
    ProductDaoImp productDaoImp;

    WebTestClient webClient;

    @BeforeEach
    void setUp(ApplicationContext context) {
        webClient = WebTestClient.bindToApplicationContext(context).build();
    }

    @Test
    public void TestGetAllProducts() {
        Product product1 = new Product("product1", 1);
        Product product2 = new Product("product2", 2);
        Product product3 = new Product("product3", 3);

        Mockito.when(productDaoImp.getAllProducts()).thenReturn(Flux.just(product1, product2, product3));

        webClient.get()
                .uri("/products")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Product.class)
                .hasSize(3)
                .contains(product1, product2, product3);

        Mockito.verify(productDaoImp, Mockito.times(1)).getAllProducts();
    }

    @Test
    public void TestGetProductByName() {
        Product product1 = new Product("product1", 1);

        Mockito.when(productDaoImp.getProductByName("product1")).thenReturn(Mono.just(product1));

        webClient.get()
                .uri("/products/{name}", "product1")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Product.class)
                .hasSize(1)
                .contains(product1);

        Mockito.verify(productDaoImp, Mockito.times(1)).getProductByName("product1");
    }

    @Test
    public void TestPostProduct() {
        Product product4 = new Product("product4", 4);


        webClient.post()
                .uri("/products/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product4)
                .exchange()
                .expectStatus()
                .isCreated();

        Mockito.verify(productDaoImp, Mockito.times(1)).saveProduct(product4);
    }

    @Test
    public void TestDeleteProduct() {
        webClient.delete()
                .uri("/products/{name}", "product1")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        Mockito.verify(productDaoImp, Mockito.times(1)).deleteProduct("product1");
    }
}

