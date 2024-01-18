package kz.job4j.accidents.service;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;

public interface AccidentService {
    List<Accident> getAll();

    List<AccidentType> getTypes();

    List<Rule> getRules();

    void create(Accident accident);

    void update(Accident accident);

    Optional<Accident> getById(Integer id);

    void create(Accident accident, String[] ids);

    void update(Accident accident, String[] ids);
}
