package ru.kpfu.itis.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.listResponse;

import java.util.List;

/**
 * Created by sanekesv on 02.08.15.
 */
@Controller
@Api(value = "group", description = "User api")
@RequestMapping(value = "api")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(httpMethod = "POST", value = "Fid all users in group")
    @RequestMapping(value = "/get/all/users/group", method = RequestMethod.POST)
    @ResponseBody
    public listResponse<UserDto> getUsersByGroup (@RequestParam String group){
        List<UserDto> usersByGroup = userService.findUsersByGroup(group);
        listResponse<UserDto> listResponse = new listResponse<>(usersByGroup);
        return listResponse;
    }
}
