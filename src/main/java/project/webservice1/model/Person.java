package project.webservice1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person extends BaseModel{
    public String name;
    public String address;
    public Integer age;
    public String contact;

    public Person(String name, String address, Integer age, String contact) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.contact = contact;
    }
}
