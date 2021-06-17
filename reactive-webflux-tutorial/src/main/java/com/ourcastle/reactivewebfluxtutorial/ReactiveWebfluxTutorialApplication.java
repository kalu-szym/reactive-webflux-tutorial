package com.ourcastle.reactivewebfluxtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@ComponentScan(basePackages = {"com.ourcastle.reactivewebfluxtutorial"})
@EnableWebFlux
public class ReactiveWebfluxTutorialApplication {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context
				= new AnnotationConfigApplicationContext(ReactiveWebfluxTutorialApplication.class))
		{context.getBean(NettyContext.class).onClose().block();

		}

	}

}
