package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserService;
import util.PasswordHelper;
import util.SecurityContextUtil;

import java.util.UUID;

/**
 * Created by Александр on 05.02.2015.
 */
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
