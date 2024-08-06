package com.example.tdd.password;

import com.example.tdd.password.criteria.PasswordCriteria;
import com.example.tdd.password.criteria.PasswordLengthCriteria;
import com.example.tdd.password.criteria.PasswordNumberCriteria;
import com.example.tdd.password.policy.DefaultPasswordStrengthMeterPolicy;
import com.example.tdd.password.policy.PasswordStrengthMeterPolicy;

import java.util.List;

public class PasswordStrengthMeter {

    private final List<PasswordCriteria> criteria = List.of(
        new PasswordLengthCriteria(),
        new PasswordNumberCriteria()
    );

    private final PasswordStrengthMeterPolicy policy = new DefaultPasswordStrengthMeterPolicy();

    @SuppressWarnings("unused") // TODO: Remove this line and implement the method
    public PasswordStrength meter(String password) {
        return policy.meter(password, criteria);
    }
}
