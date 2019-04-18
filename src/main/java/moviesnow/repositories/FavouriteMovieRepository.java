package moviesnow.repositories;

import org.springframework.data.repository.CrudRepository;

import moviesnow.models.FavouriteMovie;

public interface FavouriteMovieRepository extends CrudRepository<FavouriteMovie, Integer>{

}
