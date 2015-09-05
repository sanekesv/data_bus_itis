package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.AcademicGroup;

import java.util.List;

/**
 * Created by sanekesv on 01.08.15.
 */
@Repository
public interface GroupRepository extends CrudRepository<AcademicGroup, Long> {
    AcademicGroup findOneById(Long id);

    AcademicGroup findOneByTitle(String title);

//    @Query("SELECT * FROM AcademicGroup AS g WHERE id>0")
//    List<AcademicGroup> getAllGroupsNameObj();
}
