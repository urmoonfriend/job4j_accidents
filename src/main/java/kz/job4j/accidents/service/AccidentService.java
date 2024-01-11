package kz.job4j.accidents.service;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.repository.AccidentMem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    public List<Accident> getAll() {
        return accidentMem.getAllAccidents();
    }

    public List<AccidentType> getTypes() {
        return accidentMem.getAccidentTypes();
    }

    public void create(Accident accident) {
        accidentMem.save(accident);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }

    public Optional<Accident> getById(Integer id) {
        return accidentMem.findById(id);
    }
}
