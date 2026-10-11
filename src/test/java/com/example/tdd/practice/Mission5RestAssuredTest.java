package com.example.tdd.practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.BDDMockito.willReturn;

import com.example.tdd.api.HelloTddController;
import com.example.tdd.client.IntegrationClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

/**
 * [미션 5] RestAssured
 *
 * 대상 클래스: {@link HelloTddController}
 *
 * MockMvc 와 달리 RestAssured 는 실제로 서버를 띄우고 HTTP 요청을 보냅니다. (인수 테스트에 적합)
 * given() - when() - then() 구조로 읽기 쉬운 테스트를 작성할 수 있습니다.
 *
 * 진행 방법
 * 1. 아래 @Disabled 를 지운다.
 * 2. 클래스/필드 어노테이션과 setUp 을 완성한다.
 * 3. 각 테스트의 fail(...) 을 지우고 TODO 를 채운다.
 */
@Disabled("미션을 시작하려면 이 줄을 지우세요")
@DisplayName("[미션 5] RestAssured")
// TODO: 랜덤 포트로 실제 서버를 띄우는 어노테이션을 붙이세요.
// 힌트: @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class Mission5RestAssuredTest {

    // TODO: 실제로 할당된 포트를 주입받으세요.
    // 힌트: @LocalServerPort
    private int port;

    // TODO: 외부 API 를 호출하는 IntegrationClient 를 Mock 빈으로 바꿔치기하세요.
    // 힌트: @MockBean
    private IntegrationClient integrationClient;

    @BeforeEach
    void setUp() {
        // TODO: RestAssured 가 랜덤 포트로 요청을 보내도록 설정하세요.
        // 힌트: RestAssured.port = port;
    }

    @Test
    @DisplayName("5-1. GET /api/v1/hello?name=tdd -> Hello, tdd!")
    void hello() {
        // TODO: given-when-then 으로 요청하고 상태코드 200, 본문 "Hello, tdd!" 를 검증하세요.
        // 힌트:
        //   given()
        //       .log().all()
        //       .queryParam("name", "tdd")
        //   .when()
        //       .get("/api/v1/hello")
        //   .then()
        //       .log().all()
        //       .statusCode(HttpStatus.OK.value())
        //       .body(equalTo(...));
        fail("미션 5-1 을 완성하세요");
    }

    @Test
    @DisplayName("5-2. GET /api/v1/integration?name=tdd -> Integration 결과를 포함한다")
    void integration() {
        // TODO 1: integrationClient.callIntegration("tdd") 가 "SUCCESS" 를 반환하도록 Stub 하세요.
        //   힌트: willReturn("SUCCESS").given(integrationClient).callIntegration("tdd");
        //         (when/thenReturn, given/willReturn 어느 것이든 OK)
        // TODO 2: 요청을 보내고 본문이 "Hello, tdd! Integration is SUCCESS" 인지 검증하세요.
        fail("미션 5-2 를 완성하세요");
    }

    @Test
    @DisplayName("5-3. 존재하지 않는 URL 은 404 를 반환한다")
    void notFound() {
        // TODO: GET /api/v1/unknown 을 호출하고 상태코드 404 를 검증하세요.
        // 힌트: .statusCode(HttpStatus.NOT_FOUND.value())
        fail("미션 5-3 을 완성하세요");
    }
}
