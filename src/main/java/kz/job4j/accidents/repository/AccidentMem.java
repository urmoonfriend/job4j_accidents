package kz.job4j.accidents.repository;

import kz.job4j.accidents.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private static final Map<Integer, Accident> ACCIDENTS = new ConcurrentHashMap<>();
    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);

    static {
        Accident accident1 = new Accident()
                .setId(NEXT_ID.incrementAndGet())
                .setName("Авария 1")
                .setAddress("Абая / Момышулы")
                .setText("Авария на улице Абая / Момышулы в 19:05");
        Accident accident2 = new Accident()
                .setId(NEXT_ID.incrementAndGet())
                .setName("Авария 2")
                .setAddress("Толе би / Саина")
                .setText("Авария на улице Толе би / Саина в 18:36");
        Accident accident3 =  new Accident()
                .setId(NEXT_ID.incrementAndGet())
                .setName("Авария 3")
                .setAddress("Сатпаева / Достык")
                .setText("Авария на улице Сатпаева / Достык в 09:23");
        ACCIDENTS.put(accident1.getId(), accident1);
        ACCIDENTS.put(accident2.getId(), accident2);
        ACCIDENTS.put(accident3.getId(), accident3);
    }

    public List<Accident> getAll() {
        return new ArrayList<>(ACCIDENTS.values());
    }

    public Accident save(Accident accident) {
        accident.setId(NEXT_ID.incrementAndGet());
        ACCIDENTS.put(accident.getId(), accident);
        return accident;
    }

    public boolean update(Accident accident) {
        return ACCIDENTS.computeIfPresent(
                accident.getId(), (id, accidentToUpdate) ->
                        new Accident()
                                .setId(accidentToUpdate.getId())
                                .setName(accident.getName())
                                .setText(accident.getText())
                                .setAddress(accident.getAddress())) != null;
    }

    public Optional<Accident> findById(Integer id) {
        return Optional.ofNullable(ACCIDENTS.get(id));
    }
}
