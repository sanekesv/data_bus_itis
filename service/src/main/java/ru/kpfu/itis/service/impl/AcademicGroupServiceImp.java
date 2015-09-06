package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.AcademicGroup;
import ru.kpfu.itis.repository.GroupRepository;
import ru.kpfu.itis.service.AcademicGroupService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sanekesv on 05.09.15.
 */
@Service("AcademicGroupService")
@Transactional
public class AcademicGroupServiceImp implements AcademicGroupService {

    @Autowired
    GroupRepository groupRepository;

    @Override
    public List<AcademicGroup> getAll() {
        List < AcademicGroup > groupList = (List<AcademicGroup>) groupRepository.findAll();
        for (AcademicGroup group : groupList){
            group.getUsers().size();
        }
        return groupList;
    }
}
