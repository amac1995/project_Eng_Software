package project.webservice1.service;

import org.springframework.stereotype.Service;
import project.webservice1.model.Employee;
import project.webservice1.repository.EmployeeRepo;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeService implements EmployeeServiceI{
    public EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Set<Employee> getAllEmployee(){
        Set<Employee> employees = new HashSet<>();
        for (Employee employee:this.employeeRepo.findAll())
            employees.add(employee);
        return employees;
    }
}
