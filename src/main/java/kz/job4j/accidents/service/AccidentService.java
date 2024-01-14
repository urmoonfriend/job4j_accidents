package kz.job4j.accidents.service;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.AccidentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccidentService {

    @Qualifier("accidentHibernate")
    private final AccidentRepository accidentRepository;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public List<Accident> getAll() {
        return accidentRepository.getAllAccidents();
    }

    public List<AccidentType> getTypes() {
        return accidentTypeService.getTypes();
    }

    public List<Rule> getRules() {
        return ruleService.getRules();
    }

    public void create(Accident accident) {
        accidentTypeService.findById(accident.getType().getId()).ifPresent(accident::setType);
        accidentRepository.save(accident);
    }

    public void update(Accident accident) {
        accidentTypeService.findById(accident.getType().getId()).ifPresent(accident::setType);
        accidentRepository.update(accident);
    }

    public Optional<Accident> getById(Integer id) {
        return accidentRepository.findById(id);
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
