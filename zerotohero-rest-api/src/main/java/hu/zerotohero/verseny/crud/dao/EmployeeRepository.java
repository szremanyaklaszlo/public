package hu.zerotohero.verseny.crud.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.zerotohero.verseny.crud.domain.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByName(String name);
    
}
