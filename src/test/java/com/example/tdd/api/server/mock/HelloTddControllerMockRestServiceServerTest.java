package com.example.tdd.api.server.mock;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.example.tdd.api.acceptance.AbstractRestAssuredTest;
import com.example.tdd.client.IntegrationClient;
import com.example.tdd.client.IntegrationConfig;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

class HelloTddControllerMockRestServiceServerTest extends AbstractRestAssuredTest {

    private static final Logger log = LoggerFactory.getLogger(
        HelloTddControllerMockRestServiceServerTest.class);

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @TestConfiguration
    @Slf4j
    static class TestIntegrationConfiguration {
        @Autowired
        private IntegrationConfig integrationConfig;

        @Bean
        RestTemplate restTemplate() {
            return new RestTemplateBuilder()
                .rootUri(integrationConfig.url())
                .setConnectTimeout(integrationConfig.timeout())
                .build();
        }

        @Primary
        @Bean
        IntegrationClient integrationClient(RestTemplate restTemplate) {
            return name -> {
                log.info("[RestTemplateIntegrationClient] Calling integration with name: {}", name);
                return restTemplate.exchange(
                        "/data?name={name}", HttpMethod.GET, null, String.class, name)
                    .getBody();
            };
        }
    }

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
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
    void testIntegrationApi(String givenName, String givenIntegrationApiResponse) {
        mockServer.expect(
                ExpectedCount.once(),
                requestToUriTemplate("http://127.0.0.1:9090/data?name={value}", givenName))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess(givenIntegrationApiResponse, MediaType.TEXT_PLAIN));

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

        mockServer.verify();
    }
}