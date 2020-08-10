package project.webservice1.service.filter.doctorFilter;

import project.webservice1.model.Doctor;
import project.webservice1.model.Specialty;

import java.util.Set;
import java.util.stream.Collectors;

public class DoctorSpecialtyFilter implements DoctorFilter{
    private Specialty specialty;

    public DoctorSpecialtyFilter(Specialty specialty) {
        this.specialty = specialty;
    }


    @Override
    public Set<Doctor> filter(Set<Doctor> doctors) {
        if (specialty==null)return doctors;
        return doctors.stream().filter(doctor -> doctor.getSpecialty().equals(this.specialty)).collect(Collectors.toSet());
    }
}
