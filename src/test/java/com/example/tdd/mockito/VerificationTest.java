package com.example.tdd.mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.only;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class VerificationTest {
    interface GameNumGen {
        String generate(GameLevel gameLevel);
    }

    enum GameLevel {
        EASY,
        NORMAL,
        HARD
    }

    static class Game {
        private final GameNumGen gameNumGen;
        private String gameNumber;

        public Game(GameNumGen gameNumGen) {
            this.gameNumGen = gameNumGen;
        }

        public void init(GameLevel gameLevel) {
            this.gameNumber = gameNumGen.generate(gameLevel);
        }
    }

    @Test
    @DisplayName("")
    void init_test_by_BDD_Mockito() {
        GameNumGen mock = mock(GameNumGen.class);

        Game game = new Game(mock);

        game.init(GameLevel.EASY);

        then(mock).should(only()).generate(any());
    }

    @Test
    @DisplayName("")
    void init_test_by_Mockito() {
        GameNumGen mock = mock(GameNumGen.class);

        Game game = new Game(mock);

        game.init(GameLevel.EASY);

        verify(mock, times(1)).generate(any());
    }
}
