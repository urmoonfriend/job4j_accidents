package kz.job4j.accidents.service;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.repository.AccidentMem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccidentService {
    private final AccidentMem accidentMem;

    public List<Accident> getAll() {
        return accidentMem.getAll();
    }

}
