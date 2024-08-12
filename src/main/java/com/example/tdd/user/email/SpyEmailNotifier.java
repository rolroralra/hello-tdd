package com.example.tdd.user.email;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class SpyEmailNotifier implements EmailNotifier{
    private final List<String> receivedEmails = new ArrayList<>();

    @Override
    public void sendRegisterEmail(String email) {
        receivedEmails.add(email);
    }
}
