package com.example.tdd.client;

import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration")
public record IntegrationConfig(
    String url,
    Duration timeout
) {

}
