package project.webservice1.service.filter.doctorFilter;

import project.webservice1.model.Doctor;
import project.webservice1.model.Schedule;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

public class DoctorDayFilter implements DoctorFilter{

    private DayOfWeek dayOfWeek;

    public DoctorDayFilter(DayOfWeek dayOfWeek){
        this.dayOfWeek=dayOfWeek;
    }

    @Override
    public Set<Doctor> filter(Set<Doctor> doctors) {
        if(dayOfWeek==null)return doctors;
        Set<Doctor> filtered = new HashSet<>();
        for (Doctor doctor:doctors){
            for (Schedule schedule: doctor.getSchedules()){
                if (schedule.getDayOfWeek().equals(this.dayOfWeek)){
                    filtered.add(doctor);
                    break;
                }
            }
        }
        return filtered;
    }
}
