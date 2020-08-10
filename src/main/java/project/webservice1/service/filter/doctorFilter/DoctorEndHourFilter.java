package project.webservice1.service.filter.doctorFilter;

import project.webservice1.model.Doctor;
import project.webservice1.model.Schedule;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class DoctorEndHourFilter implements DoctorFilter{
    private LocalTime localTime;

    public DoctorEndHourFilter(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override
    public Set<Doctor> filter(Set<Doctor> doctors) {
        if (localTime==null) return doctors;
        Set<Doctor> filteredDoctors = new HashSet<>();
        for (Doctor doctor:doctors){
            for (Schedule schedule:doctor.getSchedules()){
                filteredDoctors.add(doctor);
            }
        }
        return filteredDoctors;
    }
}
