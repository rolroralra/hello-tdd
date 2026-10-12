package com.example.tdd.practice.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assumptions.abort;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.example.tdd.user.UserRegister;
import com.example.tdd.user.email.EmailNotifier;
import com.example.tdd.user.password.WeakPasswordChecker;
import com.example.tdd.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * [미션 3] Mockito
 *
 * 대상 클래스: {@link UserRegister}
 * UserRegister 는 WeakPasswordChecker, UserRepository, EmailNotifier 세 개의 협력 객체에 의존합니다.
 * 세 의존성 모두 Mock 으로 대체해서 UserRegister 의 동작만 검증해봅시다.
 *
 * 진행 방법
 * 1. 필드와 setUp 을 완성한다.
 * 2. 각 테스트의 abort(...) 을 지우고 TODO 를 채운다.
 */
@DisplayName("[미션 3] Mockito")
// TODO: Mockito 어노테이션(@Mock, @Captor)이 동작하도록 확장을 등록하세요.
// 힌트: @ExtendWith(MockitoExtension.class)
class Mission3MockitoTest {

    // TODO: 아래 세 필드에 @Mock 을 붙이세요.
    private WeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    private UserRegister userRegister;

    @BeforeEach
    void setUp() {
        // TODO: Mock 객체들을 주입해서 userRegister 를 생성하세요.
        // 힌트: new UserRegister(passwordChecker, userRepository, emailNotifier)
    }

    @Test
    @DisplayName("3-1. 약한 암호면 WeakPasswordException 이 발생한다")
    void weakPassword() {
        // TODO 1 (given): passwordChecker.checkPasswordWeak("pw") 가 true 를 반환하도록 Stub 하세요.
        //   힌트: when(mock.method(arg)).thenReturn(value)
        //         또는 BDD 스타일 given(mock.method(arg)).willReturn(value)
        // TODO 2 (when/then): register("id", "pw", "email@test.com") 호출 시 예외가 발생하는지 검증하세요.
        abort("미션 3-1 을 완성하세요");
    }

    @Test
    @DisplayName("3-2. 이미 존재하는 ID 면 DuplicationIdException 이 발생한다")
    void duplicateId() {
        // TODO 1: userRepository.findById("id") 가 이미 존재하는 User 를 반환하도록 Stub 하세요.
        //   힌트: new User("id", "pw", "email@test.com")
        //   참고: passwordChecker 는 Stub 하지 않으면 기본값 false 를 반환합니다. (boolean 기본값)
        // TODO 2: register 호출 시 DuplicationIdException 이 발생하는지 검증하세요.
        abort("미션 3-2 를 완성하세요");
    }

    @Test
    @DisplayName("3-3. 가입에 실패하면 이메일을 보내지 않는다")
    void doNotSendEmailWhenFail() {
        // TODO 1: 약한 암호 상황을 만들고 register 를 호출하세요. (예외는 assertThatThrownBy 로 잡기)
        // TODO 2: emailNotifier.sendRegisterEmail 이 한 번도 호출되지 않았는지 검증하세요.
        //   힌트: verify(mock, never()).method(anyString())
        abort("미션 3-3 을 완성하세요");
    }

    @Test
    @DisplayName("3-4. 가입에 성공하면 입력한 이메일로 메일을 보낸다")
    void sendEmailWhenSuccess() {
        // TODO 1: register("id", "pw", "email@test.com") 를 호출하세요.
        // TODO 2: emailNotifier.sendRegisterEmail("email@test.com") 이 호출되었는지 검증하세요.
        //   힌트: verify(mock).method(arg)  -- times(1) 이 기본값입니다.
        abort("미션 3-4 를 완성하세요");
    }

    @Test
    @DisplayName("3-5. 가입에 성공하면 입력한 정보로 User 를 저장한다")
    void saveUser() {
        // TODO 1: register("id", "pw", "email@test.com") 를 호출하세요.
        // TODO 2: ArgumentCaptor 로 userRepository.save() 에 전달된 User 를 꺼내서
        //         id / password / email 을 검증하세요.
        //   힌트: ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        //         verify(userRepository).save(captor.capture());
        //         User saved = captor.getValue();
        //   (필드에 @Captor ArgumentCaptor<User> captor; 로 선언해도 됩니다)
        abort("미션 3-5 를 완성하세요");
    }
}
