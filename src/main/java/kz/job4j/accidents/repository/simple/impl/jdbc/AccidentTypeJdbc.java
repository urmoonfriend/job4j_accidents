package kz.job4j.accidents.repository.simple.impl.jdbc;

import kz.job4j.accidents.mapper.AccidentTypeMapper;
import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.repository.simple.AccidentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("typeJdbc")
@RequiredArgsConstructor
public class AccidentTypeJdbc implements AccidentTypeRepository {
    private final JdbcTemplate jdbc;

    public AccidentType save(AccidentType type) {
        jdbc.update("insert into accidents (name) values (?)",
                type.getName());
        return type;
    }

    @Override
    public boolean update(AccidentType type) {
        return jdbc.update("update types set name = ? where id = ?",
                type.getName(), type.getId()) == 1;
    }

    @Override
    public Optional<AccidentType> findById(Integer id) {
        return jdbc.query("select * from types where id = ?", new AccidentTypeMapper(), id)
                .stream().findFirst();
    }

    @Override
    public List<AccidentType> getAccidentTypes() {
        return jdbc.query("select * from types", new AccidentTypeMapper());
    }

}

