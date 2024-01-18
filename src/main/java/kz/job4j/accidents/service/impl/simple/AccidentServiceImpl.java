package kz.job4j.accidents.service.impl.simple;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.simple.AccidentRepository;
import kz.job4j.accidents.service.AccidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccidentServiceImpl implements AccidentService {

    @Qualifier("accidentHibernate")
    private final AccidentRepository accidentRepository;
    private final AccidentTypeServiceImpl accidentTypeService;
    private final RuleServiceImpl ruleService;

    @Override
    public List<Accident> getAll() {
        List<Accident> accidents = accidentRepository.getAllAccidents();
        accidents.forEach(
                accident -> {
                    accidentTypeService.findById(accident.getType().getId()).ifPresent(accident::setType);
                    accident.setRules(new HashSet<>(ruleService.getRulesByIds(accident.getRules())));
                }
        );
        return accidents;
    }

    @Override
    public List<AccidentType> getTypes() {
        return accidentTypeService.getTypes();
    }

    @Override
    public List<Rule> getRules() {
        return ruleService.getRules();
    }

    @Override
    public void create(Accident accident) {
        accidentTypeService.findById(accident.getType().getId()).ifPresent(accident::setType);
        accidentRepository.save(accident);
    }

    @Override
    public void update(Accident accident) {
        accidentTypeService.findById(accident.getType().getId()).ifPresent(accident::setType);
        accidentRepository.update(accident);
    }

    @Override
    public Optional<Accident> getById(Integer id) {
        var accidentOpt = accidentRepository.findById(id);
        if (accidentOpt.isPresent()) {
            var accident = accidentOpt.get();
            accidentTypeService.findById(accident.getType().getId()).ifPresent(accident::setType);
            accident.setRules(new HashSet<>(ruleService.getRulesByIds(accident.getRules())));
            return Optional.of(accident);
        }
        return Optional.empty();
    }

    @Override
    public void create(Accident accident, String[] ids) {
        log.info("create method request: accident = [{}], ids = [{}]", accident, ids);
        accident.setRules(ruleService.getRulesByIds(ids));
        create(accident);
    }

    @Override
    public void update(Accident accident, String[] ids) {
        accident.setRules(ruleService.getRulesByIds(ids));
        update(accident);
    }
}
