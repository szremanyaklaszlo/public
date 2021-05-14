package hu.zerotohero.verseny.crud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.zerotohero.verseny.crud.domain.Employee;
import hu.zerotohero.verseny.crud.domain.EquipmentType;
import hu.zerotohero.verseny.crud.domain.JobType;
import hu.zerotohero.verseny.crud.domain.Location;

@Service
public class EmployeeValidator {

    @Autowired
    private EmployeeService employeeService;

    public boolean isValid(Employee employee) {
        boolean result = false;
        switch (employee.getJobType()) {
        case MANAGER:
            result = hasNoEquipment(employee) && isThereNoOtherManagerInTheLocation(employee);
            break;
        case COOK:
            result = doesHeWorksInAnOven(employee);
            break;
        case CASHIER:
            result = doesHeWorksInACashRegister(employee);
            break;
        default:
            throw new IllegalArgumentException("Job type: " + employee.getJobType().name().toString() + " does not defined.");
        }
        return result;
    }

    private boolean hasNoEquipment(Employee employee) {
        return employee.getEquipment() == null;
    }

    private boolean isThereNoOtherManagerInTheLocation(Employee employee) {
        List<Employee> managers = getManagers(employee.getLocation());
        return managers.size() == 0 ||
                managers.size() == 1 && managers.get(0).equals(employee);
    }

    private List<Employee> getManagers(Location location) {
        return employeeService.findAllByLocation(location).stream()
                .filter(employee -> employee.getJobType() == JobType.MANAGER)
                .collect(Collectors.toList());
    }

    private boolean doesHeWorksInAnOven(Employee employee) {
        return employee.getEquipment().getType() == EquipmentType.OVEN;
    }

    private boolean doesHeWorksInACashRegister(Employee employee) {
        return employee.getEquipment().getType() == EquipmentType.CASH_REGISTER;
    }

}
