package kz.job4j.accidents.service;

import kz.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RuleService {
    List<Rule> getRules();

    Optional<Rule> findById(Integer id);

    Set<Rule> getRulesByIds(String[] ids);

    Set<Rule> getRulesByIds(Set<Rule> rules);
}
