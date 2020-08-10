package project.webservice1.service;

import org.springframework.stereotype.Service;
import project.webservice1.model.Specialty;
import project.webservice1.service.filter.specialtyFilter.SpecialtyFilter;
import project.webservice1.service.filter.specialtyFilter.SpecialtyNameFilter;
import project.webservice1.service.filter.specialtyFilter.SpecialtyObjectFilter;

import java.util.Set;

@Service
public class SpecialtyFilterService {
    public Set<Specialty> specialtyFilter(Set<Specialty> specialty, SpecialtyObjectFilter specialtyObjectFilter) {
        System.out.println(specialtyObjectFilter);
        SpecialtyFilter specialtyNameFilter = new SpecialtyNameFilter(specialtyObjectFilter.getNome());
        return specialtyNameFilter.filter(specialty);
    }
}
