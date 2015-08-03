package ru.kpfu.itis.controller;

import com.mangofactory.swagger.annotations.ApiIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.controller.validators.RegFormValidator;
import ru.kpfu.itis.model.form.RegistrationForm;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.service.XlsService;
import ru.kpfu.itis.util.XlsUtil;

@ApiIgnore
@Controller
@RequestMapping(value = "register")
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    XlsService xlsService;

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

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String uploadUsersXls(@RequestParam("users") MultipartFile file, ModelMap map) {
        if (file == null || !XlsUtil.isXlsFile(file)) {
            map.put("uploadError", true);
            return "redirect:/register";
        }
        boolean success = xlsService.saveUsers(file);
        if (!success) {
            map.addAttribute("saveError", true);
        }
        return "redirect:/";
    }

}
