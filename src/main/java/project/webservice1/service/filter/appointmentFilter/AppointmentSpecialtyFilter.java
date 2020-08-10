package project.webservice1.service.filter.appointmentFilter;

import project.webservice1.model.Appointment;
import project.webservice1.model.Specialty;

import java.util.Set;
import java.util.stream.Collectors;

public class AppointmentSpecialtyFilter implements AppointmentFilter{
    private Specialty specialty;

    public AppointmentSpecialtyFilter(Specialty specialty) {
        this.specialty = specialty;
    }

    @Override
    public Set<Appointment> filter(Set<Appointment> appointments) {
        if (specialty == null)return appointments;
        return appointments.stream().filter(appointment -> appointment.getSpecialty().equals(this.specialty)).collect(Collectors.toSet());
    }
}
