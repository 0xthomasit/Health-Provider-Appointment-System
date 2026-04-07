package com.ion.appointment.configs;

import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(
            @NonNull HttpRequest request,
            byte @NonNull [] body,
            @NonNull ClientHttpRequestExecution execution) throws IOException {

        // Retrieve JWT token from RoleFilter's ThreadLocal
        String jwtToken = RoleFilter.getJwtToken();

        // If JWT token is present, add it to the Authorization header
        if (jwtToken != null && !jwtToken.isEmpty()) {
            HttpHeaders headers = request.getHeaders();
            headers.add("Authorization", jwtToken);
        }

        // Proceed with the request and get the response
        return execution.execute(request, body); // Correctly returning the response
    }

}