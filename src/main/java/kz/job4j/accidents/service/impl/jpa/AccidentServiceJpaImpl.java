package kz.job4j.accidents.service.impl.jpa;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.jpa.AccidentJpaRepository;
import kz.job4j.accidents.repository.simple.AccidentRepository;
import kz.job4j.accidents.service.AccidentService;
import liquibase.repackaged.org.apache.commons.collections4.IterableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class AccidentServiceJpaImpl implements AccidentService {

    private final AccidentJpaRepository accidentRepository;
    private final AccidentTypeServiceJpaImpl accidentTypeService;
    private final RuleServiceJpaImpl ruleService;

    @Override
    public List<Accident> getAll() {
        log.info("jpa getAll");
        return IterableUtils.toList(accidentRepository.findAll());
    }

    @Override
    public List<AccidentType> getTypes() {
        log.info("jpa getTypes");
        return accidentTypeService.getTypes();
    }

    @Override
    public List<Rule> getRules() {
        log.info("jpa getRules");
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
        accidentRepository.save(accident);
    }

    @Override
    public Optional<Accident> getById(Integer id) {
        log.info("jpa getById");
        return accidentRepository.findById(id);
    }

    @Override
    public void create(Accident accident, String[] ids) {
        log.info("jpa create method request: accident = [{}], ids = [{}]", accident, ids);
        accident.setRules(ruleService.getRulesByIds(ids));
        create(accident);
    }

    @Override
    public void update(Accident accident, String[] ids) {
        log.info("jpa create method request: accident = [{}], ids = [{}]", accident, ids);
        accident.setRules(ruleService.getRulesByIds(ids));
        update(accident);
    }
}
