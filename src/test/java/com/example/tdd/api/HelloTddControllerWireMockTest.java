package com.example.tdd.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WireMockTest(httpPort = 9090)
class HelloTddControllerWireMockTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @ParameterizedTest(name = "GET /api/v1/hello?name={0} --> Hello, {0}!")
    @ValueSource(strings = {"rolrorlra", "rolrorlra1", "rolrorlra2"})
    @DisplayName("GET /api/v1/hello?name={name} 정상 작동 테스트")
    void testHelloApi(String givenName) {
        // When
        ResponseEntity<String> response = testRestTemplate.exchange(
            "/api/v1/hello?name={name}",
            HttpMethod.GET,
            null,
            String.class,
            givenName
        );

        // Then
        assertThat(response.getBody()).isEqualTo("Hello, " + givenName + "!");
    }

    @ParameterizedTest(name = "GET /api/v1/integration?name={0} --> Hello, {0}! Integration is {1}")
    @CsvSource({"rolrorlra, 123123", "rolrorlra1, 123123", "rolrorlra2, 123123"})
    @DisplayName("GET /api/v1/integration?name={name} 정상 작동 테스트")
    void testIntegrationApi(String givenName, String givenIntegrationApiResponse) {
        // Given
        stubFor(WireMock.get(urlEqualTo("/data"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .withBody(givenIntegrationApiResponse)));

        // When
        ResponseEntity<String> response = testRestTemplate.exchange(
            "/api/v1/integration?name={name}",
            HttpMethod.GET,
            null,
            String.class,
            givenName
        );

        // Then
        assertThat(response.getBody())
            .isEqualTo("Hello, " + givenName + "! Integration is " + givenIntegrationApiResponse);
    }

    @ParameterizedTest(name = "GET /api/v1/integration?name={0} --> 500 Internal Server Error HTTP/1.1")
    @CsvSource({"rolrorlra, 123123"})
    @DisplayName("GET /api/v1/integration?name={name} Timeout 예외 테스트")
    void testIntegrationApiWhenTimeout(String givenName, String givenIntegrationApiResponse) {
        // Given
        stubFor(WireMock.get(urlEqualTo("/data"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .withBody(givenIntegrationApiResponse)
                .withFixedDelay(5000)));

        // When
        ResponseEntity<String> response = testRestTemplate.exchange(
            "/api/v1/integration?name={name}",
            HttpMethod.GET,
            null,
            String.class,
            givenName
        );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}