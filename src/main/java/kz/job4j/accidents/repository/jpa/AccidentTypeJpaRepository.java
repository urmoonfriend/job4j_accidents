package kz.job4j.accidents.repository.jpa;

import kz.job4j.accidents.model.AccidentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("typeJpa")
public interface AccidentTypeJpaRepository extends CrudRepository<AccidentType, Integer> {
}
