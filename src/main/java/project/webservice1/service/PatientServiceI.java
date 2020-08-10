package project.webservice1.service;

import org.springframework.http.ResponseEntity;
import project.webservice1.model.Patient;
import project.webservice1.service.filter.patientFilter.PatientObjectFilter;

import java.util.Optional;
import java.util.Set;

public interface PatientServiceI {
    Set<Patient> getAllPatients();

    Set<Patient> getFilteredPatient(PatientObjectFilter filterObjectPaciente);


    Optional<Patient> findById(Long id);

    Optional<Patient> findByPatientID(Integer patientID);

    Patient save(Patient patient);

    ResponseEntity<Patient> savePatient (Patient patient);

    Optional<Patient> removePatient (Integer patientID);
}
