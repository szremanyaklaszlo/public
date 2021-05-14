package hu.zerotohero.verseny.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import hu.zerotohero.verseny.crud.dao.LocationRepository;
import hu.zerotohero.verseny.crud.domain.Location;

@SpringBootTest
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;
    @InjectMocks
    private LocationService underTest;

    @Test
    void findAll_ReturnAllLocations() {
        // Given
        Iterable<Location> locations = Arrays.asList(new Location("First store", "New York"),
                new Location("Second store", "London"));
        // When
        when(locationRepository.findAll()).thenReturn(locations);
        List<Location> result = underTest.findAll();
        // Then
        assertThat(result).isEqualTo(locations);
    }

}
