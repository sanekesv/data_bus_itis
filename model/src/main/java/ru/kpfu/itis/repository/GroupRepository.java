package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.AcademicGroup;

/**
 * Created by sanekesv on 01.08.15.
 */
@Repository
public interface GroupRepository extends CrudRepository<AcademicGroup, Long> {
    AcademicGroup findOneById(Long id);

    AcademicGroup findOneByTitle(String title);

}
