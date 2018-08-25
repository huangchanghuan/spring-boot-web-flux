package com.sunstar.product.router;


import com.sunstar.product.handler.QuoteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;

import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * @Author 黄昌焕
 * @Date 2018-08-23  14:18
 */
@Configuration
public class QuoteRouter {

    @Bean
    public RouterFunction<ServerResponse> routeCity(QuoteHandler quoteHandler) {
        return RouterFunctions
                .route(GET("/hello").and(accept(TEXT_PLAIN)), quoteHandler::hello)
                .andRoute(POST("/echo").and(accept(TEXT_PLAIN).and(contentType(TEXT_PLAIN))), quoteHandler::echo)
                .andRoute(GET("/quotes").and(accept(APPLICATION_JSON)), quoteHandler::fetchQuotes)
                .andRoute(GET("/quotes").and(accept(APPLICATION_STREAM_JSON)), quoteHandler::streamQuotes);
    }



//    @Bean
//    @Autowired
//    public RouterFunction<ServerResponse>routerFunction(final CalculatorHandler calculatorHandler) {
//        return RouterFunctions.route(RequestPredicates.path("/calculator"), request ->
//                request.queryParam("operator").map(operator ->
//                        Mono.justOrEmpty(ReflectionUtils.findMethod(CalculatorHandler.class, operator, ServerRequest.class))
//                                .flatMap(method -> (Mono<ServerResponse>) ReflectionUtils.invokeMethod(method, calculatorHandler, request))
//                                .switchIfEmpty(ServerResponse.badRequest().build())
//                                .onErrorResume(ex -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()))
//                        .orElse(ServerResponse.badRequest().build()));
//    }


}