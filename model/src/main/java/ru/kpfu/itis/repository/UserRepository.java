package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.model.User;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLogin(String login);

    @Query("SELECT u.login FROM User u")
    List<String> getAllUserLogins();
}
