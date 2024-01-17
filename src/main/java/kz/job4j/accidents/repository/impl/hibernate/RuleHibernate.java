package kz.job4j.accidents.repository.impl.hibernate;

import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.CrudRepository;
import kz.job4j.accidents.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository("ruleHibernate")
@RequiredArgsConstructor
public class RuleHibernate implements RuleRepository {

    private final CrudRepository crudRepository;

    @Override
    public List<Rule> getRules() {
        return crudRepository.query("from Rule order by id asc", Rule.class);
    }

    @Override
    public Rule save(Rule rule) {
        crudRepository.run(session -> session.persist(rule));
        return rule;
    }

    @Override
    public boolean update(Rule rule) {
        AtomicBoolean result = new AtomicBoolean(false);
        crudRepository.run(session -> {
            result.set(session.merge(rule) != null);
        });
        return result.get();
    }

    @Override
    public Optional<Rule> findById(Integer id) {
        return crudRepository.optional("from Rule where id = : rId",
                Rule.class,
                Map.of("rId", id));
    }

    @Override
    public Set<Rule> getRulesByIds(String[] ids) {
        List<Integer> intIds = new ArrayList<>();
        Arrays.stream(ids).forEach(id -> intIds.add(Integer.valueOf(id)));
        return new HashSet<>(crudRepository.query("select distinct r from Rule r where r.id in (:rIds)",
                Rule.class,
                Map.of("rIds", intIds)));
    }

    @Override
    public Set<Rule> getRulesByIds(Set<Rule> rules) {
        List<Integer> intIds = new ArrayList<>();
        rules.forEach(rule -> intIds.add(rule.getId()));
        return new HashSet<>(crudRepository.query("select distinct r from Rule r where r.id in (:rIds)",
                Rule.class,
                Map.of("rIds", intIds)));
    }
}
