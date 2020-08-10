package project.webservice1.service;

import project.webservice1.model.Appointment;
import project.webservice1.model.Doctor;
import project.webservice1.model.Patient;
import project.webservice1.service.filter.appointmentFilter.AppointmentObjectFilter;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface AppointmentServiceI {
    Set<Appointment> getAllApointments();
    Set<Appointment> getFilteredAppointment(AppointmentObjectFilter appointmentObjectFilter);
    Optional<Appointment> findByID(Long id);
    Optional<Doctor> findByName(String doctorName);
    Optional<Patient> findByPatientID(Integer patientID);
    Appointment save(Appointment appointment);
    Optional<Appointment> saveAppointment(Appointment appointment, Integer patientID, String doctorName);
    Optional<Appointment> deleteAppointment(Long id);


}
