package com.example.tdd.password.policy;

import com.example.tdd.password.criteria.PasswordCriteria;
import com.example.tdd.password.PasswordStrength;

import java.util.List;

public interface PasswordStrengthMeterPolicy {
    PasswordStrength meter(String password, List<PasswordCriteria> criteria);
}
