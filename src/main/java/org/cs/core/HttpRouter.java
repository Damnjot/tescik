package org.cs.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
public class HttpRouter {

    @Bean
    public RouterFunction<ServerResponse> routeUsers(UserHandler handler) {
        return nest(path("/api/users"),
            RouterFunctions
                    .route(POST("/"), handler::addUser)
                    .andRoute(GET("/{id}"), handler::getUser)
                    .andRoute(GET("/"), handler::getAllUsers)
        );
    }

}