package hu.zerotohero.verseny.crud.dao;

import org.springframework.data.repository.CrudRepository;

import hu.zerotohero.verseny.crud.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Long> {

}
