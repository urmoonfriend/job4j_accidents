package kz.job4j.accidents.service;

import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.repository.AccidentTypeMem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    public List<AccidentType> getTypes() {
        return accidentTypeMem.getAccidentTypes();
    }

    public Optional<AccidentType> findById(Integer id) {
        return accidentTypeMem.findById(id);
    }
}