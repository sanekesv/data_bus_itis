package ru.kpfu.itis.administration.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import ru.kpfu.itis.model.AcademicGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.service.UserService;

import java.util.List;

/**
 * Created by sanekesv on 06.09.15.
 */
public class AcademicGroupEventListener extends AbstractRepositoryEventListener<AcademicGroup> {

    @Autowired
    UserService userService;

    @Override
    public void onBeforeSave(AcademicGroup academicGroup){
        List<User> users = academicGroup.getUsers();
        for(User user : users){
            user.setAcademicGroup(academicGroup);
            userService.saveUser(user);
        }
    }

    @Override
    public void onAfterCreate(AcademicGroup academicGroup){
        List<User> users = academicGroup.getUsers();
        for(User user : users){
            user.setAcademicGroup(academicGroup);
            userService.saveUser(user);
        }
    }

    @Override
    public void onBeforeDelete(AcademicGroup academicGroup){
        List<User> users = academicGroup.getUsers();
        for(User user : users){
            user.setAcademicGroup(null);
            userService.saveUser(user);
        }
        academicGroup.setUsers(null);
    }

}
