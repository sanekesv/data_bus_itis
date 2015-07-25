package ru.kpfu.itis.util;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.UserGroup;
import ru.kpfu.itis.model.form.RegistrationForm;

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
        user.setGroup(UserGroup.STUDENT);
        user.setAcademicGroup(form.getAcademicGroup());
        return user;
    }
}