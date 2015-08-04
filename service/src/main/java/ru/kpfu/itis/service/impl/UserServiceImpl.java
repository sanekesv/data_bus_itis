package ru.kpfu.itis.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.AcademicGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.RoleEnum;
import ru.kpfu.itis.model.enums.UserGroup;
import ru.kpfu.itis.model.form.RegistrationForm;
import ru.kpfu.itis.repository.GroupRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.UserService;
import ru.kpfu.itis.util.FormMappers;
import ru.kpfu.jbl.auth.domain.AuthUser;
import ru.kpfu.jbl.auth.response.UserResponse;

import java.util.List;


@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Override
    public User findUserByLogin(String login) {
        User user = userRepository.findOneByLogin(login);
        if (user.getRole() == null || user.getGroup() == null) {
            user.setRole(RoleEnum.STUDENT);
            user.setGroup(UserGroup.STUDENT);
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public AuthUser saveUser(UserResponse userResponse) {
        //not implemented here: because of server
        return null;
    }

    @Override
    public Boolean matchPasswords(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUser(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public Long register(RegistrationForm form) {
        User user = userRepository.findOneByLogin(form.getLogin());
        if (user == null) {
            User newUser = FormMappers.mapRegistrationForm(form);
            AcademicGroup academicGroup = groupRepository.findOneByTitle(form.getAcademicGroup());
            newUser.setAcademicGroup(academicGroup);
            newUser = userRepository.save(newUser);
            return newUser.getId();
        }
        return null;
    }

    @Override
    public List<User> findUsersByGroup(String groupName) {
        AcademicGroup academicGroup = null;
        try {
            academicGroup = groupRepository.findOneByTitle(groupName);
        } catch (Exception ignored) {
        }
        if (academicGroup == null) {
            return null;
        }
        return academicGroup.getUsers();
    }

    @Override
    public List<AcademicGroup> getAllGroups() {
        return Lists.newArrayList(groupRepository.findAll());
    }
}
