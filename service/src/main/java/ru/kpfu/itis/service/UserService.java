package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.User;
import ru.kpfu.jbl.auth.service.UserServiceAuth;

@Service
public interface UserService extends UserServiceAuth {

    Boolean matchPasswords(String pass1, String pass2);

    void saveUser(User user);
}
