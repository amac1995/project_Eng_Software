package project.webservice1.service.filter.patientFilter;

import project.webservice1.model.Patient;

import java.util.Set;
import java.util.stream.Collectors;

public class PatientNameFilter implements PatientFilter{
    private String name;

    public PatientNameFilter(String name) {
        this.name = name;
    }

    @Override
    public Set<Patient> filter(Set<Patient> patients) {
        if(name==null) return patients;
        return patients.stream().filter(patient -> patient.getName().equalsIgnoreCase(this.name)).collect(Collectors.toSet());
    }
}
