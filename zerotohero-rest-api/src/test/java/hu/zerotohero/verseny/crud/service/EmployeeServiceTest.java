package hu.zerotohero.verseny.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import hu.zerotohero.verseny.crud.dao.EmployeeRepository;
import hu.zerotohero.verseny.crud.domain.Employee;
import hu.zerotohero.verseny.crud.domain.Equipment;
import hu.zerotohero.verseny.crud.domain.JobType;
import hu.zerotohero.verseny.crud.domain.Location;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EquipmentResolver equipmentResolver;
    @Mock
    private EmployeeValidator employeeValidator;
    @Mock
    private LocationResolver locationResolver;
    @InjectMocks
    private EmployeeService underTest;

    @Test
    void testFindAllReturnAllEmployee() {
        // Given
        Iterable<Employee> employees = Arrays.asList(
                new Employee("Test1", JobType.MANAGER, new Location("A", "A"), null),
                new Employee("Test2", JobType.COOK, new Location("A", "A"), new Equipment()));
        // When
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> result = underTest.findAll();
        // Then
        assertThat(employees).isEqualTo(result);
    }

    @Test
    void testFindAllByLocationReturnAllEmployeeFromTheLocation() {
        // Given
        Location location = new Location("test", "test");
        Location otherLocation = new Location("other", "other");
        Iterable<Employee> employees = Arrays.asList(
                new Employee("Test1", JobType.MANAGER, location, null),
                new Employee("Test2", JobType.COOK, location, new Equipment()),
                new Employee("Test3", JobType.COOK, otherLocation, new Equipment()),
                new Employee("Test4", JobType.COOK, otherLocation, new Equipment()));
        Iterable<Employee> expected = Arrays.asList(
                new Employee("Test1", JobType.MANAGER, location, null),
                new Employee("Test2", JobType.COOK, location, new Equipment()));
        // When
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> result = underTest.findAllByLocation(location);
        // Then
        assertThat(expected).isEqualTo(result);
    }

}
