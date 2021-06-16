package com.ourcastle.reactivewebfluxtutorial.webclient;

import org.springframework.web.reactive.function.client.WebClient;

public class ProductWebClient {

    WebClient client = WebClient.create("http://localhost:8080");

}
