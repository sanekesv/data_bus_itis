package ru.kpfu.itis.util;

import ru.kpfu.itis.model.User;

/**
 * Created by ermolaev.a on 17.08.2015.
 */
public class UserHelper {

    public static void changeField(User user, User saved){
        if(user.getPassword() == null || user.getPassword().length() < 1)
            user.setPassword(saved.getPassword());
        if(user.getSalt() == null ||user.getSalt().length() < 1)
            user.setSalt(saved.getSalt());
        if(user.getId()==null)
            user.setId(saved.getId());
        if (user.getLogin() == null || user.getLogin().length() < 1)
            user.setLogin(saved.getLogin());
    }
}
