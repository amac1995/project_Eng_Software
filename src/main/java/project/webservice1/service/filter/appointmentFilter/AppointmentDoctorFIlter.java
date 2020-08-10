package project.webservice1.service.filter.appointmentFilter;

import project.webservice1.model.Appointment;
import project.webservice1.model.Doctor;

import java.util.Set;
import java.util.stream.Collectors;

public class AppointmentDoctorFIlter implements AppointmentFilter{
    private Doctor doctor;

    public AppointmentDoctorFIlter(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public Set<Appointment> filter(Set<Appointment> appointments) {
        if (doctor==null)return appointments;
        return appointments.stream().filter(appointment -> appointment.getDoctor().equals(this.doctor)).collect(Collectors.toSet());
    }
}
