package project.webservice1.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Appointment extends BaseModel{

    @ManyToOne(cascade = CascadeType.MERGE/*,targetEntity=Doctor.class*/)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name="doctorID")
    public Doctor doctor;

    @ManyToOne(cascade = CascadeType.MERGE/*,targetEntity=Patient.class*/)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name="patientID")
    public Patient patient;

    public LocalDateTime localDateTime;
    public LocalDateTime endHour;

    @OneToOne
    private Specialty specialty;


    public Appointment(LocalDateTime localDateTime, Patient patient, Doctor doctor) {
        this.setLocalDateTime(localDateTime);
        this.patient = patient;
        this.doctor = doctor;
    }


    public void setSchedule(LocalDateTime localDateTime){
        this.localDateTime=localDateTime;
        this.endHour=localDateTime.plusMinutes(30);
    }
}
