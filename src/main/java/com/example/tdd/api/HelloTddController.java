package com.example.tdd.api;

import com.example.tdd.client.IntegrationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class HelloTddController {

    private final IntegrationClient integrationClient;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Developer") String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/integration")
    public String integration(@RequestParam(value = "name", defaultValue = "Developer") String name) {
        String integrationResult = integrationClient.callIntegration(name);

        return "Hello, %s! Integration is %s".formatted(name, integrationResult);
    }
}
