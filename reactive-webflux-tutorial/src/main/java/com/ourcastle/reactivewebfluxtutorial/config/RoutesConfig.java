//package com.ourcastle.reactivewebfluxtutorial.config;
//
//import com.ourcastle.reactivewebfluxtutorial.dao.ProductDao;
//import com.ourcastle.reactivewebfluxtutorial.dao.ProductDaoImp;
//import com.ourcastle.reactivewebfluxtutorial.model.Product;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import static org.springframework.http.ResponseEntity.ok;
//import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
//import static org.springframework.web.reactive.function.server.RouterFunctions.route;
//
//public class RoutesConfig {
//
//    private ProductDaoImp productDaoImp;
//
//    @Bean
//    RouterFunction<ServerResponse> composedRoutes() {
//        return
//                route(GET("/products"),
//                        req -> ok().body(
//                                productDaoImp.getAllProducts(), Product.class))
//
//                        .and(route(GET("/employees/{id}"),
//                                req -> ok().body(
//                                        employeeRepository().findEmployeeById(req.pathVariable("id")), Employee.class)))
//
//                        .and(route(POST("/employees/update"),
//                                req -> req.body(toMono(Employee.class))
//                                        .doOnNext(employeeRepository()::updateEmployee)
//                                        .then(ok().build())));
//    }
//
//}
