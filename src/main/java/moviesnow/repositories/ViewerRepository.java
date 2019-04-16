package moviesnow.repositories;

import org.springframework.data.repository.CrudRepository;

import moviesnow.models.Viewer;


public interface ViewerRepository extends CrudRepository<Viewer, Integer> {
}
