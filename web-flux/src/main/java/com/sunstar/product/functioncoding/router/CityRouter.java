package com.sunstar.product.functioncoding.router;

import com.sunstar.product.functioncoding.handler.CalculatorHandler;
import com.sunstar.product.functioncoding.handler.CityHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

/**
 * @Author 黄昌焕
 * @Date 2018-08-23  14:18
 */
@Configuration
public class CityRouter {

    @Bean
    public RouterFunction<ServerResponse> routeCity(CityHandler cityHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        cityHandler::helloCity);
    }

    @Bean
    @Autowired
    public RouterFunction<ServerResponse>routerFunction(final CalculatorHandler calculatorHandler) {
        return RouterFunctions.route(RequestPredicates.path("/calculator"), request ->
                request.queryParam("operator").map(operator ->
                        Mono.justOrEmpty(ReflectionUtils.findMethod(CalculatorHandler.class, operator, ServerRequest.class))
                                .flatMap(method -> (Mono<ServerResponse>) ReflectionUtils.invokeMethod(method, calculatorHandler, request))
                                .switchIfEmpty(ServerResponse.badRequest().build())
                                .onErrorResume(ex -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
                        .orElse(ServerResponse.badRequest().build()));
    }


}