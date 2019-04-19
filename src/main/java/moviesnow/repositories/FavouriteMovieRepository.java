package moviesnow.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import moviesnow.models.FavouriteMovie;

public interface FavouriteMovieRepository extends CrudRepository<FavouriteMovie, Integer>{
	
	@Query("Select movie from FavouriteMovie movie where movie.imdbid=:imdbId and movie.user.id=:userId")
	public FavouriteMovie findMovieByImdbId(@Param("imdbId") String id, @Param("userId") int userId);

}
