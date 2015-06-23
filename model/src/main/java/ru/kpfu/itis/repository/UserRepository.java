package ru.kpfu.itis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    User findOneByLogin(String login);
}
