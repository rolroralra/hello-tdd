package com.example.tdd.api.mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tdd.api.acceptance.AbstractMockMvcTest;
import com.example.tdd.client.IntegrationClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

class HelloTddControllerMockitoTest extends AbstractMockMvcTest {

    @Override
    protected String identifier() {
        return "mock-mvc";
    }

    @Autowired
    IntegrationClient integrationClient;

    @TestConfiguration
    static class TestIntegrationConfiguration {
        @Primary
        @Bean
        IntegrationClient integrationClient() {
            return mock(IntegrationClient.class);
        }
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
        when(integrationClient.callIntegration(givenName)).thenReturn(givenIntegrationApiResponse);

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