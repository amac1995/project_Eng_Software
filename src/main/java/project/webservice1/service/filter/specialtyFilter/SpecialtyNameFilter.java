package project.webservice1.service.filter.specialtyFilter;

import project.webservice1.model.Specialty;

import java.util.Set;
import java.util.stream.Collectors;

public class SpecialtyNameFilter implements SpecialtyFilter {
    private String name;

    public SpecialtyNameFilter(String name) {
        this.name = name;
    }

    @Override
    public Set<Specialty> filter(Set<Specialty> specialties) {
        if (name==null) return specialties;
        return specialties.stream().filter(specialty -> specialty.getName().equalsIgnoreCase(this.name)).collect(Collectors.toSet());
    }
}
