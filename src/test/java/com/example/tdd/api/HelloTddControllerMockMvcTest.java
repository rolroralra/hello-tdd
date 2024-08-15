package com.example.tdd.api;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WireMockTest(httpPort = 9090)
class HelloTddControllerMockMvcTest extends AbstractMockMvcTest {

    @Override
    protected String identifier() {
        return "mock-mvc";
    }

    @ParameterizedTest(name = "GET /api/v1/hello?name={0} --> Hello, {0}!")
    @ValueSource(strings = {"rolrorlra", "rolrorlra1", "rolrorlra2"})
    @DisplayName("GET /api/v1/hello?name={name} 정상 작동 테스트")
    void testHelloApi(String givenName) throws Exception {
        perform(
            get("/api/v1/hello?name={name}", givenName),
            queryParameters(
                parameterWithName("name").description("이름").optional()
            )
        ).andExpect(status().isOk())
        .andExpect(content().string("Hello, " + givenName + "!"))
        .andDo(print());
    }

    @ParameterizedTest(name = "GET /api/v1/integration?name={0} --> Hello, {0}! Integration is {1}")
    @CsvSource({"rolrorlra, 123123123", "rolrorlra1, 123123123", "rolrorlra2, 1231232131"})
    @DisplayName("GET /api/v1/integration?name={name} 정상 작동 테스트")
    void testIntegrationApi(String givenName, String givenIntegrationApiResponse) throws Exception {
        // Given
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/data"))
            .willReturn(WireMock.aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .withBody(givenIntegrationApiResponse)));

        perform(
            get("/api/v1/integration?name={name}", givenName),
            queryParameters(
                parameterWithName("name").description("이름").optional()
            ))
        .andExpect(status().isOk())
        .andExpect(content().string(
            "Hello, " + givenName + "! Integration is " + givenIntegrationApiResponse))
        .andDo(print());
    }
}