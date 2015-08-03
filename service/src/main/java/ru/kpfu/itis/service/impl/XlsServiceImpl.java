package ru.kpfu.itis.service.impl;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.model.AcademicGroup;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.UserGroup;
import ru.kpfu.itis.repository.GroupRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.XlsService;
import ru.kpfu.itis.util.CommonUtil;
import ru.kpfu.itis.util.XlsUtil;
import ru.kpfu.jbl.auth.util.PasswordHelper;

import java.util.*;

@Service
public class XlsServiceImpl implements XlsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GroupRepository groupRepository;

    @Transactional
    @Override
    public boolean saveUsers(MultipartFile file) {
        final List<String> allUserLogins = userRepository.getAllUserLogins();
        List<User> unregisteredUsers = new ArrayList<>();
        try {

            Workbook workbook = XlsUtil.getWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Cell nameCell = row.getCell(0);
                Cell groupCell = row.getCell(1);
                Cell yearCell = row.getCell(2);
                String name = XlsUtil.readCell(nameCell);
                String group = XlsUtil.readCell(groupCell);
                String year = XlsUtil.readCell(yearCell);
                if ("фио".equalsIgnoreCase(name) || name==null) {
                    //ignore first row if exists
                    continue;
                }
                User user = new User();
                user.setName(name);
                AcademicGroup findAcademicGroup = groupRepository.findOneByTitle(group);
                if(findAcademicGroup ==null) {
                    AcademicGroup savingAcademicGroup = new AcademicGroup();
                    savingAcademicGroup.setTitle(group);
                    findAcademicGroup = groupRepository.save(savingAcademicGroup);
                }
                user.setAcademicGroup(findAcademicGroup);
                try {
                    user.setEntranceYear(Long.valueOf(year));
                } catch (Exception e) {//ignored
                }
                user.setLogin(CommonUtil.generateLogin(user.getName()));
                user.setSalt(PasswordHelper.generateSalt());
                String unencrypted = PasswordHelper.generatePassword();
                String password = PasswordHelper.encryptForGenerated(unencrypted, user.getSalt());
                user.setPassword(password);
                user.setGroup(UserGroup.STUDENT);
                unregisteredUsers.add(user);
            }
        } catch (Exception e) {
            return false;
        }
/*        Iterable<User> dublicatedLogins = Iterables.filter(unregisteredUsers, new Predicate<User>() {
            @Override
            public boolean apply(final User user) {

                return Iterables.any(allUserLogins, new Predicate<String>() {
                    @Override
                    public boolean apply(String s) {
                        return s.equals(user.getLogin());
                    }
                });
            }
        });*/
        HashMap<String, List<User>> usersMap = new HashMap<>();
        for (User user : unregisteredUsers) {
            List<User> userList = Optional.fromNullable(usersMap.get(user.getLogin())).or(new ArrayList<User>());
            userList.add(user);
            usersMap.put(user.getLogin(), userList);
        }
        for (final Map.Entry<String, List<User>> entry : usersMap.entrySet()) {
            List<User> usersList = entry.getValue();
            boolean loginExistsInDB = Iterables.any(allUserLogins, new Predicate<String>() {
                @Override
                public boolean apply(String s) {
                    return s.equals(entry.getKey());
                }
            });
            if (usersList.size() > 1 || loginExistsInDB) {
                for (int i = 0; i < usersList.size(); i++) {
                    User u = usersList.get(i);
                    u.setLogin(u.getLogin() + "" + i);
                }
            }
        }
        userRepository.save(unregisteredUsers);
        return true;
    }


}
