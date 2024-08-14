package com.example.tdd.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@WireMockTest(httpPort = 9090)
class HelloTddControllerWebTestClientTest {
    @Autowired
    private WebTestClient webTestClient;

    @ParameterizedTest(name = "GET /api/v1/hello?name={0} --> Hello, {0}!")
    @ValueSource(strings = {"rolrorlra", "rolrorlra1", "rolrorlra2"})
    @DisplayName("GET /api/v1/hello?name={name} 정상 작동 테스트")
    void testHelloApi(String givenName) {
        webTestClient.get().uri("/api/v1/hello?name={name}", givenName)
            .exchange()
            .expectAll(
                response -> response.expectStatus().isOk(),
                response -> response.expectBody(String.class).isEqualTo("Hello, " + givenName + "!")
            );
    }

    @ParameterizedTest(name = "GET /api/v1/integration?name={0} --> Hello, {0}! Integration is {1}")
    @CsvSource({"rolrorlra, 123123123", "rolrorlra1, 123123123", "rolrorlra2, 1231232131"})
    @DisplayName("GET /api/v1/integration?name={name} 정상 작동 테스트")
    void testIntegrationApi(String givenName, String givenIntegrationApiResponse) {
        // Given
        stubFor(WireMock.get(urlEqualTo("/data"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .withBody(givenIntegrationApiResponse)));

        webTestClient.get().uri("/api/v1/integration?name={name}", givenName)
            .exchange()
            .expectAll(
                response -> response.expectStatus().isOk(),
                response -> response.expectBody(String.class).isEqualTo("Hello, " + givenName + "! Integration is " + givenIntegrationApiResponse)
            );
    }
}