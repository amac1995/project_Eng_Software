package project.webservice1.service;

import org.springframework.stereotype.Service;
import project.webservice1.model.Appointment;
import project.webservice1.model.Doctor;
import project.webservice1.model.Patient;
import project.webservice1.repository.AppointmentRepo;
import project.webservice1.repository.DoctorRepo;
import project.webservice1.repository.PatientRepo;
import project.webservice1.service.filter.appointmentFilter.AppointmentObjectFilter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppointmentService implements AppointmentServiceI{
    private AppointmentFilterService appointmentFilterService;
    private AppointmentRepo appointmentRepo;
    private DoctorRepo doctorRepo;
    private PatientRepo patientRepo;

    public AppointmentService(AppointmentRepo appointmentRepo, DoctorRepo doctorRepo, PatientRepo patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    @Override
    public Set<Appointment> getAllApointments(){
        Set<Appointment> appointments = new HashSet<>();
        for (Appointment appointment:this.appointmentRepo.findAll()){
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public Set<Appointment> getFilteredAppointment(AppointmentObjectFilter appointmentObjectFilter) {
        return appointmentFilterService.filterAppointments(getAllApointments(), appointmentObjectFilter);
    }


    @Override
    public Optional<Appointment> findByID(Long id){
        return appointmentRepo.findById(id);
    }

    @Override
    public Optional<Doctor> findByName(String doctorName) {
        return doctorRepo.findByName(doctorName);
    }

    @Override
    public Optional<Patient> findByPatientID(Integer patientID) {
        return patientRepo.findByPatientID(patientID);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepo.save(appointment);
    }


    @Override
    public Optional<Appointment> saveAppointment(Appointment appointment, Integer patientID, String doctorName) {
        Optional<Patient> patientOptional = this.patientRepo.findByPatientID(patientID);
        Optional<Doctor> doctorOptional = this.doctorRepo.findByName(doctorName);
        if (patientOptional.isPresent() && doctorOptional.isPresent()){
            Patient patient = patientOptional.get();
            Doctor doctor = doctorOptional.get();
            if(doctor.addAppointmentDoctor(appointment)){
                patient.addAppointment(appointment);
                patientRepo.save(patient);
                doctorRepo.save(doctor);
                appointmentRepo.save(appointment);
                return appointmentRepo.findById(appointment.getId());
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Appointment> deleteAppointment(Long id){
        Optional<Appointment> appointmentOptional = this.appointmentRepo.findById(id);
        if (appointmentOptional.isPresent()){
            Appointment appointment = appointmentOptional.get();
            appointmentRepo.delete(appointment);
            return appointmentRepo.findById(id);
        }
        return Optional.empty();
    }
}
