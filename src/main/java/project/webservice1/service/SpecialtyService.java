package project.webservice1.service;

import org.springframework.stereotype.Service;
import project.webservice1.model.Doctor;
import project.webservice1.model.Specialty;
import project.webservice1.repository.DoctorRepo;
import project.webservice1.repository.SpecialtyRepo;
import project.webservice1.service.filter.specialtyFilter.SpecialtyObjectFilter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SpecialtyService implements SpecialtyServiceI{
    private SpecialtyFilterService specialtyFilterService;
    private SpecialtyRepo specialtyRepo;
    private DoctorRepo doctorRepo;

    public SpecialtyService(SpecialtyFilterService specialtyFilterService, SpecialtyRepo specialtyRepo, DoctorRepo doctorRepo) {
        this.specialtyFilterService = specialtyFilterService;
        this.specialtyRepo = specialtyRepo;
        this.doctorRepo = doctorRepo;
    }


    @Override
    public Set<Specialty> getAllSpecialty() {
        Set<Specialty> specialties = new HashSet<>();
        for (Specialty specialty : this.specialtyRepo.findAll()) {
            specialties.add(specialty);
        }
        return specialties;
    }

    @Override
    public Set<Specialty> getFilteredSpecialty(SpecialtyObjectFilter specialtyObjectFilter) {
        return specialtyFilterService.specialtyFilter(findAll(), specialtyObjectFilter);
    }

    @Override
    public Optional<Specialty> findById(Long id) {
        return this.specialtyRepo.findById(id);
    }

    @Override
    public Optional<Specialty> findByName(String specialtyName) {
        return this.specialtyRepo.findByName(specialtyName);
    }

    @Override
    public Optional<Doctor> findByDoctor(String doctorName) {
        return doctorRepo.findByName(doctorName);
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        for (Specialty specialty : this.specialtyRepo.findAll()) {
            specialties.add(specialty);
        }
        return specialties;
    }

    @Override
    public Specialty save(Specialty specialty) {
        return this.specialtyRepo.save(specialty);
    }

    @Override
    public Optional<Specialty> saveSpecialty(Specialty specialty, String doctorName) {
        Optional<Doctor> doctorOptional = this.doctorRepo.findByName(doctorName);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();

            doctor.addSpecialty(specialty);
            doctorRepo.save(doctor);
            return specialtyRepo.findByName(specialty.getName());
        }
        return Optional.of(specialty);
    }

    @Override
    public Optional<Specialty> removeSpecialty(String specialtyName) {
        Optional<Specialty> specialtyOptional = this.specialtyRepo.findByName(specialtyName);
        if (specialtyOptional.isPresent()) {
            Specialty specialty = specialtyOptional.get();

            Doctor doctor=specialty.getDoctor();
            doctor.removeSpecialty(specialty);
            doctorRepo.save(doctor);
            specialtyRepo.delete(specialty);
            return specialtyRepo.findByName(specialty.getName());
        }
        return Optional.empty();
    }
}
