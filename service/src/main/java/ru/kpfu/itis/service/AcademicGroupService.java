package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.AcademicGroup;

import java.util.List;


/**
 * Created by sanekesv on 05.09.15.
 */
@Service
public interface AcademicGroupService {
    List<AcademicGroup> getAll();
}
