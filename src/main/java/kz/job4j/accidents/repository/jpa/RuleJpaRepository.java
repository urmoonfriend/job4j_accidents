package kz.job4j.accidents.repository.jpa;

import kz.job4j.accidents.model.Rule;
import org.springframework.data.repository.CrudRepository;

@org.springframework.stereotype.Repository("ruleJpa")
public interface RuleJpaRepository extends CrudRepository<Rule, Integer> {
}
