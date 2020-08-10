package project.webservice1.service;

import org.springframework.http.ResponseEntity;
import project.webservice1.model.Doctor;
import project.webservice1.service.filter.doctorFilter.DoctorFilterObject;

import java.util.Optional;
import java.util.Set;

public interface DoctorServiceI {
    Set<Doctor> getAllDoctors();

    Doctor getDoctorByName(String name);

    Set<Doctor> getFilteredDoctors(DoctorFilterObject doctorFilterObject);

    Optional<Doctor> findById(Long id);

    Optional<Doctor> findByName(String doctorName);

    Doctor save(Doctor doctor);

    ResponseEntity<Doctor> saveDoctor(Doctor doctor);

    Optional<Doctor> removeDoctor(String doctorName);
}
