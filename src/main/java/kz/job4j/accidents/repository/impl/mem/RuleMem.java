package kz.job4j.accidents.repository.impl.mem;

import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.RuleRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMem implements RuleRepository {
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

    @Override
    public List<Rule> getRules() {
        return new ArrayList<>(rules.values());
    }

    @Override
    public Rule save(Rule rule) {
        rule.setId(nextId.incrementAndGet());
        rules.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public boolean update(Rule rule) {
        return rules.computeIfPresent(
                rule.getId(), (id, ruleToUpdate) ->
                        new Rule()
                                .setId(ruleToUpdate.getId())
                                .setName(rule.getName())) != null;
    }

    @Override
    public Optional<Rule> findById(Integer id) {
        return Optional.ofNullable(rules.get(id));
    }

    @Override
    public Set<Rule> getRulesByIds(String[] ids) {
        Set<Rule> rules = new HashSet<>();
        Arrays.stream(ids).forEach(
                id -> findById(Integer.valueOf(id)).ifPresent(rules::add)
        );
        return rules;
    }
}
