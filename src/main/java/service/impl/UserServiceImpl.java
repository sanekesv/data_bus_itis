package service.impl;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;
import service.UserService;

import javax.transaction.Transactional;

/**
 * Created by Александр on 07.02.2015.
 */
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
}
