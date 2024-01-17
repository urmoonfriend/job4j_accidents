package kz.job4j.accidents.repository;

import kz.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RuleRepository {
    List<Rule> getRules();

    Rule save(Rule rule);

    boolean update(Rule rule);

    Optional<Rule> findById(Integer id);

    Set<Rule> getRulesByIds(String[] ids);

    Set<Rule> getRulesByIds(Set<Rule> rules);
}
