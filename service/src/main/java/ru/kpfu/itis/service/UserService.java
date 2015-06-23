package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.User;

@Service
public interface UserService {

    User findUserByLogin(String login);

    Boolean matchPasswords(String pass1, String pass2);

    void saveUser(User user);
}
