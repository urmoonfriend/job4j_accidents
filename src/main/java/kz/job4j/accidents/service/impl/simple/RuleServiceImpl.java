package kz.job4j.accidents.service.impl.simple;

import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.simple.RuleRepository;
import kz.job4j.accidents.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {
    @Qualifier("ruleHibernate")
    private final RuleRepository ruleRepository;

    @Override
    public List<Rule> getRules() {
        return ruleRepository.getRules();
    }

    @Override
    public Optional<Rule> findById(Integer id) {
        return ruleRepository.findById(id);
    }

    @Override
    public Set<Rule> getRulesByIds(String[] ids) {
        return ruleRepository.getRulesByIds(ids);
    }

    @Override
    public Set<Rule> getRulesByIds(Set<Rule> rules) {
        return ruleRepository.getRulesByIds(rules);
    }
}
