package com.example.tdd.practice.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.tdd.user.User;
import com.example.tdd.user.UserRegister;
import com.example.tdd.user.email.EmailNotifier;
import com.example.tdd.user.exception.DuplicationIdException;
import com.example.tdd.user.exception.WeakPasswordException;
import com.example.tdd.user.password.WeakPasswordChecker;
import com.example.tdd.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * [미션 3 정답] Mockito
 *
 * @see com.example.tdd.practice.Mission3MockitoTest
 */
@DisplayName("[미션 3 정답] Mockito")
@ExtendWith(MockitoExtension.class)
class Mission3MockitoSolutionTest {

    private static final String ID = "id";
    private static final String PASSWORD = "pw";
    private static final String EMAIL = "email@test.com";

    @Mock
    private WeakPasswordChecker passwordChecker;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailNotifier emailNotifier;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    private UserRegister userRegister;

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(passwordChecker, userRepository, emailNotifier);
    }

    @Test
    @DisplayName("3-1. 약한 암호면 WeakPasswordException 이 발생한다")
    void weakPassword() {
        // given
        when(passwordChecker.checkPasswordWeak(PASSWORD)).thenReturn(true);

        // when & then
        assertThatThrownBy(() -> userRegister.register(ID, PASSWORD, EMAIL))
            .isInstanceOf(WeakPasswordException.class);
    }

    @Test
    @DisplayName("3-2. 이미 존재하는 ID 면 DuplicationIdException 이 발생한다")
    void duplicateId() {
        // given (BDD 스타일)
        given(userRepository.findById(ID)).willReturn(new User(ID, PASSWORD, EMAIL));

        // when & then
        assertThatThrownBy(() -> userRegister.register(ID, PASSWORD, EMAIL))
            .isInstanceOf(DuplicationIdException.class);
    }

    @Test
    @DisplayName("3-3. 가입에 실패하면 이메일을 보내지 않는다")
    void doNotSendEmailWhenFail() {
        // given
        when(passwordChecker.checkPasswordWeak(PASSWORD)).thenReturn(true);

        // when
        assertThatThrownBy(() -> userRegister.register(ID, PASSWORD, EMAIL))
            .isInstanceOf(WeakPasswordException.class);

        // then
        verify(emailNotifier, never()).sendRegisterEmail(anyString());
        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("3-4. 가입에 성공하면 입력한 이메일로 메일을 보낸다")
    void sendEmailWhenSuccess() {
        // when
        userRegister.register(ID, PASSWORD, EMAIL);

        // then
        verify(emailNotifier).sendRegisterEmail(EMAIL);
    }

    @Test
    @DisplayName("3-5. 가입에 성공하면 입력한 정보로 User 를 저장한다")
    void saveUser() {
        // when
        userRegister.register(ID, PASSWORD, EMAIL);

        // then
        verify(userRepository).save(userCaptor.capture());
        User saved = userCaptor.getValue();

        assertThat(saved.getId()).isEqualTo(ID);
        assertThat(saved.getPassword()).isEqualTo(PASSWORD);
        assertThat(saved.getEmail()).isEqualTo(EMAIL);
    }
}
