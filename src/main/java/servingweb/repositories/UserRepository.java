package servingweb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import servingweb.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User getUserByUsername(String username);

}

