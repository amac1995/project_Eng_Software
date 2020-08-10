package project.webservice1.service.filter.appointmentFilter;

import project.webservice1.model.Appointment;

import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;

public class AppointmentHourFilter implements AppointmentFilter{
    private LocalTime localTime;

    public AppointmentHourFilter(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override
    public Set<Appointment> filter(Set<Appointment> appointments) {
        if (localTime==null)return appointments;
        return appointments.stream().filter(appointment -> appointment.getLocalDateTime().toLocalTime().equals(this.localTime)).collect(Collectors.toSet());
    }
}
