package project.webservice1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Doctor extends Person{

    public Integer doctorID;

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(targetEntity= Appointment.class,cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "doctor")
    public Set<Appointment> appointments = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "doctor")
    public Set<Specialty> specialties = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @OneToMany(targetEntity=Schedule.class,cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "doctor")
    public Set<Schedule> schedules = new HashSet<>();


    public Doctor(String name, String address, Integer age, String contact, Integer doctorID, Specialty specialty) {
        super(name, address,age, contact);
        this.doctorID=doctorID;
        this.addSpecialty(specialty);
    }

    public boolean addAppointmentDoctor(Appointment appointment){
        if(verifySchedule(appointment.getLocalDateTime()) && verifyAppointments(appointment.getLocalDateTime())){
            appointments.add(appointment);
            appointment.setDoctor(this);
            appointment.setSpecialty(this.getSpecialty());
            return true;
        } else return false;
    }

    public Specialty getSpecialty(){
        for (Specialty specialty: this.getSpecialties())
            return specialty;
        return null;
    }

    //modify specialtys
    public void addSpecialty(Specialty specialty){
        specialties.add(specialty);
        specialty.setDoctor(this);
    }

    public void removeSpecialty(Specialty specialty){
        specialties.remove(specialty);
    }

    //modify schedules
    public void addSchedule(Schedule schedule){
        schedules.add(schedule);
        schedule.setDoctor(this);
    }

    public void removeSchedule(Schedule schedule){
        schedules.remove(schedule);
    }

    private boolean verifySchedule(LocalDateTime localDateTime){
        for (Schedule schedule:this.getSchedules()){
            if(schedule.getDayOfWeek().equals(localDateTime.getDayOfWeek()) && schedule.getStartHour().isBefore(localDateTime.toLocalTime()) && schedule.getEndHour().isAfter(localDateTime.toLocalTime()))
                return true;
        }
        return false;
    }

    private boolean verifyAppointments(LocalDateTime localDateTime){
        for (Appointment appointment: this.getAppointments()){
            if (appointment.getLocalDateTime().getDayOfWeek().equals(localDateTime.getDayOfWeek())){
                if (appointment.getLocalDateTime().equals(localDateTime))
                    return false;
                if (appointment.getEndHour().toLocalTime().isAfter(localDateTime.toLocalTime()))
                    return false;
                if (appointment.getLocalDateTime().toLocalTime().isBefore(localDateTime.toLocalTime().plusMinutes(30)))
                    return false;
            }
        }
        return true;
    }
}
