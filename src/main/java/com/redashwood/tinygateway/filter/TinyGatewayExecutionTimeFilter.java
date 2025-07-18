package com.redashwood.tinygateway.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(value = -1)
public class TinyGatewayExecutionTimeFilter implements GatewayFilter {

    private static final Logger LOGGER = LogManager.getLogger(TinyGatewayExecutionTimeFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long start = System.currentTimeMillis();

        return chain.filter(exchange)
                .doOnError(error -> {
                    String path = exchange.getRequest().getURI().getPath();
                    LOGGER.error("Request to [{}] failed after {} ms with error: {}", path, System.currentTimeMillis() - start, error.getMessage());
                })
                .doFinally(signalType -> {
                    String path = exchange.getRequest().getURI().getPath();
                    LOGGER.info("Request to [{}] completed in {} ms with signal [{}]", path, System.currentTimeMillis() - start, signalType);
                });
    }
}
