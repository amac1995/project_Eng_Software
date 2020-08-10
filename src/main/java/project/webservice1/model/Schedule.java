package project.webservice1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Schedule extends BaseModel{
    public DayOfWeek dayOfWeek;
    public LocalTime startHour;
    public LocalTime endHour;

    @ManyToOne(cascade = CascadeType.MERGE/*,targetEntity=Doctor.class*/)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name="doctorID")
    public Doctor doctor;

    public Schedule(DayOfWeek dayOfWeek, LocalTime startHour, LocalTime endHour) {
        this.dayOfWeek = dayOfWeek;
        this.startHour = startHour;
        this.endHour = endHour;
    }
}
