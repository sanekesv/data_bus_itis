package ru.kpfu.itis.administration.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.UserHelper;
import ru.kpfu.jbl.auth.util.PasswordHelper;

/**
 * Created by ermolaev.a on 17.08.2015.
 */
public class UserEventListener extends AbstractRepositoryEventListener<User> {

    @Autowired
    UserService userService;

    @Override
    public void onBeforeSave(User user){
        System.out.println("rere");
        String login = user.getLogin();
        String pass = user.getPassword();
        User findingUser = userService.findUser(login);
        if (findingUser == null){
            user.setSalt(PasswordHelper.generateSalt());
            user.setPassword(PasswordHelper.encrypt(pass, user.getSalt()));
        } else {
            UserHelper.changeField(user, findingUser);
            if(!user.getPassword().equals(findingUser.getPassword())){
                user.setSalt(PasswordHelper.generateSalt());
                user.setPassword(PasswordHelper.encrypt(pass, user.getSalt()));
            }
        }

    }

    @Override
    public void onBeforeCreate(User user) {
        user.setSalt(PasswordHelper.generateSalt());
        user.setPassword(PasswordHelper.encrypt(user.getPassword(), user.getSalt()));
    }
}
