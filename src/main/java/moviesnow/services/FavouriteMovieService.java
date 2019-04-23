package moviesnow.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import moviesnow.models.FavouriteMovie;
import moviesnow.models.User;
import moviesnow.repositories.FavouriteMovieRepository;
import moviesnow.repositories.UserRepository;

@RestController
public class FavouriteMovieService {

	
	@Autowired
	private FavouriteMovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/api/users/{userId}/movie")
	public FavouriteMovie likeMovie(@PathVariable("userId") int userId, @RequestBody FavouriteMovie movie) {
		User user = userRepository.findById(userId).get();
		String movieId = movie.getImdbid();
		FavouriteMovie movie1 = movieRepository.findMovieByImdbId(movieId, userId);
		if(movie1!=null) {
			return null;
		}
		movie.setUser(user);
		movieRepository.save(movie);
		return movie;
	}
	
	@DeleteMapping("/api/users/{userId}/movie/{movieId}")
	public FavouriteMovie unlikeMovie(@PathVariable("userId") int userId, @PathVariable("movieId") String movieId) {
		FavouriteMovie movie = movieRepository.findMovieByImdbId(movieId, userId);
		movieRepository.delete(movie);
		return movie;
	}
	
	@GetMapping("/api/users/{userId}/movie")
	public List<FavouriteMovie> getLikedMovies(@PathVariable("userId") int userId){
		User user = userRepository.findById(userId).get();
		return user.getMovies();
	}
	
	@GetMapping("/api/users/{userId}/followermovies")
	public Map<String, List<FavouriteMovie>> moviesofFollowedUsers(@PathVariable("userId") int id){
		Map<String, List<FavouriteMovie>> userMovies = new HashMap<>();
		List<User> users = userService.getFollwedPeople(id);
		for (User user : users) {
			List<FavouriteMovie> movies = getLikedMovies(user.getId());
			if(!movies.isEmpty())
			userMovies.put(user.getFirstname(), movies);
		}
		return userMovies;
	}
	
	@GetMapping("/api/movie/recentlyliked")
	public List<FavouriteMovie> getRecentlyLikedMovies() {
		List<FavouriteMovie> likedMovies = movieRepository.getRecentlyLikedMovie(new PageRequest(0, 10));
		return likedMovies;
	}
}
