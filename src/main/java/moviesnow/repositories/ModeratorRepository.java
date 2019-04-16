package moviesnow.repositories;

import org.springframework.data.repository.CrudRepository;

import moviesnow.models.Moderator;

public interface ModeratorRepository extends CrudRepository<Moderator, Integer>{
}
