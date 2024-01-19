package kz.job4j.accidents.repository.jpa;

import kz.job4j.accidents.model.Rule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@org.springframework.stereotype.Repository("ruleJpa")
public interface RuleJpaRepository extends CrudRepository<Rule, Integer> {
    List<Rule> findAll();

    List<Rule> findAllByIdIn(List<Integer> ids);
}
