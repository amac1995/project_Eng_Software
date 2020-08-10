package project.webservice1.service;

import org.springframework.stereotype.Service;
import project.webservice1.model.Appointment;
import project.webservice1.service.filter.AndFilter;
import project.webservice1.service.filter.FilterI;
import project.webservice1.service.filter.appointmentFilter.*;

import java.util.Set;

@Service
public class AppointmentFilterService {
    public Set<Appointment> filterAppointments(Set<Appointment> appointments, AppointmentObjectFilter appointmentObjectFilter) {
        System.out.println(appointmentObjectFilter);
        FilterI<Appointment> appointmentDoctorFIlter = new AppointmentDoctorFIlter(appointmentObjectFilter.getDoctor());
        FilterI<Appointment> appointmetPatientIDFilter = new AppointmentPatientIDFilter(appointmentObjectFilter.getPatient().patientID);
        FilterI<Appointment> doctorAndPatientIDFilter = new AndFilter<>(appointmentDoctorFIlter, appointmetPatientIDFilter);
        FilterI<Appointment> appointmentSpecialtyFilter = new AppointmentSpecialtyFilter(appointmentObjectFilter.getSpecialty());
        FilterI<Appointment> doctorAndPatientIDAndSpecialtyFilter = new AndFilter<>(doctorAndPatientIDFilter, appointmentSpecialtyFilter);
        FilterI<Appointment> appointmentDayFilter = new AppointmentDayFilter(appointmentObjectFilter.getDayOfWeek());
        FilterI<Appointment> doctorAndPatientIDAndSpecialtyAndDayFilter = new AndFilter<>(doctorAndPatientIDAndSpecialtyFilter, appointmentDayFilter);
        FilterI<Appointment> appointmentHourFilter = new AppointmentHourFilter(appointmentObjectFilter.getHour());
        FilterI<Appointment> doctorAndPatientIDAndSpecialtyAndDayAndHourFilter = new AndFilter<>(doctorAndPatientIDAndSpecialtyAndDayFilter, appointmentHourFilter);
        return doctorAndPatientIDAndSpecialtyAndDayAndHourFilter.filter(appointments);
    }
}
