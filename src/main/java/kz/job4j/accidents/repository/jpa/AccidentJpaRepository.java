package kz.job4j.accidents.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import kz.job4j.accidents.model.Accident;
import org.springframework.stereotype.Repository;

@Repository("accidentJpa")
public interface AccidentJpaRepository extends CrudRepository<Accident, Integer> {
}