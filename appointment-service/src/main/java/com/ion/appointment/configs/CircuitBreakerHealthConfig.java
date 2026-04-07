package com.ion.appointment.configs;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.NonNull;
import org.springframework.boot.health.contributor.AbstractHealthIndicator;
import org.springframework.boot.health.contributor.Health;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Manual circuit breaker health indicator registration for Spring Boot 4.x compatibility.
 * The resilience4j-spring-boot3 auto-configuration checks for
 * org.springframework.boot.actuate.health.HealthIndicator which was moved to
 * org.springframework.boot.health.contributor.HealthIndicator in Spring Boot 4.
 */
@Configuration
public class CircuitBreakerHealthConfig {

    @Bean("circuitBreakers")
    public AbstractHealthIndicator circuitBreakersHealthIndicator(CircuitBreakerRegistry registry) {
        return new AbstractHealthIndicator() {
            @Override
            protected void doHealthCheck(@NonNull Health.Builder builder) {
                var circuitBreakers = registry.getAllCircuitBreakers();
                boolean allClosed = circuitBreakers.stream()
                        .allMatch(cb -> cb.getState() == CircuitBreaker.State.CLOSED);

                if (allClosed) {
                    builder.up();
                } else {
                    builder.down();
                }

                circuitBreakers.forEach(cb -> builder.withDetail(cb.getName(),
                        new CircuitBreakerDetail(
                                cb.getMetrics().getFailureRate(), // Percentage
                                cb.getCircuitBreakerConfig().getFailureRateThreshold(), // Percentage
                                cb.getMetrics().getSlowCallRate(), // Percentage
                                cb.getCircuitBreakerConfig().getSlowCallRateThreshold(), // Percentage
                                cb.getMetrics().getNumberOfBufferedCalls(),
                                cb.getMetrics().getNumberOfSlowCalls(),
                                cb.getMetrics().getNumberOfSlowFailedCalls(),
                                cb.getMetrics().getNumberOfFailedCalls(),
                                cb.getMetrics().getNumberOfNotPermittedCalls(),
                                cb.getState().name()
                        )));
            }
        };
    }

    record CircuitBreakerDetail(
            float failureRate,
            float failureRateThreshold,
            float slowCallRate,
            float slowCallRateThreshold,
            int bufferedCalls,
            int slowCalls,
            int slowFailedCalls,
            int failedCalls,
            long notPermittedCalls,
            String state
    ) {
    }

}