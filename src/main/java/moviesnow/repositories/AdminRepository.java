package moviesnow.repositories;

import org.springframework.data.repository.CrudRepository;

import moviesnow.models.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

}
