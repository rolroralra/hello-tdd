package com.example.tdd.user;

import com.example.tdd.user.exception.WeakPasswordException;
import com.example.tdd.user.password.StubWeakPasswordChecker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserRegisterTest {

    private UserRegister userRegister;
    private final StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        stubWeakPasswordChecker.setWeak(true);

        Assertions.assertThatExceptionOfType(WeakPasswordException.class)
            .isThrownBy(() -> userRegister.register("id", "pw", "email"));
    }

}