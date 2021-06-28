package com.ourcastle.reactivewebfluxtutorial;

import com.ourcastle.reactivewebfluxtutorial.controller.ProductController;
import com.ourcastle.reactivewebfluxtutorial.dao.ProductDaoImp;
import com.ourcastle.reactivewebfluxtutorial.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ProductController.class)
@Import(ProductDaoImp.class)
public class TestProductControllerWebTestClient {

    @MockBean
    ProductDaoImp productDaoImp;

    @Autowired
    WebTestClient webClient;


    @Test
    public void TestGetAllProducts() {
        Comparator<Product> sortProductByNameDescendingOrder = Comparator.comparing(Product::getName, (p1,p2) -> {return p2.compareTo(p1);});

        Product product1 = new Product("product1", 1);
        Product product2 = new Product("product2", 2);
        Product product3 = new Product("product3", 3);

        List<Product> productRepo = new ArrayList<Product>(List.of(product1, product2, product3));

        Mockito.when(productDaoImp.getAllProducts()).thenReturn(Flux.just(product3, product2, product1));

        webClient.get()
                .uri("/products")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(Product.class)
                .isEqualTo(productRepo.stream().sorted(sortProductByNameDescendingOrder).collect(Collectors.toList()));
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
                .expectBody(Product.class)
                .isEqualTo(product1);

        Mockito.verify(productDaoImp, Mockito.times(1)).getProductByName("product1");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void TestPostProduct() {
        Product product4 = new Product("product4", 4);


        webClient.mutateWith(csrf())
                .post()
                .uri("/products/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product4)
                .exchange()
                .expectStatus()
                .isCreated();

        Mockito.verify(productDaoImp, Mockito.times(1)).saveProduct(product4);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void TestDeleteProduct() {
        webClient.mutateWith(csrf())
                .delete()
                .uri("/products/{name}", "product1")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        Mockito.verify(productDaoImp, Mockito.times(1)).deleteProduct("product1");
    }
}

