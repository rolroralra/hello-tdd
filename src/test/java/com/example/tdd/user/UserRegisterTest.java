package com.example.tdd.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.tdd.user.exception.DuplicationIdException;
import com.example.tdd.user.exception.WeakPasswordException;
import com.example.tdd.user.password.StubWeakPasswordChecker;
import com.example.tdd.user.repository.MemoryUserRepository;
import com.example.tdd.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UserRegisterTest {

    private UserRegister userRegister;
    private final StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private final UserRepository fakeUserRepository = new MemoryUserRepository();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeUserRepository);
    }

    @ParameterizedTest(name = "id={0}, password={1}, email={2}")
    @CsvSource({"id, password, email"})
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword(String id, String password, String email) {
        // Given
        stubWeakPasswordChecker.setWeak(true);

        // Expected
        Assertions.assertThatExceptionOfType(WeakPasswordException.class)
            .isThrownBy(() -> userRegister.register(id, password, email));
    }

    @ParameterizedTest(name = "id={0}, password={1}, email={2}")
    @CsvSource({"id, password, email"})
    @DisplayName("약한 암호가 아니라면 가입 성공")
    void strongPassword(String id, String password, String email) {
        // Given
        stubWeakPasswordChecker.setWeak(false);

        // Expected
        Assertions.assertThatNoException()
            .isThrownBy(() -> userRegister.register(id, password, email));
    }

    @ParameterizedTest(name = "id={0}, password={1}, email={2}")
    @CsvSource({"id, password, email"})
    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    void dupIdExists(String id, String password, String email) {
        // Given
        fakeUserRepository.save(new User(id, password, email));

        // Expected
        Assertions.assertThatExceptionOfType(DuplicationIdException.class)
            .isThrownBy(() -> userRegister.register(id, password, email));
    }

    @ParameterizedTest(name = "id={0}, password={1}, email={2}")
    @CsvSource({"id, password, email"})
    @DisplayName("이미 같은 ID가 존재하지 않으면 가입 성공")
    void noDupId_RegisterSuccess(String id, String password, String email) {
        // Expected
        Assertions.assertThatNoException()
            .isThrownBy(() -> userRegister.register(id, password, email));

        assertThat(fakeUserRepository.findById(id))
            .isNotNull()
            .hasFieldOrPropertyWithValue("id", id)
            .hasFieldOrPropertyWithValue("password", password)
            .hasFieldOrPropertyWithValue("email", email);
    }

}