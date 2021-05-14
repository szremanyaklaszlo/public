package hu.zerotohero.verseny.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import hu.zerotohero.verseny.crud.domain.Location;
import hu.zerotohero.verseny.crud.service.exception.LocationNotFoundException;

@SpringBootTest
public class LocationResolverTest {

    @Mock
    private LocationService locationService;
    @InjectMocks
    private LocationResolver underTest;
    
    @Test
    void getLocation_findTheLocationInTheDatabaseAndReturnIt () {
        //Given
        Location london = new Location("Store one","London");
        Location dublin = new Location("Store two","Dublin");
        List<Location> locations = Arrays.asList(london,dublin);
        //When
        when(locationService.findAll()).thenReturn(locations);
        Location result = underTest.getLocation(london);
        //Then
        assertThat(london).isEqualTo(result);
    }
    
    @Test
    void getLocation_throwExceprionWhenDontFind () {
        //Given
        Location london = new Location("Store one","London");
        Location dublin = new Location("Store two","Dublin");
        List<Location> locations = Arrays.asList(dublin);
        //When
        when(locationService.findAll()).thenReturn(locations);
        //Then
        assertThatExceptionOfType(LocationNotFoundException.class).isThrownBy(() -> underTest.getLocation(london));
    }
    
    @Test
    void getLocation_throwExceprionWhenListEmpty () {
        //Given
        Location london = new Location("Store one","London");
        List<Location> locations = Arrays.asList();
        //When
        when(locationService.findAll()).thenReturn(locations);
        //Then
        assertThatExceptionOfType(LocationNotFoundException.class).isThrownBy(() -> underTest.getLocation(london));
    }
}
