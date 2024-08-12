package com.example.tdd.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class ArgumentMatcherTest {
    interface GameNumGen {
        String generate(GameLevel gameLevel);
    }

    enum GameLevel {
        EASY,
        NORMAL,
        HARD
    }

    @ParameterizedTest
    @EnumSource(GameLevel.class)
    void anyMatchTest_BDD(GameLevel gameLevel) {
        // Given
        String givenValue = "123";
        GameNumGen gameNumGenMock = mock(GameNumGen.class);
        given(gameNumGenMock.generate(any(GameLevel.class))).willReturn(givenValue);

        // When
        String gameNumber = gameNumGenMock.generate(gameLevel);

        // Then
        assertThat(gameNumber).isEqualTo(givenValue);
    }

    @ParameterizedTest
    @EnumSource(GameLevel.class)
    void anyMatchTest(GameLevel gameLevel) {
        // Given
        String givenValue = "123";
        GameNumGen gameNumGenMock = mock(GameNumGen.class);
        when(gameNumGenMock.generate(any(GameLevel.class))).thenReturn(givenValue);

        // When
        String gameNumber = gameNumGenMock.generate(gameLevel);

        // Then
        assertThat(gameNumber).isEqualTo(givenValue);
    }

    /**
     *
     * <p>
     * org.mockito.exceptions.misusing.InvalidUseOfMatchersException:<br/>
     * Invalid use of argument matchers!<br/>
     * 2 matchers expected, 1 recorded:<br/>
     * -> <code>at com.example.tdd.mockito.ArgumentMatcherTest.whenUseArgumentMatchersThenMustUseAllArgumentMatchers(ArgumentMatcherTest.java:65)</code>
     * <p>
     * This exception may occur if matchers are combined with raw values:</br>
     *     incorrect:<br/>
     *     <code>someMethod(any(), "raw String");</code><br/>
     * When using matchers, all arguments have to be provided by matchers.<br/>
     * For example:<br/>
     *     correct:<br/>
     *     <code>someMethod(any(), eq("String by matcher"));</code><br/>
     * </p>
     */
    @DisplayName("ArgumentMatchers를 하나라도 사용하면, 모두 ArgumentMatchers를 사용해야 한다.")
    @Test
    void whenUseArgumentMatchersThenMustUseAllArgumentMatchers() {
        // Given
        String givenValue = "123";
        @SuppressWarnings("unchecked")
        List<String> mockList = mock(List.class);

        // When
        when(mockList.set(anyInt(), givenValue)).thenReturn(givenValue);    // InvalidUseOfMatchersException 발생

        // Then
        assertThat(mockList.set(0, givenValue)).isEqualTo(givenValue);
    }
}
