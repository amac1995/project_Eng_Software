package project.webservice1.service;

import project.webservice1.model.Doctor;
import project.webservice1.model.Specialty;
import project.webservice1.service.filter.specialtyFilter.SpecialtyObjectFilter;

import java.util.Optional;
import java.util.Set;

public interface SpecialtyServiceI {

    Set<Specialty> getAllSpecialty();

    Set<Specialty> getFilteredSpecialty(SpecialtyObjectFilter specialtyObjectFilter);

    Optional<Specialty> findById(Long id);

    Optional<Specialty> findByName(String specialtyName);

    Optional<Doctor> findByDoctor(String doctorName);

    Set<Specialty> findAll();

    Specialty save(Specialty specialty);

    Optional<Specialty> saveSpecialty(Specialty specialty, String doctorName);

    Optional<Specialty> removeSpecialty(String specialtyName);

}
