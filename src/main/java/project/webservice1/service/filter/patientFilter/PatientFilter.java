package project.webservice1.service.filter.patientFilter;

import project.webservice1.service.filter.FilterI;
import project.webservice1.model.Patient;

import java.util.Set;

public interface PatientFilter extends FilterI<Patient> {
    Set<Patient> filter(Set<Patient> patients);
}
