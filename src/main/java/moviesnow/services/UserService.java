package moviesnow.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import moviesnow.models.User;
import moviesnow.repositories.ModeratorRepository;
import moviesnow.repositories.UserRepository;
import moviesnow.repositories.ViewerRepository;

@RestController
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModeratorRepository moderatorRepository;
	@Autowired
	private ViewerRepository viewerRepository;
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpSession session) {
		User findUser = (User) userRepository.findByUsername(user.getUsername());
		if(findUser != null) {
			return null;
		}else {
			userRepository.save(user);
			session.setAttribute("currentUser", user);
			return user;
		}
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User user =  (User) session.getAttribute("currentUser");
		return user;
	}
	
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpSession session) {
		User findUser = userRepository.findByCredentials(user.getUsername(), user.getPassword());
		if(findUser != null) {
			session.setAttribute("currentUser", findUser);
			return findUser;
		}
		return null;	
	}
	
	@PutMapping("/api/update")
	public User update(@RequestBody User user, HttpSession session) {
		User findUser = userRepository.findById(user.getId()).get();
		findUser.setFirstname(user.getFirstname());
		findUser.setLastname(user.getLastname());
		findUser.setPassword(user.getPassword());
		findUser.setEmail(user.getEmail());
		userRepository.save(findUser);
		session.setAttribute("currentUser", findUser);
		return findUser;
	}
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("currentUser");
	}
	
	@GetMapping("/api/users")
	public List<User> findAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	@GetMapping("/api/users/{userId}")
	public User findUserById(@PathVariable("userId") int id) {
		return userRepository.findById(id).get();
	}
	
	@GetMapping("/api/users/{userId}/findPoeple")
	public List<User> returnUsersToFollow(@PathVariable("userId") int userId){
		List<User> usersToFollow = userRepository.returnUsersToFollow(userId);
		return usersToFollow;
	}
	
	@PostMapping("/api/users/{follwer}/follow/{followee}")
	public User followUser(@PathVariable("follwer") int followerId, @PathVariable("followee") int followeeId) {
		User follower = userRepository.findById(followerId).get();
		User followee = userRepository.findById(followeeId).get();
		follower.addFollows(followee);
		userRepository.save(follower);
		userRepository.save(followee);
		return followee;
	}
	
	@DeleteMapping("/api/users/{follwer}/unfollow/{followee}")
	public User unfollowUser(@PathVariable("follwer") int followerId, @PathVariable("followee") int followeeId) {
		User follower = userRepository.findById(followerId).get();
		User followee = userRepository.findById(followeeId).get();
		follower.removeFollows(followee);
		userRepository.save(follower);
		userRepository.save(followee);
		return followee;
	}

	@DeleteMapping("/api/users/{user}")
	public void deleteUser(@PathVariable("user") int userId) {
		userRepository.deleteById(userId);
	}
	
	@GetMapping("/api/users/{follower}/follow")
	public List<User> getFollwedPeople(@PathVariable("follower") int followerId){
		User follower = userRepository.findById(followerId).get();
		return follower.getFollows();
	}
	
	
}
