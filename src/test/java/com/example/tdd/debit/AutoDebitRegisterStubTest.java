package com.example.tdd.debit;

import static com.example.tdd.debit.CardValidity.INVALID;
import static com.example.tdd.debit.CardValidity.THEFT;
import static com.example.tdd.debit.CardValidity.VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.tdd.debit.repository.AutoDebitInfoRepository;
import com.example.tdd.debit.stub.StubAutoDebitInfoRepository;
import com.example.tdd.debit.stub.StubCardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoDebitRegisterStubTest {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubCardNumberValidator;
    private AutoDebitInfoRepository stubAutoDebitInfoRepository;

    @BeforeEach
    void setUp() {
        stubCardNumberValidator = new StubCardNumberValidator();
        stubAutoDebitInfoRepository = new StubAutoDebitInfoRepository();

        register = new AutoDebitRegister(stubCardNumberValidator, stubAutoDebitInfoRepository);
    }

    @Test
    void validCard() {
        // 업체에서 받은 테스트용 유효한 카드 번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
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
        assertEquals(INVALID, result.getValidity());
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
        assertEquals(THEFT, result.getValidity());
    }
}