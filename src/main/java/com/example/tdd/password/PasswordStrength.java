package com.example.tdd.password;

import java.util.Arrays;

public enum PasswordStrength {
    STRONG(0),
    NORMAL(1),
    WEAK(2, 3),
    INVALID();

    public final int[] allowableCounts;

    PasswordStrength(int... allowableCount)    {
        this.allowableCounts = allowableCount;
    }

    public static PasswordStrength fromAllowableCount(long allowableCount) {
        return Arrays.stream(values())
            .filter(strength -> strength.match(allowableCount))
            .findFirst()
            .orElse(INVALID);
    }

    private boolean match(long allowableCount) {
        return Arrays.stream(allowableCounts)
                .anyMatch(count -> count == allowableCount);
    }
}
