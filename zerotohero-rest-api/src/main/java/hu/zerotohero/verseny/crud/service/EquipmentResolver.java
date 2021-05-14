package hu.zerotohero.verseny.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.zerotohero.verseny.crud.domain.Employee;
import hu.zerotohero.verseny.crud.domain.Equipment;
import hu.zerotohero.verseny.crud.domain.Location;
import hu.zerotohero.verseny.crud.service.exception.HasNoFreeEquipmentException;

@Service
public class EquipmentResolver {

    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private EmployeeService employeeService;

    public Equipment getEquipment(Location location, Employee employee) {
        if(employee == null) {
            throw new NullPointerException("Employee is null.");
        }
        List<Equipment> equipments = equipmentService.findAllEquipmentByLocation(location);
        return equipments.stream()
                .filter(equipment -> isUseable(employee, equipment))
                .findAny()
                .orElseThrow(() -> new HasNoFreeEquipmentException(
                        "Has no free in type of " + employee.getEquipment().getType()));
    }

    private boolean isUseable(Employee employee, Equipment equipment) {
        return equipment.getType() == employee.getEquipment().getType() && !isReserved(equipment)
                || employee.getEquipment().getId() != null && employee.getEquipment().getId().equals(equipment.getId());
    }

    public boolean isReserved(Equipment equipment) {
        List<Employee> employees = employeeService.findAllByLocation(equipment.getLocatedAt());
        return employees.stream()
                .anyMatch(employee -> employee.getEquipment().equals(equipment));
    }

}
