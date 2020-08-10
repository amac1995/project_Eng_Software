package project.webservice1.service.filter.appointmentFilter;

import lombok.Data;
import project.webservice1.model.Doctor;
import project.webservice1.model.Patient;
import project.webservice1.model.Specialty;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
public class AppointmentObjectFilter {
    private Patient patient;
    private Specialty specialty;
    private DayOfWeek dayOfWeek;
    private LocalTime hour;
    private Doctor doctor;

}
