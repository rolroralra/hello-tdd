package com.example.tdd.client.impl;

import com.example.tdd.client.IntegrationClient;
import com.example.tdd.client.IntegrationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class DefaultIntegrationClient implements IntegrationClient {
    private final IntegrationConfig integrationConfig;

    @Override
    public String callIntegration(String name) {
        WebClient webClient = WebClient.create(integrationConfig.url());

        return webClient.get()
            .uri("/data")
            .retrieve()
            .bodyToMono(String.class)
            .block(integrationConfig.timeout());
    }
}
