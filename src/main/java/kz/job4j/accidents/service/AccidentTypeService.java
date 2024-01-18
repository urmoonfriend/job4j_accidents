package kz.job4j.accidents.service;

import kz.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

public interface AccidentTypeService {
    List<AccidentType> getTypes();

    Optional<AccidentType> findById(Integer id);
}
