package com.example.tdd.config;

import com.example.tdd.client.IntegrationConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(IntegrationConfig.class)
public class AppConfig {

}
