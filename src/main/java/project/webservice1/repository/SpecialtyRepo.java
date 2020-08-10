package project.webservice1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.webservice1.model.Specialty;

import java.util.Optional;

@Repository
public interface SpecialtyRepo extends CrudRepository<Specialty, Long> {
    Optional<Specialty> findByName(String name);
}
