package service;

import model.User;
import org.springframework.stereotype.Service;

/**
 * Created by Александр on 07.02.2015.
 */
@Service
public interface UserService {

    public User findUserByLogin(String login);

    public Boolean matchPasswords(String pass1, String pass2);

    public void saveUser(User user);
}
