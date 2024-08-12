package com.example.tdd.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.tdd.user.UserRegister;
import com.example.tdd.user.email.EmailNotifier;
import com.example.tdd.user.password.StubWeakPasswordChecker;
import com.example.tdd.user.repository.MemoryUserRepository;
import com.example.tdd.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;

public class ArgumentCaptorTest {
    private UserRegister userRegister;
    private final StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private final UserRepository fakeUserRepository = new MemoryUserRepository();
    private final EmailNotifier mockEmailNotifier = mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeUserRepository, mockEmailNotifier);
    }

    @ParameterizedTest(name = "id={0}, password={1}, email={2}")
    @CsvSource({"id, password, email@gmail.com"})
    @DisplayName("약한 암호면 가입 실패")
    void argumentCapture_by_ArgumentCaptor_for_class_method(String id, String password, String email) {
        // Given
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        doNothing().when(mockEmailNotifier).sendRegisterEmail(anyString());

        // When
        userRegister.register(id, password, email);

        // Then
        verify(mockEmailNotifier).sendRegisterEmail(captor.capture());

        String emailParameter = captor.getValue();
        assertThat(emailParameter).isEqualTo(email);
    }

    @ParameterizedTest(name = "id={0}, password={1}, email={2}")
    @CsvSource({"id, password, email@gmail.com"})
    @DisplayName("약한 암호면 가입 실패")
    void argumentCapture_by_ArgumentCaptor_captor_method(String id, String password, String email) {
        // Given
        ArgumentCaptor<String> captor = ArgumentCaptor.captor();
        doNothing().when(mockEmailNotifier).sendRegisterEmail(anyString());

        // When
        userRegister.register(id, password, email);

        // Then
        verify(mockEmailNotifier).sendRegisterEmail(captor.capture());

        String emailParameter = captor.getValue();
        assertThat(emailParameter).isEqualTo(email);
    }
}
