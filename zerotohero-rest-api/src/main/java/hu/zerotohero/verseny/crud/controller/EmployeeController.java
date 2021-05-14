package hu.zerotohero.verseny.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.zerotohero.verseny.crud.domain.Employee;
import hu.zerotohero.verseny.crud.service.EmployeeService;

@RestController
@RequestMapping("/crud")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "api/employee/get", produces = "application/json")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    @PostMapping(value = "api/employee", consumes = "application/json")
    public HttpStatus createEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return HttpStatus.OK;
    }
    
    @PutMapping(value = "api/employee/{id}", consumes = "application/json")
    public HttpStatus updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.updateEmployee(id, employee);
        return HttpStatus.OK;
    }
    
    @DeleteMapping(value = "api/employee/delete/{id}", consumes = "application/json")
    public HttpStatus deleteEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employeeService.deleteEmployee(id);
        return HttpStatus.OK;
    }

}
