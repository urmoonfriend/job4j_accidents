package kz.job4j.accidents.service;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.AccidentMem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccidentService {

    private final AccidentMem accidentMem;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public List<Accident> getAll() {
        return accidentMem.getAllAccidents();
    }

    public List<AccidentType> getTypes() {
        return accidentTypeService.getTypes();
    }

    public List<Rule> getRules() {
        return ruleService.getRules();
    }

    public void create(Accident accident) {
        accidentTypeService.findById(accident.getType().getId()).ifPresent(accident::setType);
        accidentMem.save(accident);
    }

    public void update(Accident accident) {
        accidentTypeService.findById(accident.getType().getId()).ifPresent(accident::setType);
        accidentMem.update(accident);
    }

    public Optional<Accident> getById(Integer id) {
        return accidentMem.findById(id);
    }

    public void create(Accident accident, String[] ids) {
        log.info("create method request: accident = [{}], ids = [{}]", accident, ids);
        accident.setRules(ruleService.getRulesByIds(ids));
        create(accident);
    }

    public void update(Accident accident, String[] ids) {
        accident.setRules(ruleService.getRulesByIds(ids));
        update(accident);
    }
}
