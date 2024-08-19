package com.example.tdd.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MockitoTest {
    interface GameNumGen {
        String generate(GameLevel gameLevel);
    }

    enum GameLevel {
        EASY
    }

    private GameNumGen gameNumGenMock;

    @BeforeEach
    void setUp() {
        gameNumGenMock = mock(GameNumGen.class);
    }

    @Test
    void mockTest_By_BDDMockito_given_method() {
        given(gameNumGenMock.generate(GameLevel.EASY)).willReturn("123");

        String gameNumber = gameNumGenMock.generate(GameLevel.EASY);

        assertThat(gameNumber).isEqualTo("123");
    }

    @Test
    void mockTest_By_Mockito_when_method() {
        when(gameNumGenMock.generate(eq(GameLevel.EASY))).thenReturn("123");

        String gameNumber = gameNumGenMock.generate(GameLevel.EASY);

        assertThat(gameNumber).isEqualTo("123");
    }


    @Test
    void mockThrowTest_By_BDDMockito_given_method() {
        given(gameNumGenMock.generate(GameLevel.EASY)).willThrow(IllegalArgumentException.class);
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> gameNumGenMock.generate(GameLevel.EASY));
    }

    @Test
    void mockThrowTest_By_Mockito_when_method() {
        when(gameNumGenMock.generate(eq(GameLevel.EASY))).thenThrow(IllegalArgumentException.class);

        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> gameNumGenMock.generate(GameLevel.EASY));
    }

    @Test
    void mockThrowTest_void_method_By_BDDMockito() {
        @SuppressWarnings("unchecked")
        List<String> mockList = mock(List.class);

        willThrow(UnsupportedOperationException.class)
            .given(mockList)
            .clear();

        assertThatExceptionOfType(UnsupportedOperationException.class)
            .isThrownBy(mockList::clear);
    }

    @Test
    void mockThrowTest_void_method_By_Mockito_doThrow_or_doNothing() {
        @SuppressWarnings("unchecked")
        List<String> mockList = mock(List.class);

        doThrow(UnsupportedOperationException.class)
            .when(mockList)
            .clear();

        assertThatExceptionOfType(UnsupportedOperationException.class)
            .isThrownBy(mockList::clear);
    }

    @Test
    void mockNothing_Test_void_method_By_BDDMockito() {
        @SuppressWarnings("unchecked")
        List<String> mockList = mock(List.class);

        willDoNothing().given(mockList).clear();

        assertThatNoException().isThrownBy(mockList::clear);
    }

    @Test
    void mockNothing_Test_void_method_By_Mockito_doThrow_or_doNothing() {
        @SuppressWarnings("unchecked")
        List<String> mockList = mock(List.class);

        doNothing().when(mockList).clear();

        assertThatNoException().isThrownBy(mockList::clear);
    }
}
