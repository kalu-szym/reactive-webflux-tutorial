package com.ourcastle.reactivewebfluxtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ourcastle.reactivewebfluxtutorial"})
@EnableWebFlux
public class ReactiveWebfluxTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveWebfluxTutorialApplication.class, args);
    }
}
