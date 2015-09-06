package ru.kpfu.itis.administration.listener;

import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import ru.kpfu.itis.model.User;
import ru.kpfu.jbl.auth.util.PasswordHelper;

/**
 * Created by ermolaev.a on 17.08.2015.
 */
public class UserEventListener extends AbstractRepositoryEventListener<User> {

    @Override
    public void onBeforeSave(User user) {
        System.out.println("rere");
        if(user.getEntranceYear()<2000){
            user.setEntranceYear(null);
        }
        String pass = user.getPassword();
        if (pass.length()<32) {
            user.setSalt(PasswordHelper.generateSalt());
            user.setPassword(PasswordHelper.encrypt(pass, user.getSalt()));
        }
    }

    @Override
    public void onBeforeCreate(User user) {
        if(user.getEntranceYear()<2000){
            user.setEntranceYear(null);
        }
        user.setSalt(PasswordHelper.generateSalt());
        user.setPassword(PasswordHelper.encrypt(user.getPassword(), user.getSalt()));
    }
}
