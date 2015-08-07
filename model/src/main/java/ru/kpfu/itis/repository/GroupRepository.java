package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.AcademicGroup;

/**
 * Created by sanekesv on 01.08.15.
 */
@Transactional
public interface GroupRepository extends JpaRepository<AcademicGroup, Long> {
    AcademicGroup findOneById(Long id);

    AcademicGroup findOneByTitle(String title);

}
