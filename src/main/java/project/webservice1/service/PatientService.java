package project.webservice1.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.webservice1.model.Patient;
import project.webservice1.repository.PatientRepo;
import project.webservice1.service.filter.patientFilter.PatientObjectFilter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientService implements PatientServiceI{
    public PatientRepo patientRepo;
    private PatientFilterService patientFilterService;

    public PatientService(PatientRepo patientRepo, PatientFilterService patientFilterService) {
        this.patientRepo = patientRepo;
        this.patientFilterService = patientFilterService;
    }

    @Override
    public Set<Patient> getAllPatients() {
        Set<Patient> patients = new HashSet<>();
        for (Patient patient:this.patientRepo.findAll()){
            patients.add(patient);
        }
        return patients;
    }

    @Override
    public Set<Patient> getFilteredPatient(PatientObjectFilter filterObjectPaciente) {
        return patientFilterService.filterPatient(getAllPatients(), filterObjectPaciente);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepo.findById(id);
    }

    @Override
    public Optional<Patient> findByPatientID(Integer patientID) {
        return patientRepo.findByPatientID(patientID);
    }

    @Override
    public Patient save(Patient patient) {
        return this.patientRepo.save(patient);
    }

    @Override
    public ResponseEntity<Patient> savePatient(Patient patient) {
        patientRepo.save(patient);
        return ResponseEntity.notFound().build();
    }

    @Override
    public Optional<Patient> removePatient(Integer patientID) {
        Optional<Patient> patientOptional = this.patientRepo.findByPatientID(patientID);
        if (patientOptional.isPresent()){
            Patient patient = patientOptional.get();
            patientRepo.delete(patient);
            return patientRepo.findByPatientID(patientID);
        }
        return Optional.empty();
    }

}
