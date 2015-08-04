package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.AcademicGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.form.RegistrationForm;
import ru.kpfu.jbl.auth.response.UserResponse;
import ru.kpfu.jbl.auth.service.UserServiceAuth;

import java.util.List;

@Service
public interface UserService extends UserServiceAuth {

    Boolean matchPasswords(String pass1, String pass2);

    void saveUser(User user);

    User findUser(String login);

    Long register(RegistrationForm registrationForm);

    List<User> findUsersByGroup(String group);

    List<AcademicGroup> getAllGroups();
}
