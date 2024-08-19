package com.example.tdd.client.impl;

import com.example.tdd.client.IntegrationClient;
import com.example.tdd.client.IntegrationConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultIntegrationClient implements IntegrationClient {
    private final IntegrationConfig integrationConfig;

    @Override
    public String callIntegration(String name) {
        WebClient webClient = WebClient.create(integrationConfig.url());

        log.info("[DefaultIntegrationClient] Calling integration with name: {}", name);

        return webClient.get()
            .uri("/data")
            .retrieve()
            .bodyToMono(String.class)
            .block(integrationConfig.timeout());
    }
}
