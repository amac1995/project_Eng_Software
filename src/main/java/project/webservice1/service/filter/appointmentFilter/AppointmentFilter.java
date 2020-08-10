package project.webservice1.service.filter.appointmentFilter;

import project.webservice1.service.filter.FilterI;
import project.webservice1.model.Appointment;

import java.util.Set;

public interface AppointmentFilter extends FilterI<Appointment> {
    Set<Appointment> filter(Set<Appointment> appointments);
}
