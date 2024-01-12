package kz.job4j.accidents.repository;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.AccidentType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(1);

    private AccidentMem() {
        Accident accident1 = new Accident()
                .setId(nextId.incrementAndGet())
                .setName("Авария 1")
                .setAddress("Абая / Момышулы")
                .setText("Авария на улице Абая / Момышулы в 19:05")
                .setType(new AccidentType().setId(1).setName("Две машины"));
        Accident accident2 = new Accident()
                .setId(nextId.incrementAndGet())
                .setName("Авария 2")
                .setAddress("Толе би / Саина")
                .setText("Авария на улице Толе би / Саина в 18:36")
                .setType(new AccidentType().setId(2).setName("Машина и человек"));
        Accident accident3 = new Accident()
                .setId(nextId.incrementAndGet())
                .setName("Авария 3")
                .setAddress("Сатпаева / Достык")
                .setText("Авария на улице Сатпаева / Достык в 09:23")
                .setType(new AccidentType().setId(3).setName("Машина и велосипед"));

        accidents.put(accident1.getId(), accident1);
        accidents.put(accident2.getId(), accident2);
        accidents.put(accident3.getId(), accident3);
    }

    public List<Accident> getAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public Accident save(Accident accident) {
        accident.setId(nextId.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    public boolean update(Accident accident) {
        return accidents.computeIfPresent(
                accident.getId(), (id, accidentToUpdate) ->
                        new Accident()
                                .setId(accidentToUpdate.getId())
                                .setName(accident.getName())
                                .setText(accident.getText())
                                .setAddress(accident.getAddress())
                                .setType(accident.getType())) != null;
    }

    public Optional<Accident> findById(Integer id) {
        return Optional.ofNullable(accidents.get(id));
    }
}
