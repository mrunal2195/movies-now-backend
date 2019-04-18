package moviesnow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@PostMapping("/api/user/{userId}/movie")
	public FavouriteMovie likeMovie(@PathVariable("userId") int userId, @RequestBody FavouriteMovie movie) {
		User user = userRepository.findById(userId).get();
		movie.setUser(user);
		movieRepository.save(movie);
		return movie;
		
	}
	
	@GetMapping("/api/user/{userId}/movie")
	public List<FavouriteMovie> getLikedMovies(@PathVariable("userId") int userId){
		User user = userRepository.findById(userId).get();
		return user.getMovies();
	}
}
