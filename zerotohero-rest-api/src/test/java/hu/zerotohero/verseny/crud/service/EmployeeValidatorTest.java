package hu.zerotohero.verseny.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hu.zerotohero.verseny.crud.domain.Employee;
import hu.zerotohero.verseny.crud.domain.Equipment;
import hu.zerotohero.verseny.crud.domain.EquipmentType;
import hu.zerotohero.verseny.crud.domain.JobType;
import hu.zerotohero.verseny.crud.domain.Location;

@SpringBootTest
@Transactional
public class EmployeeValidatorTest {

    @Mock
    private EmployeeService employeeService;
    
    @InjectMocks
    private EmployeeValidator underTest;

    
    @ParameterizedTest
    @MethodSource("isValidTestCases")
    void isValid_returnBoolean(List<Employee> employees, boolean expected, Employee employee) {
        //Given
        //When
        when(employeeService.findAllByLocation(employee.getLocation())).thenReturn(employees);
        boolean result = underTest.isValid(employee);
        //Then
        assertThat(expected).isEqualTo(result);
    }
    
    static Stream<Arguments> isValidTestCases(){
        List<Employee> employeesForLocationA = Arrays.asList(
                new Employee("Cook", JobType.COOK, new Location("A","A"), new Equipment(null,EquipmentType.OVEN,null)),
                new Employee("Cashier", JobType.CASHIER, new Location("A","A"), new Equipment(null,EquipmentType.CASH_REGISTER,null)));
        List<Employee> employeesForLocationB = Arrays.asList(
                new Employee("Manager blocker", JobType.MANAGER, new Location("B","B"), null));
        List<Employee> employeesForLocationC = Arrays.asList(
                new Employee("He is the manager", JobType.MANAGER, new Location("C","C"), null));
        return Stream.of(
                Arguments.of(employeesForLocationA, true, new Employee("No manager in the location", JobType.MANAGER, new Location("A","A"), null)),
                Arguments.of(employeesForLocationB, false, new Employee("Already have a manager in the location", JobType.MANAGER, new Location("B","B"), null)),
                Arguments.of(employeesForLocationC, true, new Employee("He is the manager", JobType.MANAGER, new Location("C","C"), null)),
                Arguments.of(employeesForLocationA, true, new Employee("Valid CASHIER", JobType.CASHIER, new Location("A","A"), new Equipment(null, EquipmentType.CASH_REGISTER, null))),
                Arguments.of(employeesForLocationA, false, new Employee("Invalid CASHIER", JobType.CASHIER, new Location("C","C"), new Equipment(null,EquipmentType.OVEN,null))),
                Arguments.of(employeesForLocationA, true, new Employee("Valid COOK", JobType.COOK, new Location("C","C"), new Equipment(null,EquipmentType.OVEN,null))),
                Arguments.of(employeesForLocationA, false, new Employee("Invalid COOK", JobType.COOK, new Location("C","C"), new Equipment(null,EquipmentType.CASH_REGISTER,null)))
                );
    }
}
