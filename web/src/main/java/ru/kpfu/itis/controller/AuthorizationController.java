package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.SecurityContextUtil;

import java.util.UUID;

@Controller
public class AuthorizationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String HelloWorld() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Authenticated(@RequestParam(value = "error", required = false) String error,
                                ModelMap map) {
        System.out.println(error);
        User user = SecurityContextUtil.getCurrentUser();

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        if (user != null) {
            return "index";
        } else {
            return "login";
        }
    }
}
