package ru.kpfu.itis.controller;

import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ApiIgnore
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {
        return "redirect:/api/v1/user/current";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String openLoginPage(){
        return "login";
    }

}
