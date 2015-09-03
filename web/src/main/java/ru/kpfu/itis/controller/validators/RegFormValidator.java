package ru.kpfu.itis.controller.validators;

import org.springframework.validation.Errors;
import ru.kpfu.itis.model.form.RegistrationForm;

/**
 * Created by ermolaev.a on 24.07.2015.
 */
public class RegFormValidator {
    public void validate(RegistrationForm rf, Errors errors) {
        if (rf.getName() == null || rf.getName().isEmpty()) {
            errors.rejectValue("name", null, "Name is empty");
        }
        if (rf.getAcademicGroup() == null || rf.getAcademicGroup().isEmpty()) {
            errors.rejectValue("academicGroup", null, "Academic group is empty");
        }
        if (rf.getPassword() == null || rf.getPassword().isEmpty()) {
            errors.rejectValue("password", null, "Password is empty");
        }
        if (rf.getPassword().length() > 31){
            errors.rejectValue("password", null, "Your password is too long.");
        }
        if (rf.getLogin() == null || rf.getLogin().isEmpty()) {
            errors.rejectValue("login", null, "Login is empty");
        }
    }
}
