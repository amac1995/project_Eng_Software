package project.webservice1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.webservice1.model.Patient;

import java.util.Optional;

@Repository
public interface PatientRepo extends CrudRepository<Patient, Long> {
    Optional<Patient> findByPatientID(Integer patientID);
}
