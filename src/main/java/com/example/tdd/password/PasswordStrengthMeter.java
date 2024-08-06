package com.example.tdd.password;

import com.example.tdd.password.criteria.PasswordCriteria;
import com.example.tdd.password.criteria.PasswordLengthCriteria;
import com.example.tdd.password.criteria.PasswordNumberCriteria;
import com.example.tdd.password.policy.DefaultPasswordStrengthMeterPolicy;
import com.example.tdd.password.policy.PasswordStrengthMeterPolicy;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PasswordStrengthMeter {

    private final List<PasswordCriteria> criteria = List.of(
        new PasswordLengthCriteria(),
        new PasswordNumberCriteria()
    );

    private final PasswordStrengthMeterPolicy policy = new DefaultPasswordStrengthMeterPolicy();

    public PasswordStrength meter(String password) {
        if (StringUtils.isBlank(password)) {
            return PasswordStrength.INVALID;
        }

        return policy.meter(password, criteria);
    }
}
