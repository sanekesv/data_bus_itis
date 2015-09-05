package ru.kpfu.itis.util;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import ru.kpfu.itis.model.AcademicGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.jbl.auth.response.GroupResponse;
import ru.kpfu.jbl.auth.response.UserResponse;

import java.util.List;

/**
 * Created by sanekesv on 02.08.15.
 */
public class DtoMappers {

    public static UserResponse userToUserDto(User user) {
        return USER_TO_USER_RESPONSE.apply(user);
    }

    public static <T, K> List<K> transform(List<T> items, Function<? super T, ? extends K> transformer) {
        return Lists.newArrayList(Iterables.transform(items, transformer));
    }


    public static final Function<AcademicGroup, GroupResponse> GROUP_TO_GROUP_RESPONSE = new Function<AcademicGroup, GroupResponse>() {
        @Override
        public GroupResponse apply(AcademicGroup academicGroup) {
            GroupResponse groupResponse = new GroupResponse();
            groupResponse.setId(academicGroup.getId());
            groupResponse.setTitle(academicGroup.getTitle());
            return groupResponse;
        }
    };

    public static final Function<User, UserResponse> USER_TO_USER_RESPONSE = new Function<User, UserResponse>() {

        @Override
        public UserResponse apply(User user) {
            UserResponse userDto = new UserResponse();
            if (user.getAcademicGroup() != null) {
                userDto.setAcademicGroup(user.getAcademicGroup().getId());
            }
            userDto.setEntranceYear(user.getEntranceYear());
            userDto.setId(user.getId());
            userDto.setLogin(user.getLogin());
            userDto.setFaculty(-1l);
            userDto.setName(user.getName());
            return userDto;
        }
    };
}
