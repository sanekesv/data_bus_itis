package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.controller.validators.RegFormValidator;
import ru.kpfu.itis.model.form.RegistrationForm;
import ru.kpfu.itis.service.UserService;

@Controller
@RequestMapping(value = "register")
public class RegistrationController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String registrationGetMethod (ModelMap map) {
        map.put("regForm", new RegistrationForm());
        return "register";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("regForm") RegistrationForm form, BindingResult errors){
        new RegFormValidator().validate(form, errors);
        if (form.getLogin() != null && !form.getLogin().isEmpty() && userService.findUser(form.getLogin()) != null) {
            errors.rejectValue("login", null, "Login already exists");
        }
        if (errors.hasErrors()) {
            return "register";
        }
        Long regId = userService.register(form);
        if (regId == null) {
            errors.rejectValue("login", "user.exists");
            return "register";
        }
        return "index";
    }
}
