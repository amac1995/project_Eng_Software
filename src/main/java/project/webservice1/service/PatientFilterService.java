package project.webservice1.service;

import org.springframework.stereotype.Service;
import project.webservice1.model.Patient;
import project.webservice1.service.filter.AndFilter;
import project.webservice1.service.filter.FilterI;
import project.webservice1.service.filter.patientFilter.PatientIDFilter;
import project.webservice1.service.filter.patientFilter.PatientNameFilter;
import project.webservice1.service.filter.patientFilter.PatientObjectFilter;

import java.util.Set;

@Service
public class PatientFilterService {
    public Set<Patient> filterPatient(Set<Patient> patients, PatientObjectFilter patientObjectFilter){
        FilterI<Patient> patientNameFilter = new PatientNameFilter(patientObjectFilter.getName());

        FilterI<Patient> patientIDFilter = new PatientIDFilter(patientObjectFilter.getPatientID());

        FilterI<Patient> nameAndIDFilter = new AndFilter<>(patientNameFilter, patientIDFilter);

        return nameAndIDFilter.filter(patients);
    }
}
