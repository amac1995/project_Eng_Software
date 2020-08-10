package project.webservice1.service.filter.appointmentFilter;

import project.webservice1.model.Appointment;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.stream.Collectors;

public class AppointmentDayFilter implements AppointmentFilter{
    private DayOfWeek dayOfWeek;

    public AppointmentDayFilter(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public Set<Appointment> filter(Set<Appointment> appointments) {
        if (dayOfWeek==null) return appointments;
        return appointments.stream().filter(appointment -> appointment.getLocalDateTime().getDayOfWeek().equals(this.dayOfWeek)).collect(Collectors.toSet());
    }
}
