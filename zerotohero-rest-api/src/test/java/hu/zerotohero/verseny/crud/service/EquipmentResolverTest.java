package hu.zerotohero.verseny.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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

import hu.zerotohero.verseny.crud.domain.Employee;
import hu.zerotohero.verseny.crud.domain.Equipment;
import hu.zerotohero.verseny.crud.domain.EquipmentType;
import hu.zerotohero.verseny.crud.domain.JobType;
import hu.zerotohero.verseny.crud.domain.Location;
import hu.zerotohero.verseny.crud.service.exception.HasNoFreeEquipmentException;

@SpringBootTest
public class EquipmentResolverTest {

    @Mock
    private EquipmentService equipmentService;
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EquipmentResolver underTest;

    @ParameterizedTest
    @MethodSource(value = { "getEquipmentTestCases" })
    void getEquipment(List<Equipment> equipments, List<Employee> employees, List<Equipment> possibleResults, Employee employee) throws Exception {
        // Given
        // When
        when(equipmentService.findAllEquipmentByLocation(employee.getLocation())).thenReturn(equipments);
        when(employeeService.findAllByLocation(employee.getLocation())).thenReturn(employees);
        Equipment result = underTest.getEquipment(employee.getLocation(), employee);
        // Then
        assertThat(result).isIn(possibleResults);
    }

    static Stream<Arguments> getEquipmentTestCases() {
        Location location = new Location("Test", "Test");
        Equipment equipment = new Equipment("Needed", EquipmentType.CASH_REGISTER, location);
        Employee employee = new Employee("Test", JobType.CASHIER, location, equipment);

        Arguments whenHasFreeEquipmentBesideOneReserved = Arguments.of(
                Arrays.asList(new Equipment("Free1", EquipmentType.CASH_REGISTER, location),
                        new Equipment("Free2", EquipmentType.CASH_REGISTER, location),
                        new Equipment("Reserved", EquipmentType.CASH_REGISTER, location)),
                Arrays.asList(new Employee("One block", JobType.CASHIER, location, new Equipment("Reserved", EquipmentType.CASH_REGISTER, location))),
                Arrays.asList(new Equipment("Free1", EquipmentType.CASH_REGISTER, location),
                        new Equipment("Free2", EquipmentType.CASH_REGISTER, location)),
                employee);
        Arguments whenHasFreeEquipmentWithOtherReservedType = Arguments.of(
                Arrays.asList(new Equipment("Free", EquipmentType.CASH_REGISTER, location),
                        new Equipment("Other type", EquipmentType.OVEN, location),
                        new Equipment("Reserved", EquipmentType.CASH_REGISTER, location)),
                Arrays.asList(new Employee("One block", JobType.CASHIER, location, new Equipment("Reserved", EquipmentType.CASH_REGISTER, location))),
                Arrays.asList(new Equipment("Free", EquipmentType.CASH_REGISTER, location)),
                employee);
        return Stream.of(whenHasFreeEquipmentBesideOneReserved, whenHasFreeEquipmentWithOtherReservedType);
    }

    @ParameterizedTest
    @MethodSource(value = { "getEquipmentThrowEquipmentNotFoundExceptionTestCases" })
    void getEquipment_throwEquipmentNotFoundExceptionWhenNotFoundFreeEquipment(List<Equipment> equipments, List<Employee> employees, Exception exception,
            Employee employee) throws Exception {
        // Given
        // When
        when(equipmentService.findAllEquipmentByLocation(new Location("Test", "Test"))).thenReturn(equipments);
        when(employeeService.findAllByLocation(new Location("Test", "Test"))).thenReturn(employees);
        // Then
        assertThatExceptionOfType(exception.getClass()).isThrownBy(() -> underTest.getEquipment(employee.getLocation(), employee));
    }

    static Stream<Arguments> getEquipmentThrowEquipmentNotFoundExceptionTestCases() {
        Location location = new Location("Test", "Test");
        Equipment equipment = new Equipment("Needed", EquipmentType.CASH_REGISTER, location);
        Employee employee = new Employee("Test", JobType.CASHIER, location, equipment);

        Arguments whenHasNoFreeEquipment = Arguments.of(
                Arrays.asList(new Equipment("Reserved", EquipmentType.CASH_REGISTER, location)),
                Arrays.asList(new Employee("One block", JobType.CASHIER, location, new Equipment("Reserved", EquipmentType.CASH_REGISTER, location))),
                new HasNoFreeEquipmentException(),
                employee);
        Arguments whenHasNoEquipment = Arguments.of(
                Arrays.asList(),
                Arrays.asList(new Employee("One block", JobType.CASHIER, location, new Equipment("Reserved", EquipmentType.CASH_REGISTER, location))),
                new HasNoFreeEquipmentException(),
                employee);
        Arguments whenEmployeeNull = Arguments.of(
                Arrays.asList(new Equipment("Free", EquipmentType.CASH_REGISTER, location)),
                Arrays.asList(),
                new NullPointerException(),
                null);
        return Stream.of(whenHasNoFreeEquipment, whenHasNoEquipment, whenEmployeeNull);
    }

    @ParameterizedTest
    @MethodSource(value = { "isReservedTestCases" })
    void isReserved(boolean expected, Equipment equipment, List<Employee> employees) throws Exception {
        // Given
        // When
        when(employeeService.findAllByLocation(equipment.getLocatedAt())).thenReturn(employees);
        boolean result = underTest.isReserved(equipment);
        // Then
        assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> isReservedTestCases() {
        Location location = new Location("Test", "Test");
        Equipment equipment = new Equipment("Needed", EquipmentType.CASH_REGISTER, location);

        Arguments notReserved = Arguments.of(
                false, equipment,
                Arrays.asList(new Employee("No block", JobType.CASHIER, location, new Equipment("Test", EquipmentType.CASH_REGISTER, location))));
        Arguments reserved = Arguments.of(
                true, equipment,
                Arrays.asList(new Employee("No block", JobType.CASHIER, location, new Equipment("Test", EquipmentType.CASH_REGISTER, location)),
                        new Employee("One block", JobType.CASHIER, location, equipment)));
        Arguments hasNoEmployee = Arguments.of(
                false, equipment, Arrays.asList());
        return Stream.of(notReserved, reserved, hasNoEmployee);
    }

}
