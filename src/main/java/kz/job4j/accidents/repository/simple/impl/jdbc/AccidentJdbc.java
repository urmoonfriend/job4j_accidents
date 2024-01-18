package kz.job4j.accidents.repository.simple.impl.jdbc;

import kz.job4j.accidents.mapper.AccidentMapper;
import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.simple.AccidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository("accidentJdbc")
@RequiredArgsConstructor
public class AccidentJdbc implements AccidentRepository {
    private final JdbcTemplate jdbc;

    @Override
    public Accident save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)",
                    new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            accident.setId(keyHolder.getKey().intValue());
        }
        accident.getRules().forEach(
                rule -> jdbc.update("insert into accidents_rules(accident_id, rule_id) values (?, ?)",
                        accident.getId(), rule.getId())
        );
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        jdbc.update("delete from accidents_rules where accident_id = ?",
                accident.getId());
        accident.getRules().forEach(
                rule -> jdbc.update("insert into accidents_rules(accident_id, rule_id) values (?, ?)",
                        accident.getId(), rule.getId())
        );
        return jdbc.update("update accidents set name = ? ,"
                        + "text = ? ,"
                        + "address = ? ,"
                        + "type_id = ? "
                        + "where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId()) == 1;
    }

    @Override
    public Optional<Accident> findById(Integer id) {
        return jdbc.query("select * from accidents where id = ?", new AccidentMapper(), id)
                .stream().findFirst()
                .map(accident -> {
                    var rules = jdbc.query("select rule_id from accidents_rules where accident_id = ?",
                            (rs, row) -> new Rule().setId(rs.getInt("rule_id")),
                            accident.getId());
                    accident.setRules(new HashSet<>(rules));
                    return accident;
                });
    }

    @Override
    public List<Accident> getAllAccidents() {
        List<Accident> accidents = jdbc.query("select * from accidents", new AccidentMapper());
        accidents.forEach(
                accident -> accident.setRules(new HashSet<>(
                        jdbc.query("select rule_id from accidents_rules where accident_id = ?",
                                (rs, row) -> new Rule().setId(rs.getInt("rule_id")),
                                accident.getId())
                ))
        );
        return accidents;
    }

}

