package kz.job4j.accidents.repository;

import kz.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository {
    List<Accident> getAllAccidents();

    Accident save(Accident accident);

    boolean update(Accident accident);

    Optional<Accident> findById(Integer id);
}
