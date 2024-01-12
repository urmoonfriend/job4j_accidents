package kz.job4j.accidents.repository;

import kz.job4j.accidents.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem {
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);

    private RuleMem() {
        Rule rule1 = new Rule()
                .setId(nextId.incrementAndGet())
                .setName("Статья. 1");
        Rule rule2 = new Rule()
                .setId(nextId.incrementAndGet())
                .setName("Статья. 2");
        Rule rule3 = new Rule()
                .setId(nextId.incrementAndGet())
                .setName("Статья. 3");
        rules.put(rule1.getId(), rule1);
        rules.put(rule2.getId(), rule2);
        rules.put(rule3.getId(), rule3);
    }

    public List<Rule> getRules() {
        return new ArrayList<>(rules.values());
    }

    public Rule save(Rule rule) {
        rule.setId(nextId.incrementAndGet());
        rules.put(rule.getId(), rule);
        return rule;
    }

    public boolean update(Rule rule) {
        return rules.computeIfPresent(
                rule.getId(), (id, ruleToUpdate) ->
                        new Rule()
                                .setId(ruleToUpdate.getId())
                                .setName(rule.getName())) != null;
    }

    public Optional<Rule> findById(Integer id) {
        return Optional.ofNullable(rules.get(id));
    }
}
