package hu.zerotohero.verseny.crud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.zerotohero.verseny.crud.dao.EquipmentRepository;
import hu.zerotohero.verseny.crud.domain.Equipment;
import hu.zerotohero.verseny.crud.domain.Location;
import hu.zerotohero.verseny.crud.service.exception.EquipmentNotFoundException;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private LocationResolver locationResolver;
    @Autowired
    private EquipmentResolver equipmentResolver;
    
    public List<Equipment> findAll(){
        return (List<Equipment>) equipmentRepository.findAll();
    }
    
    public List<Equipment> findAllEquipmentByLocation(Location location){
        return findAll().stream().filter(equipment -> location.equals(equipment.getLocatedAt())).collect(Collectors.toList());
    }
    
    public void createEquipment (Equipment equipment) {
        equipment.setLocatedAt(locationResolver.getLocation(equipment.getLocatedAt()));
        equipmentRepository.save(equipment);
    }
    
    public void updateEquipment(long id, Equipment updatedEquipment) {
        Equipment equipment = findEquipmentById(id);
        if(equipmentResolver.isReserved(equipment)) {
            throw new RuntimeException("Could not be modified this equipment. Someone use it.");
        }
        equipment.setName(updatedEquipment.getName());
        equipment.setType(updatedEquipment.getType());
        equipment.setLocatedAt(locationResolver.getLocation(updatedEquipment.getLocatedAt()));
        equipmentRepository.save(equipment);
    }
    
    public void deleteEquipment(long id) {
        Equipment equipment = findEquipmentById(id);
        equipmentRepository.delete(equipment);
    }

    private Equipment findEquipmentById(long id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with id " + id));
    }

}
