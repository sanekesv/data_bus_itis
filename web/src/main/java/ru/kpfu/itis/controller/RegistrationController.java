package ru.kpfu.itis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.model.form.RegistrationForm;

@Controller
@RequestMapping(value = "registration")
public class RegistrationController {

    @RequestMapping(method = RequestMethod.GET)
    public String registrationGetMethod (ModelMap map) {
        map.put("regForm", new RegistrationForm());
        return "register";
    }
}
