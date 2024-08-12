package com.example.tdd.debit;

import static com.example.tdd.debit.CardValidity.INVALID;
import static com.example.tdd.debit.CardValidity.THEFT;
import static com.example.tdd.debit.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.tdd.debit.repository.AutoDebitInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AutoDebitRegisterMockTest {
    private AutoDebitRegister register;

    @Mock
    private CardNumberValidator mockCardNumberValidator;

    @Mock
    private AutoDebitInfoRepository mockAutoDebitInfoRepository;

    @BeforeEach
    void setUp() {
        register = new AutoDebitRegister(mockCardNumberValidator, mockAutoDebitInfoRepository);
    }

    @ParameterizedTest
    @CsvSource({"user1, 1234123412341234"})
    void validCard(String userId, String cardNumber) {
        // Given
        when(mockCardNumberValidator.validate(eq(cardNumber))).thenReturn(VALID);

        // 업체에서 받은 테스트용 유효한 카드 번호 사용
        AutoDebitReq req = new AutoDebitReq(userId, cardNumber);
        RegisterResult result = this.register.register(req);

        assertAll(
            () -> assertTrue(result.isSuccess()),
            () -> assertEquals(VALID, result.getValidity())
        );
    }

    @ParameterizedTest
    @CsvSource({"user1, 1234123412341234"})
    void invalidCard(String userId, String cardNumber) {
        // Given
        when(mockCardNumberValidator.validate(eq(cardNumber))).thenReturn(INVALID);

        AutoDebitReq req = new AutoDebitReq(userId, cardNumber);

        // When
        RegisterResult result = this.register.register(req);

        // Then
        assertAll(
            () -> assertEquals(INVALID, result.getValidity()),
            () -> assertFalse(result.isSuccess())
        );
    }

    @ParameterizedTest
    @CsvSource({"user1, 1234123412341234"})
    void theftCard(String userId, String cardNumber) {
        // Given
        when(mockCardNumberValidator.validate(eq(cardNumber))).thenReturn(THEFT);

        AutoDebitReq req = new AutoDebitReq(userId, cardNumber);

        // When
        RegisterResult result = this.register.register(req);

        // Then
        assertAll(
            () -> assertEquals(THEFT, result.getValidity()),
            () -> assertFalse(result.isSuccess())
        );
    }

    @ParameterizedTest
    @CsvSource({"user1, 1234123412341234"})
    void alreadyRegistered_InfoUpdated(String userId, String cardNumber) {
        // Given
        String otherCarNumber = "11111111123123123";
        when(mockCardNumberValidator.validate(eq(cardNumber))).thenReturn(VALID);
        when(mockAutoDebitInfoRepository.findOne(eq(userId))).thenReturn(new AutoDebitInfo(userId, otherCarNumber));

        AutoDebitReq givenAutoDebitReq = new AutoDebitReq(userId, cardNumber);

        // When
        RegisterResult result = register.register(givenAutoDebitReq);

        // Then
        assertAll(
            () -> assertEquals(VALID, result.getValidity()),
            () -> assertEquals(cardNumber, mockAutoDebitInfoRepository.findOne(userId).getCardNumber())
        );

        verify(mockAutoDebitInfoRepository, never()).save(any());
    }

    @ParameterizedTest
    @CsvSource({"user1, 1234123412341234"})
    void notYetRegistered_newInfoRegistered(String userId, String cardNumber) {
        // Given
        when(mockCardNumberValidator.validate(eq(cardNumber))).thenReturn(VALID);
        when(mockAutoDebitInfoRepository.findOne(eq(userId)))
            .thenReturn(isNull(), new AutoDebitInfo(userId, cardNumber));

        AutoDebitReq givenAutoDebitReq = new AutoDebitReq(userId, cardNumber);

        // When
        RegisterResult result = register.register(givenAutoDebitReq);

        // Then
        assertAll(
            () -> assertEquals(VALID, result.getValidity()),
            () -> assertEquals(cardNumber,
                mockAutoDebitInfoRepository.findOne(userId).getCardNumber())
        );
    }
}