package project.webservice1.service.filter.doctorFilter;

import project.webservice1.model.Doctor;

import java.util.Set;
import java.util.stream.Collectors;

public class DoctorNameFilter implements DoctorFilter{
    private String name;

    public DoctorNameFilter(String name) {
        this.name = name;
    }

    @Override
    public Set<Doctor> filter(Set<Doctor> doctors) {
        if (name==null)return doctors;
        return doctors.stream().filter(doctor -> doctor.getName().equalsIgnoreCase(this.name)).collect(Collectors.toSet());
    }
}
