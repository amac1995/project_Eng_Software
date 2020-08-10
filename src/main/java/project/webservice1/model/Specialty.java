package project.webservice1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Specialty extends BaseModel{
    private String name;
    private float cost;

    @OneToOne(cascade = CascadeType.MERGE,targetEntity=Doctor.class)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name="doctorID")
    private Doctor doctor;

    public Specialty(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }
}
