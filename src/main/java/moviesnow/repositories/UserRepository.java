package moviesnow.repositories;

import org.springframework.data.repository.CrudRepository;
import moviesnow.entities.User;

public interface UserRepository extends CrudRepository<User, Integer>{
}
