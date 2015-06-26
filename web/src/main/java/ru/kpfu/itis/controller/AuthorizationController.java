package ru.kpfu.itis.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.service.UserService;

@Controller
@Api(value = "Login", description = "Token login based on username and password")
@RequestMapping(value = "api")
public class AuthorizationController {

    @Autowired
    UserService userService;

    @ApiOperation(httpMethod = "POST", value = "Proxy method for auth: really we use POST to /login. be careful")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Authenticated(@RequestParam String username, @RequestParam String password) {
        return null;
    }
}
