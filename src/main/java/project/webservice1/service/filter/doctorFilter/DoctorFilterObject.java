package project.webservice1.service.filter.doctorFilter;

import lombok.Data;
import project.webservice1.model.Specialty;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class DoctorFilterObject {
    private String name;
    private Specialty specialty;
    private LocalDate day;
    private LocalTime startHour;
    private LocalTime endHour;
}
