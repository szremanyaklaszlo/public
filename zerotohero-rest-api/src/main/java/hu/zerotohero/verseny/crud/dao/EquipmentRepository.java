package hu.zerotohero.verseny.crud.dao;

import org.springframework.data.repository.CrudRepository;

import hu.zerotohero.verseny.crud.domain.Equipment;

public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

}
