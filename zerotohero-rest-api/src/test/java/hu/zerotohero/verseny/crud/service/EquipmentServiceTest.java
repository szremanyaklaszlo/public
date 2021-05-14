package hu.zerotohero.verseny.crud.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import hu.zerotohero.verseny.crud.dao.EquipmentRepository;
import hu.zerotohero.verseny.crud.domain.Equipment;
import hu.zerotohero.verseny.crud.domain.EquipmentType;
import hu.zerotohero.verseny.crud.domain.Location;

@SpringBootTest
public class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;
    @Mock
    private LocationResolver locationResolver;
    @Mock
    private EquipmentResolver equipmentResolver;
    @InjectMocks
    private EquipmentService underTest;

    @Test
    void findAllEquipment_returnEquipments() {
        //Given
        Iterable<Equipment> equipments = Arrays.asList(
                new Equipment("test1", EquipmentType.CASH_REGISTER, new Location()),
                new Equipment("test2", EquipmentType.OVEN, new Location()),
                new Equipment("test3", EquipmentType.CASH_REGISTER, new Location("test", "test")));
        //When
        when(equipmentRepository.findAll()).thenReturn(equipments);
        List<Equipment> result = underTest.findAll();
        //Then
        assertThat(result).isEqualTo(equipments);
    }
    
    @Test
    void findAllEquipmentByLocation_returnEquipmentsFromTheLocation() {
        //Given
        Location location = new Location("test", "test");
        Location otherLocation = new Location("other", "other");
        Iterable<Equipment> equipments = Arrays.asList(
                new Equipment("test1", EquipmentType.CASH_REGISTER, location),
                new Equipment("test2", EquipmentType.OVEN, location),
                new Equipment("test3", EquipmentType.CASH_REGISTER, otherLocation));
        List<Equipment> expected = Arrays.asList(
                new Equipment("test1", EquipmentType.CASH_REGISTER, location),
                new Equipment("test2", EquipmentType.OVEN, location));
        //When
        when(equipmentRepository.findAll()).thenReturn(equipments);
        List<Equipment> result = underTest.findAllEquipmentByLocation(location);
        //Then
        assertThat(result).isEqualTo(expected);
    }

}
