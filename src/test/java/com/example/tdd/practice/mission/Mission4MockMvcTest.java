package com.example.tdd.practice.mission;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.example.tdd.api.HelloTddController;
import com.example.tdd.client.IntegrationClient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

/**
 * [미션 4] MockMvc
 *
 * 대상 클래스: {@link HelloTddController}
 *   GET /api/v1/hello?name={name}        -> "Hello, {name}!"   (name 기본값: Developer)
 *   GET /api/v1/integration?name={name}  -> "Hello, {name}! Integration is {IntegrationClient 결과}"
 *
 * MockMvc 는 실제 서버를 띄우지 않고 DispatcherServlet 을 통해 컨트롤러를 호출합니다.
 *
 * 진행 방법
 * 1. 아래 @Disabled 를 지운다.
 * 2. 클래스/필드 어노테이션을 완성한다.
 * 3. 각 테스트의 fail(...) 을 지우고 TODO 를 채운다.
 */
@Disabled("미션을 시작하려면 이 줄을 지우세요")
@DisplayName("[미션 4] MockMvc")
// TODO: 웹 계층(Controller)만 로딩하는 슬라이스 테스트 어노테이션을 붙이세요.
// 힌트: @WebMvcTest(HelloTddController.class)
class Mission4MockMvcTest {

    // TODO: @Autowired 를 붙여 MockMvc 를 주입받으세요.
    private MockMvc mockMvc;

    // TODO: 컨트롤러가 의존하는 IntegrationClient 를 Mock 빈으로 등록하세요.
    // 힌트: @MockBean  -- @WebMvcTest 는 Service/Client 빈을 로딩하지 않기 때문에 필요합니다.
    private IntegrationClient integrationClient;

    @Test
    @DisplayName("4-1. GET /api/v1/hello?name=tdd -> Hello, tdd!")
    void hello() throws Exception {
        // TODO: 요청을 보내고 상태코드 200, 응답 본문 "Hello, tdd!" 를 검증하세요.
        // 힌트:
        //   mockMvc.perform(get("/api/v1/hello").param("name", "tdd"))
        //       .andDo(print())
        //       .andExpect(status().isOk())
        //       .andExpect(content().string(...));
        fail("미션 4-1 을 완성하세요");
    }

    @Test
    @DisplayName("4-2. name 파라미터가 없으면 Hello, Developer!")
    void helloWithDefaultName() throws Exception {
        // TODO: 파라미터 없이 GET /api/v1/hello 를 호출하고 "Hello, Developer!" 를 검증하세요.
        fail("미션 4-2 를 완성하세요");
    }

    @Test
    @DisplayName("4-3. GET /api/v1/integration?name=tdd -> Integration 결과를 포함한다")
    void integration() throws Exception {
        // TODO 1: integrationClient.callIntegration("tdd") 가 "SUCCESS" 를 반환하도록 Stub 하세요.
        //   힌트: given(integrationClient.callIntegration("tdd")).willReturn("SUCCESS");
        // TODO 2: 요청을 보내고 "Hello, tdd! Integration is SUCCESS" 를 검증하세요.
        // TODO 3 (보너스): integrationClient.callIntegration("tdd") 가 호출되었는지 verify 하세요.
        fail("미션 4-3 을 완성하세요");
    }
}
