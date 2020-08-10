package project.webservice1.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.webservice1.service.filter.doctorFilter.DoctorFilterObject;
import project.webservice1.model.Doctor;
import project.webservice1.repository.DoctorRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DoctorService implements DoctorServiceI{
    public DoctorRepo doctorRepo;
    public DoctorFilterService doctorFilterService;

    public DoctorService(DoctorRepo doctorRepo, DoctorFilterService doctorFilterService) {
        this.doctorRepo = doctorRepo;
        this.doctorFilterService = doctorFilterService;
    }

    @Override
    public Set<Doctor> getAllDoctors() {
        Set<Doctor> doctors = new HashSet<>();
        for (Doctor doctor:this.doctorRepo.findAll()){
            doctors.add(doctor);
        }
        return doctors;
    }

    @Override
    public Doctor getDoctorByName(String name) {
        for (Doctor doctor:getAllDoctors()){
            if(doctor.getName().equalsIgnoreCase(name)){
                return doctor;
            }
        }
        return null;
    }

    @Override
    public Set<Doctor> getFilteredDoctors(DoctorFilterObject doctorFilterObject) {
        return doctorFilterService.filterDoctor(getAllDoctors(), doctorFilterObject);
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepo.findById(id);
    }

    @Override
    public Optional<Doctor> findByName(String doctorName) {
        return doctorRepo.findByName(doctorName);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    @Override
    public ResponseEntity<Doctor> saveDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<Doctor> removeDoctor(String doctorName) {
        Optional<Doctor> doctorOptional = this.doctorRepo.findByName(doctorName);
        if (doctorOptional.isPresent()){
            Doctor doctor = doctorOptional.get();
            doctorRepo.delete(doctor);
            return doctorRepo.findByName(doctor.getName());
        }
        return Optional.empty();
    }

}
