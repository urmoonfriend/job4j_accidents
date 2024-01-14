package kz.job4j.accidents.repository.impl.jdbc;

import kz.job4j.accidents.repository.AccidentRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import kz.job4j.accidents.model.Accident;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentRepository {
    private final JdbcTemplate jdbc;

    public Accident save(Accident accident) {
        jdbc.update("insert into accidents (name) values (?)",
                accident.getName());
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        return false;
    }

    @Override
    public Optional<Accident> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Accident> getAllAccidents() {
        return null;
    }

}

