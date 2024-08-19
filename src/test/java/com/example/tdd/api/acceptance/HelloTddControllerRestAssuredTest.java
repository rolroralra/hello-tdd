package com.example.tdd.api.acceptance;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@WireMockTest(httpPort = 9090)
class HelloTddControllerRestAssuredTest extends AbstractRestAssuredTest{

    @Override
    protected String identifier() {
        return "rest-assured";
    }

    @ParameterizedTest(name = "GET /api/v1/hello?name={0} --> Hello, {0}!")
    @ValueSource(strings = {"rolrorlra", "rolrorlra1", "rolrorlra2"})
    @DisplayName("GET /api/v1/hello?name={name} 정상 작동 테스트")
    void testHelloApi(String givenName) {
        given(
            queryParameters(
                parameterWithName("name").description("이름").optional()
            )
        )
        .when()
            .get("/api/v1/hello?name={name}", givenName)
        .then()
            .statusCode(200)
            .body(equalTo("Hello, " + givenName + "!"));
    }

    @ParameterizedTest(name = "GET /api/v1/integration?name={0} --> Hello, {0}! Integration is {1}")
    @CsvSource({"rolrorlra, 123123123", "rolrorlra1, 123123123", "rolrorlra2, 1231232131"})
    @DisplayName("GET /api/v1/integration?name={name} 정상 작동 테스트")
    void testIntegrationApi(String givenName, String givenIntegrationApiResponse) {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/data"))
            .willReturn(WireMock.aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .withBody(givenIntegrationApiResponse)));

        given(
            queryParameters(
                parameterWithName("name").description("이름").optional()
            )
        )
        .when()
            .get("/api/v1/integration?name={name}", givenName)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body(is("Hello, " + givenName + "! Integration is " + givenIntegrationApiResponse));
    }
}