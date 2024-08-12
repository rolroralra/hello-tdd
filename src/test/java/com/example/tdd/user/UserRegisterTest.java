package com.example.tdd.user;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserRegisterTest {

    @Disabled
    @Test
    @DisplayName("")
    void register() {
        UserRegister userRegister = new UserRegister(null, null, null);
        userRegister.register("id", "pw", "email");
    }

}