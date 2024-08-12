package com.example.tdd.user;

import com.example.tdd.user.email.EmailNotifier;
import com.example.tdd.user.exception.DuplicationIdException;
import com.example.tdd.user.exception.WeakPasswordException;
import com.example.tdd.user.password.WeakPasswordChecker;
import com.example.tdd.user.repository.UserRepository;

public class UserRegister {
    private final WeakPasswordChecker passwordChecker;
    private final UserRepository userRepository;
    private final EmailNotifier emailNotifier;

    public UserRegister(WeakPasswordChecker passwordChecker,
        UserRepository userRepository,
        EmailNotifier emailNotifier) {
        this.passwordChecker = passwordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email) {
        if (passwordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DuplicationIdException();
        }
        userRepository.save(new User(id, pw, email));

        emailNotifier.sendRegisterEmail(email);
    }
}
