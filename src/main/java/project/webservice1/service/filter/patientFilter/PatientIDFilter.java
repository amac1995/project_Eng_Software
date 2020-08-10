package project.webservice1.service.filter.patientFilter;

import project.webservice1.model.Patient;

import java.util.Set;
import java.util.stream.Collectors;

public class PatientIDFilter implements PatientFilter{
    private Integer patientID;

    public PatientIDFilter(Integer patientID) {
        this.patientID = patientID;
    }

    @Override
    public Set<Patient> filter(Set<Patient> patients) {
        if (patientID==null)return patients;
        return patients.stream().filter(patient -> patient.getPatientID().equals(this.patientID)).collect(Collectors.toSet());
    }
}
