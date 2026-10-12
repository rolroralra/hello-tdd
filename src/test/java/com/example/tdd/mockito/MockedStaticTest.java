package com.example.tdd.mockito;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

class MockedStaticTest {
    @Test
    void 현재시간_고정() {
        LocalDateTime fixed = LocalDateTime.of(2026, 10, 12, 13, 0);

        try (MockedStatic<LocalDateTime> mocked = Mockito.mockStatic(LocalDateTime.class)) {
            mocked.when(LocalDateTime::now).thenReturn(fixed);

            assertThat(LocalDateTime.now()).isEqualTo(fixed);
        }

        // 블록 밖에서는 원래 LocalDateTime.now() 동작
        assertThat(LocalDateTime.now()).isNotEqualTo(fixed);
    }
}
