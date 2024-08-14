package com.example.tdd.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloTddControllerTestRestTemplateTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @ParameterizedTest(name = "GET /api/v1/hello?name={0} --> Hello, {0}!")
    @ValueSource(strings = {"rolrorlra", "rolrorlra1", "rolrorlra2"})
    @DisplayName("GET /api/v1/hello?name={name} 정상 작동 테스트")
    void testHelloApi(String givenName) {
        ResponseEntity<String> response = testRestTemplate.exchange("/api/v1/hello?name={name}", HttpMethod.GET,
            null, String.class, givenName);

        assertThat(response.getBody()).isEqualTo("Hello, " + givenName + "!");
    }

    @Disabled("Test Double 없이 테스트를 실행하면 외부 의존성으로 인해 테스트가 실패합니다.")
    @ParameterizedTest(name = "GET /api/v1/integration?name={0} --> Hello, {0}!")
    @ValueSource(strings = {"rolrorlra", "rolrorlra1", "rolrorlra2"})
    @DisplayName("GET /api/v1/integration?name={name} 정상 작동 테스트")
    void testIntegrationApi(String givenName) {
        ResponseEntity<String> response = testRestTemplate.exchange("/api/v1/integration?name={name}", HttpMethod.GET,
            null, String.class, givenName);

        assertThat(response.getBody()).isEqualTo("Hello, " + givenName + "!");
    }
}