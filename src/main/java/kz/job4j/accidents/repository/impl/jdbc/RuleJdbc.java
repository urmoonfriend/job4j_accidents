package kz.job4j.accidents.repository.impl.jdbc;

import kz.job4j.accidents.mapper.RuleMapper;
import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.RuleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("ruleJdbc")
@Primary
@RequiredArgsConstructor
public class RuleJdbc implements RuleRepository {
    private final JdbcTemplate jdbc;

    public Rule save(Rule rule) {
        jdbc.update("insert into rules (name) values (?)",
                rule.getName());
        return rule;
    }

    @Override
    public boolean update(Rule rule) {
        return jdbc.update("update rules set name = ? where id = ?",
                rule.getName(), rule.getId()) == 1;
    }

    @Override
    public Optional<Rule> findById(Integer id) {
        return jdbc.query("select * from rules where id = ?", new RuleMapper(), id)
                .stream().findFirst();
    }

    @Override
    public List<Rule> getRules() {
        return jdbc.query("select * from rules", new RuleMapper());
    }

    @Override
    public Set<Rule> getRulesByIds(String[] ids) {
        List<Integer> intIds = new ArrayList<>();
        Arrays.stream(ids).forEach(id -> intIds.add(Integer.valueOf(id)));
        String inSql = String.join(",", Collections.nCopies(intIds.size(), "?"));
        return new HashSet<>(
                jdbc.query(String.format("select * from rules where id in (%s)", inSql),
                        new RuleMapper(),
                        intIds.toArray()));
    }

    @Override
    public Set<Rule> getRulesByIds(Set<Rule> rules) {
        List<Integer> intIds = new ArrayList<>();
        rules.forEach(rule -> intIds.add(rule.getId()));
        String inSql = String.join(",", Collections.nCopies(intIds.size(), "?"));
        return new HashSet<>(
                jdbc.query(String.format("select * from rules where id in (%s)", inSql),
                        new RuleMapper(),
                        intIds.toArray()));
    }

}

