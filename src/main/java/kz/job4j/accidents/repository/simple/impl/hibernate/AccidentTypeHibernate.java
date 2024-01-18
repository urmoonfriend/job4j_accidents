package kz.job4j.accidents.repository.simple.impl.hibernate;

import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.repository.simple.AccidentTypeRepository;
import kz.job4j.accidents.repository.simple.CrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository("typeHibernate")
@RequiredArgsConstructor
@Primary
public class AccidentTypeHibernate implements AccidentTypeRepository {

    private final CrudRepository crudRepository;

    @Override
    public List<AccidentType> getAccidentTypes() {
        return crudRepository.query("from AccidentType order by id asc", AccidentType.class);
    }

    @Override
    public AccidentType save(AccidentType accidentType) {
        crudRepository.run(session -> session.persist(accidentType));
        return accidentType;
    }

    @Override
    public boolean update(AccidentType accidentType) {
        AtomicBoolean result = new AtomicBoolean(false);
        crudRepository.run(session -> {
            result.set(session.merge(accidentType) != null);
        });
        return result.get();
    }

    @Override
    public Optional<AccidentType> findById(Integer id) {
        return crudRepository.optional("from AccidentType where id = : aId", AccidentType.class,
                Map.of("aId", id));
    }
}
