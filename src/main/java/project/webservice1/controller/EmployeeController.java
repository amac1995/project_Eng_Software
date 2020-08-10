package project.webservice1.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import project.webservice1.model.Employee;
import project.webservice1.service.EmployeeServiceI;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    public EmployeeServiceI employeeServiceI;

    public EmployeeController(EmployeeServiceI employeeServiceI) {
        this.employeeServiceI = employeeServiceI;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Employee> AllEmployee(){
        return employeeServiceI.getAllEmployee();
    }
}
