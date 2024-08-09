package com.example.tdd.debit.stub;

import com.example.tdd.debit.CardNumberValidator;
import com.example.tdd.debit.CardValidity;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StubCardNumberValidator extends CardNumberValidator {

    private String invalidCardNumber;

    private String theftCardNumber;

    public StubCardNumberValidator() {
        this("");
    }

    public StubCardNumberValidator(String invalidCardNumber) {
        this(invalidCardNumber, "");
    }

    public StubCardNumberValidator(String invalidCardNumber, String theftCardNumber) {
        this.invalidCardNumber = invalidCardNumber;
        this.theftCardNumber = theftCardNumber;
    }

    @Override
    public CardValidity validate(String cardNumber) {
        if (Objects.equals(cardNumber, invalidCardNumber)) {
            return CardValidity.INVALID;
        }

        if (Objects.equals(cardNumber, theftCardNumber)) {
            return CardValidity.THEFT;
        }

        return CardValidity.VALID;
    }
}
