package kz.job4j.accidents.repository.impl.hibernate;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.repository.AccidentRepository;
import kz.job4j.accidents.repository.CrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository("accidentHibernate")
@RequiredArgsConstructor
public class AccidentHibernate implements AccidentRepository {

    private final CrudRepository crudRepository;

    @Override
    public List<Accident> getAllAccidents() {
        return crudRepository.query("select distinct a from kz.job4j.accidents.model.Accident a "
                + " JOIN FETCH a.rules "
                + " JOIN FETCH a.type "
                + " order by a.id asc", Accident.class);
    }

    @Override
    public Accident save(Accident accident) {
        crudRepository.run(session -> session.persist(accident));
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        AtomicBoolean result = new AtomicBoolean(false);
        crudRepository.run(session -> {
            result.set(session.merge(accident) != null);
        });
        return result.get();
    }

    @Override
    public Optional<Accident> findById(Integer id) {
        return crudRepository.optional("from Accident a "
                        + " JOIN FETCH a.rules "
                        + " JOIN FETCH a.type "
                        + " where a.id = : aId", Accident.class,
                Map.of("aId", id));
    }
}
