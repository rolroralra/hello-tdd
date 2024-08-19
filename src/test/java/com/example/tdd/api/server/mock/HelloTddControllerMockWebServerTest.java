package com.example.tdd.api.server.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

import com.example.tdd.api.acceptance.AbstractRestAssuredTest;
import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class HelloTddControllerMockWebServerTest extends AbstractRestAssuredTest {

    private MockWebServer mockWebServer;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start(9090);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

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
    void testIntegrationApi(String givenName, String givenIntegrationApiResponse)
        throws InterruptedException {
        mockWebServer.enqueue(
            new MockResponse()
                .setBody(givenIntegrationApiResponse)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
        );

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

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        assertThat(recordedRequest)
            .hasFieldOrPropertyWithValue("path", "/data")
            .hasFieldOrPropertyWithValue("method", "GET");
    }
}