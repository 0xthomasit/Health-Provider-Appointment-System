package com.ion.appointment.configs;

import com.ion.appointment.clients.HealthProviderClient;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final ObservationRegistry observationRegistry;

    @Bean
    @Primary
    RestClient.Builder defaultRestClientBuilder() {
        return RestClient.builder();
    }

    @Lazy
    @LoadBalanced
    @Bean
    RestClient.Builder loadBalancedRestClientBuilder(JwtInterceptor jwtInterceptor) {
        return RestClient.builder().requestInterceptor(jwtInterceptor);
    }

    @Lazy
    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(@LoadBalanced RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder
                .baseUrl("http://API-GATEWAY")
                .observationRegistry(observationRegistry) // restClientBuilder must have @LoadBalanced for this to work
                .build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        return HttpServiceProxyFactory
                .builderFor(restClientAdapter)
                .build();
    }

    @Lazy
    @Bean
    public HealthProviderClient healthProviderClient(HttpServiceProxyFactory httpServiceProxyFactory) {
        return httpServiceProxyFactory.createClient(HealthProviderClient.class);
    }

}