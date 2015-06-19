package repository;

import model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Александр on 07.02.2015.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

    User findOneByLogin(String login);
}
