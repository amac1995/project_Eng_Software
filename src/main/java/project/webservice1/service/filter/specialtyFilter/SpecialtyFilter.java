package project.webservice1.service.filter.specialtyFilter;

import project.webservice1.model.Specialty;

import java.util.Set;

public interface SpecialtyFilter {
    Set<Specialty> filter(Set<Specialty> specialties);
}
