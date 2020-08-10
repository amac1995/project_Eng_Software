package project.webservice1.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee extends Person{
    public Integer employeeID;
    public Integer iban;

    public Employee(String name, String address, Integer age, String contact) {
        super(name, address, age, contact);
    }
}
