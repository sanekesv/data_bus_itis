package ru.kpfu.itis.util;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.RoleEnum;
import ru.kpfu.itis.model.enums.UserGroup;
import ru.kpfu.itis.model.form.RegistrationForm;
import ru.kpfu.jbl.auth.util.PasswordHelper;

/**
 * Created by ermolaev.a on 24.07.2015.
 */
public class FormMappers {
    public static User mapRegistrationForm(RegistrationForm form) {
        User user = new User();
        user.setLogin(form.getLogin());
        user.setName(form.getName());
        user.setSalt(PasswordHelper.generateSalt());
        user.setPassword(PasswordHelper.encrypt(form.getPassword(), user.getSalt()));
        user.setRole(RoleEnum.STUDENT);
        return user;
    }
}
