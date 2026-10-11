package com.example.tdd.practice.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.willReturn;

import com.example.tdd.client.IntegrationClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

/**
 * [미션 5 정답] RestAssured
 *
 * @see com.example.tdd.practice.Mission5RestAssuredTest
 */
@DisplayName("[미션 5 정답] RestAssured")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Mission5RestAssuredSolutionTest {

    @LocalServerPort
    private int port;

    @MockBean
    private IntegrationClient integrationClient;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("5-1. GET /api/v1/hello?name=tdd -> Hello, tdd!")
    void hello() {
        given()
            .log().all()
            .queryParam("name", "tdd")
        .when()
            .get("/api/v1/hello")
        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .body(equalTo("Hello, tdd!"));
    }

    @Test
    @DisplayName("5-2. GET /api/v1/integration?name=tdd -> Integration 결과를 포함한다")
    void integration() {
        // given
        willReturn("SUCCESS").given(integrationClient).callIntegration("tdd");

        // when & then
        given()
            .log().all()
            .queryParam("name", "tdd")
        .when()
            .get("/api/v1/integration")
        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .body(equalTo("Hello, tdd! Integration is SUCCESS"));
    }

    @Test
    @DisplayName("5-3. 존재하지 않는 URL 은 404 를 반환한다")
    void notFound() {
        given()
            .log().all()
        .when()
            .get("/api/v1/unknown")
        .then()
            .log().all()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
