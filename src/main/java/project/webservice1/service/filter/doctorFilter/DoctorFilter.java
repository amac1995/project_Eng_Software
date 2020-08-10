package project.webservice1.service.filter.doctorFilter;

import project.webservice1.service.filter.FilterI;
import project.webservice1.model.Doctor;

import java.util.Set;

public interface DoctorFilter extends FilterI<Doctor> {
    Set<Doctor> filter(Set<Doctor> entities);
}
