package project.webservice1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.webservice1.model.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
