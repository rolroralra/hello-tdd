package com.example.tdd.practice.example;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tdd.api.HelloTddController;
import com.example.tdd.client.IntegrationClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * [미션 4 정답] MockMvc
 *
 * @see com.example.tdd.practice.Mission4MockMvcTest
 */
@DisplayName("[미션 4 정답] MockMvc")
@WebMvcTest(HelloTddController.class)
class Mission4MockMvcSolutionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IntegrationClient integrationClient;

    @Test
    @DisplayName("4-1. GET /api/v1/hello?name=tdd -> Hello, tdd!")
    void hello() throws Exception {
        mockMvc.perform(get("/api/v1/hello").param("name", "tdd"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello, tdd!"));
    }

    @Test
    @DisplayName("4-2. name 파라미터가 없으면 Hello, Developer!")
    void helloWithDefaultName() throws Exception {
        mockMvc.perform(get("/api/v1/hello"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello, Developer!"));
    }

    @Test
    @DisplayName("4-3. GET /api/v1/integration?name=tdd -> Integration 결과를 포함한다")
    void integration() throws Exception {
        // given
        given(integrationClient.callIntegration("tdd")).willReturn("SUCCESS");

        // when & then
        mockMvc.perform(get("/api/v1/integration").param("name", "tdd"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string("Hello, tdd! Integration is SUCCESS"));

        verify(integrationClient).callIntegration("tdd");
    }
}
