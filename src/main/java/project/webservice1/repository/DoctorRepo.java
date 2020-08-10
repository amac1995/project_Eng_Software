package project.webservice1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.webservice1.model.Doctor;

import java.util.Optional;

@Repository
public interface DoctorRepo extends CrudRepository<Doctor, Long> {
    Optional<Doctor> findByName(String name);
}
