package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    User findOneByLogin(String login);

    @Query("SELECT u.login FROM User u")
    List<String> getAllUserLogins();
}
