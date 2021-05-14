package hu.zerotohero.verseny.crud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.zerotohero.verseny.crud.dao.EmployeeRepository;
import hu.zerotohero.verseny.crud.domain.Employee;
import hu.zerotohero.verseny.crud.domain.Location;
import hu.zerotohero.verseny.crud.service.exception.EmployeNotFoundException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EquipmentResolver equipmentResolver;
    @Autowired
    private EmployeeValidator employeeValidator;
    @Autowired
    private LocationResolver locationResolver;

    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public List<Employee> findAllByLocation(Location location) {
        return findAll().stream().filter(employee -> employee.getLocation() == location).collect(Collectors.toList());
    }

    public void saveEmployee(Employee employee) {
        if (!employeeValidator.isValid(employee)) {
            throw new IllegalArgumentException("Invalid emoloyee data.");
        }
        employee.setLocation(locationResolver.getLocation(employee.getLocation()));
        employee.setEquipment(equipmentResolver.getEquipment(employee.getLocation(), employee));
        employeeRepository.save(employee);
    }

    public void updateEmployee(long id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeNotFoundException("Employe not found with id " + id));
        if (!employeeValidator.isValid(updatedEmployee)) {
            throw new IllegalArgumentException("Invalid emoloyee data.");
        }
        employee.setName(updatedEmployee.getName());
        employee.setJobType(updatedEmployee.getJobType());
        employee.setLocation(locationResolver.getLocation(updatedEmployee.getLocation()));
        employee.setEquipment(equipmentResolver.getEquipment(updatedEmployee.getLocation(), updatedEmployee));
        employeeRepository.save(employee);
    }
    
    public void deleteEmployee(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeNotFoundException("Employe not found with id " + id));
        employeeRepository.delete(employee);
    }
}
