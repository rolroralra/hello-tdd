package com.example.tdd.password;

import java.util.Arrays;

public enum PasswordStrength {
    STRONG(0),
    NORMAL(1),
    WEAK(2),
    INVALID(null);

    public final Integer allowableCount;

    PasswordStrength(Integer allowableCount)    {
        this.allowableCount = allowableCount;
    }

    public static PasswordStrength fromAllowableCount(long allowableCount) {
        return Arrays.stream(values())
            .filter(strength -> strength.allowableCount != null && strength.allowableCount >= allowableCount)
            .findFirst()
            .orElse(INVALID);
    }
}
