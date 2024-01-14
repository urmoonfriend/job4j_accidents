package kz.job4j.accidents.repository;

import kz.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

public interface AccidentTypeRepository {
    List<AccidentType> getAccidentTypes();

    AccidentType save(AccidentType type);

    boolean update(AccidentType type);

    Optional<AccidentType> findById(Integer id);
}
