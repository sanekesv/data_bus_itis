package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.form.RegistrationForm;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.FormMappers;


@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public Boolean matchPasswords(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUser(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public Long register(RegistrationForm form) {
        User user = userRepository.findOneByLogin(form.getLogin());
        if (user == null) {
            User newUser = FormMappers.mapRegistrationForm(form);
            newUser = userRepository.save(newUser);
            return newUser.getId();
        }
        return null;
    }
}
