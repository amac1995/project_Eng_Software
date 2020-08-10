package project.webservice1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.webservice1.model.Appointment;

import java.util.Optional;

@Repository
public interface AppointmentRepo extends CrudRepository<Appointment, Long> {

}
