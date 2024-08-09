package com.example.tdd.debit;

import static com.example.tdd.debit.CardValidity.INVALID;
import static com.example.tdd.debit.CardValidity.THEFT;
import static com.example.tdd.debit.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.tdd.debit.repository.AutoDebitInfoRepository;
import com.example.tdd.debit.repository.MemoryAutoDebitInfoRepository;
import com.example.tdd.debit.stub.StubCardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoDebitRegisterFakeTest {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubCardNumberValidator;
    private AutoDebitInfoRepository autoDebitInfoRepository;

    @BeforeEach
    void setUp() {
        stubCardNumberValidator = new StubCardNumberValidator();
        autoDebitInfoRepository = new MemoryAutoDebitInfoRepository();

        register = new AutoDebitRegister(stubCardNumberValidator, autoDebitInfoRepository);
    }

    @Test
    void validCard() {
        // 업체에서 받은 테스트용 유효한 카드 번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);

        assertAll(
            () -> assertTrue(result.isSuccess()),
            () -> assertEquals(VALID, result.getValidity())
        );
    }

    @Test
    void invalidCard() {
        // Given
        String invalidCarNumber = "1234567890123456";
        stubCardNumberValidator.setInvalidCardNumber(invalidCarNumber);

        AutoDebitReq req = new AutoDebitReq("user1", invalidCarNumber);

        // When
        RegisterResult result = this.register.register(req);

        // Then
        assertAll(
            () -> assertEquals(INVALID, result.getValidity()),
            () -> assertFalse(result.isSuccess())
        );
    }

    @Test
    void theftCard() {
        // Given
        String theftCarNumber = "1234567890123456";
        stubCardNumberValidator.setTheftCardNumber(theftCarNumber);

        AutoDebitReq req = new AutoDebitReq("user1", theftCarNumber);

        // When
        RegisterResult result = this.register.register(req);

        // Then
        assertAll(
            () -> assertEquals(THEFT, result.getValidity()),
            () -> assertFalse(result.isSuccess())
        );
    }

    @Test
    void alreadyRegistered_InfoUpdated() {
        // Given
        String givenValidCardNumber = "1234123412341234";
        String givenUserId = "user1";
        AutoDebitReq givenAutoDebitReq = new AutoDebitReq(givenUserId, givenValidCardNumber);

        autoDebitInfoRepository.save(new AutoDebitInfo(givenUserId, givenValidCardNumber));

        // When
        RegisterResult result = register.register(givenAutoDebitReq);

        // Then
        assertAll(
            () -> assertEquals(VALID, result.getValidity()),
            () -> assertEquals(givenValidCardNumber, autoDebitInfoRepository.findOne(givenUserId).getCardNumber())
        );
    }

    @Test
    void notYetRegistered_newInfoRegistered() {
        // Given
        String givenValidCardNumber = "11123123123123112312";
        String givenUserId = "user1";
        AutoDebitReq givenAutoDebitReq = new AutoDebitReq(givenUserId, givenValidCardNumber);

        // When
        RegisterResult result = register.register(givenAutoDebitReq);

        // Then
        assertAll(
            () -> assertEquals(VALID, result.getValidity()),
            () -> assertEquals(givenValidCardNumber,
                autoDebitInfoRepository.findOne(givenUserId).getCardNumber())
        );
    }
}