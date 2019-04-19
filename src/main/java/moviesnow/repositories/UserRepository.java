package moviesnow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import moviesnow.models.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	@Query("Select user from User user where user.username=:username")
	public User findByUsername(@Param("username") String username);
	
	@Query("Select user from User user where user.username=:username and user.password=:password")
	public User findByCredentials(@Param("username") String username, @Param("password") String password);
	
	@Query("Select user from User user where user.id<>:userId")
	public List<User> returnUsersToFollow(@Param("userId") int userId);
}
