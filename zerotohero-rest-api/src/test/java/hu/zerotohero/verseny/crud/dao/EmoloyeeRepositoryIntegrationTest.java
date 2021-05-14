package hu.zerotohero.verseny.crud.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import hu.zerotohero.verseny.crud.domain.Employee;
import hu.zerotohero.verseny.crud.domain.JobType;
import hu.zerotohero.verseny.crud.domain.Location;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
public class EmoloyeeRepositoryIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LocationRepository locationRepository;

    @ParameterizedTest
    @MethodSource("findByNameTestCases")
    void findByName_returnsListOfEmployees(int expected, String name) throws Exception {
        // Given
        Location location = new Location("Store name", "Address");
        locationRepository.save(location);
        employeeRepository.saveAll(Arrays.asList(
                new Employee("Jackson", JobType.COOK, location, null),
                new Employee("Mary", JobType.MANAGER, location, null),
                new Employee("Mary", JobType.CASHIER, location, null)));
        // When
        List<Employee> result = employeeRepository.findByName(name);
        // Then
        assertThat(expected).isEqualTo(result.size());
    }

    static Stream<Arguments> findByNameTestCases() {
        return Stream.of(Arguments.of(1, "Jackson"),
                Arguments.of(2, "Mary"),
                Arguments.of(0, "Unknown"));
    }

}
