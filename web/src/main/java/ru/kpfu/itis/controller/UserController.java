package ru.kpfu.itis.controller;

import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.model.User;
import ru.kpfu.jbl.auth.response.UserResponse;
import ru.kpfu.jbl.auth.service.TokenService;

/**
 * Created by vladislav on 19.04.2015.
 */

@RestController
@Api(value = "User controller", description = "")
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    UserService userService;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "user")
    public UserResponse getCurrentUser(@RequestParam(required = true) String token) {
        User user = (User) tokenService.retrieve(token).getPrincipal();
        UserResponse userDto = new UserResponse();
        userDto.setLogin(user.getLogin());
        userDto.setRole(user.getGroup().toString());
        userDto.setName(user.getName());
        userDto.setFaculty(null);
        userDto.setId(user.getId());
        return userDto;
    }

   

    @ApiOperation(httpMethod = "POST", value = "Fid all users in group")
    @RequestMapping(value = "/get/all/users/group", method = RequestMethod.POST)
    @ResponseBody
    public listResponse<UserDto> getUsersByGroup (@RequestParam String group){
        List<UserDto> usersByGroup = userService.findUsersByGroup(group);
        listResponse<UserDto> listResponse = new listResponse<>(usersByGroup);
        return listResponse;
    }
}
