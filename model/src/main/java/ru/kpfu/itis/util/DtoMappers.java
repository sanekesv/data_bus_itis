package ru.kpfu.itis.util;

import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.model.User;

/**
 * Created by sanekesv on 02.08.15.
 */
public class DtoMappers {

    public static UserDto userToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setAcademicGroup(user.getAcademicGroup().getTitle());
        userDto.setEntranceYear(user.getEntranceYear());
        userDto.setGender(user.getGender());
        userDto.setGroup(user.getGroup());
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setId(user.getId());
        return userDto;
    }
}
