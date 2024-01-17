package kz.job4j.accidents.service;

import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RuleService {
    @Qualifier("ruleJdbc")
    private final RuleRepository ruleRepository;

    public List<Rule> getRules() {
        return ruleRepository.getRules();
    }

    public Optional<Rule> findById(Integer id) {
        return ruleRepository.findById(id);
    }

    public Set<Rule> getRulesByIds(String[] ids) {
        return ruleRepository.getRulesByIds(ids);
    }

    public Set<Rule> getRulesByIds(Set<Rule> rules) {
        return ruleRepository.getRulesByIds(rules);
    }
}
