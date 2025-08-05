package com.jyotirmay.tinygateway.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class Resilience4jRateLimiterFilter extends AbstractGatewayFilterFactory<Object> {

    private final RateLimiter rateLimiter;

    public Resilience4jRateLimiterFilter(RateLimiterRegistry registry) {
        this.rateLimiter = registry.rateLimiter("apiGatewayLimiter");
    }

    @Override
    public GatewayFilter apply(Object config) {

        return (exchange, chain) -> Mono.defer(() -> {
            if (!rateLimiter.acquirePermission()) {
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        });
    }
}
