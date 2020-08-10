package project.webservice1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Patient extends Person{

    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, mappedBy = "patient")
    public Set<Appointment> appointments = new HashSet<>();

    @Column(unique = true)
    public Integer patientID;

    public Patient(String name, String address, Integer age, String contact, Integer patientID) {
        super(name, address, age, contact);
        this.setPatientID(patientID);
    }

    public void addAppointment(Appointment appointment){
        appointments.add(appointment);
        appointment.setPatient(this);
    }


    public void cancelAppointment(Appointment appointment){
        appointments.remove(appointment);
        this.appointments.remove(appointment);
    }
}
