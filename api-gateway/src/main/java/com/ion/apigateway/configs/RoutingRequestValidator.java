package com.ion.apigateway.configs;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RoutingRequestValidator {

    public static final List<String> bypassEndpoints = List.of(
            "/api/v1/users/signup",
            "/api/v1/users/generateToken",
            "/api/v1/users/validateToken"
    );

    public Predicate<ServerHttpRequest> isAuthenticated =
            serverHttpRequest -> bypassEndpoints
                    .stream()
                    .noneMatch(endpoint -> serverHttpRequest
                            .getURI()
                            .getPath()
                            .contains(endpoint)
                    );

}