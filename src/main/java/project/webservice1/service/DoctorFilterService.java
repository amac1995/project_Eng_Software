package project.webservice1.service;

import org.springframework.stereotype.Service;
import project.webservice1.model.Doctor;
import project.webservice1.service.filter.AndFilter;
import project.webservice1.service.filter.FilterI;
import project.webservice1.service.filter.doctorFilter.*;

import java.util.Set;

@Service
public class DoctorFilterService {
    public Set<Doctor> filterDoctor(Set<Doctor> doctors, DoctorFilterObject doctorFilterObject){
        System.out.println(doctorFilterObject);
        FilterI<Doctor> doctorNameFilter = new DoctorNameFilter(doctorFilterObject.getName());
        FilterI<Doctor> doctorSpecialtyFilter = new DoctorSpecialtyFilter(doctorFilterObject.getSpecialty());
        FilterI<Doctor> nameAndSpecialtyFilter = new AndFilter<>(doctorNameFilter, doctorSpecialtyFilter);
        FilterI<Doctor> doctorStartHourFilter = new DoctorStartHourFilter(doctorFilterObject.getStartHour());
        FilterI<Doctor> nameAndSpecialtyAndStartHourFilter = new AndFilter<>(nameAndSpecialtyFilter, doctorStartHourFilter);
        FilterI<Doctor> doctorEndHourFIlter = new DoctorEndHourFilter(doctorFilterObject.getEndHour());
        FilterI<Doctor> nameAndSpecialtyAndStartHourAndEndHourFilter = new AndFilter<>(nameAndSpecialtyAndStartHourFilter, doctorEndHourFIlter);
        return nameAndSpecialtyAndStartHourAndEndHourFilter.filter(doctors);
    }
}
