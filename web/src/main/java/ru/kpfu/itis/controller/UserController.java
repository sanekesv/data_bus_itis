package ru.kpfu.itis.controller;

import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.jbl.auth.response.GroupResponse;
import ru.kpfu.jbl.auth.response.ListResponse;
import ru.kpfu.jbl.auth.response.UserResponse;
import ru.kpfu.jbl.auth.service.TokenService;

import java.util.List;

import static ru.kpfu.itis.util.DtoMappers.*;

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
    private UserService userService;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "user")
    public UserResponse getCurrentUser(@RequestParam(required = true) String token) {
        User user = (User) tokenService.retrieve(token).getPrincipal();
        return userToUserDto(user);
    }


    @ApiImplicitParams(value = {@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "query")})
    @ApiOperation(httpMethod = "POST", value = "Fid all users in group")
    @RequestMapping(value = "/get/all/users/group", method = RequestMethod.POST)
    @ResponseBody
    public ListResponse<UserResponse> getUsersByGroup(@RequestParam String group) {
        List<User> usersByGroup = userService.findUsersByGroup(group);
        return new ListResponse<>(transform(usersByGroup, USER_TO_USER_RESPONSE));
    }

    @ApiImplicitParams(value = {@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "string", paramType = "query")})
    @ApiOperation(httpMethod = "POST", value = "Fid all users in group")
    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    @ResponseBody
    public ListResponse<GroupResponse> getAllGroups() {
        return new ListResponse<>(transform(userService.getAllGroups(), GROUP_TO_GROUP_RESPONSE));
    }
}
