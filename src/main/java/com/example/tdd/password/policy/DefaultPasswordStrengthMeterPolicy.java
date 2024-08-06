package com.example.tdd.password.policy;

import com.example.tdd.password.criteria.PasswordCriteria;
import com.example.tdd.password.PasswordStrength;

import java.util.List;

public class DefaultPasswordStrengthMeterPolicy implements PasswordStrengthMeterPolicy {
    @Override
    public PasswordStrength meter(String password, List<PasswordCriteria> criteria) {
        long verifiedCount = criteria.stream().filter(c -> c.verify(password)).count();

        return PasswordStrength.fromAllowableCount(criteria.size() - verifiedCount);
    }
}
