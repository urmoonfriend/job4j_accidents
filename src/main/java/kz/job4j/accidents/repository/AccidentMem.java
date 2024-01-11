package kz.job4j.accidents.repository;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.model.AccidentType;
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
    private static final Map<Integer, AccidentType> ACCIDENT_TYPES = new ConcurrentHashMap<>();
    private static final AtomicInteger NEXT_ACCIDENT_ID = new AtomicInteger(0);

    private static final AtomicInteger NEXT_TYPE_ID = new AtomicInteger(0);

    static {
        AccidentType type1 = new AccidentType()
                .setId(NEXT_TYPE_ID.incrementAndGet())
                .setName("Две машины");
        AccidentType type2 = new AccidentType()
                .setId(NEXT_TYPE_ID.incrementAndGet())
                .setName("Машина и человек");
        AccidentType type3 = new AccidentType()
                .setId(NEXT_TYPE_ID.incrementAndGet())
                .setName("Машина и велосипед");
        ACCIDENT_TYPES.put(type1.getId(), type1);
        ACCIDENT_TYPES.put(type2.getId(), type2);
        ACCIDENT_TYPES.put(type3.getId(), type3);

        Accident accident1 = new Accident()
                .setId(NEXT_ACCIDENT_ID.incrementAndGet())
                .setName("Авария 1")
                .setAddress("Абая / Момышулы")
                .setText("Авария на улице Абая / Момышулы в 19:05")
                .setType(type1);
        Accident accident2 = new Accident()
                .setId(NEXT_ACCIDENT_ID.incrementAndGet())
                .setName("Авария 2")
                .setAddress("Толе би / Саина")
                .setText("Авария на улице Толе би / Саина в 18:36")
                .setType(type2);
        Accident accident3 =  new Accident()
                .setId(NEXT_ACCIDENT_ID.incrementAndGet())
                .setName("Авария 3")
                .setAddress("Сатпаева / Достык")
                .setText("Авария на улице Сатпаева / Достык в 09:23")
                .setType(type3);
        ACCIDENTS.put(accident1.getId(), accident1);
        ACCIDENTS.put(accident2.getId(), accident2);
        ACCIDENTS.put(accident3.getId(), accident3);
    }

    public List<Accident> getAllAccidents() {
        return new ArrayList<>(ACCIDENTS.values());
    }

    public List<AccidentType> getAccidentTypes() {
        return new ArrayList<>(ACCIDENT_TYPES.values());
    }

    public Accident save(Accident accident) {
        accident.setId(NEXT_ACCIDENT_ID.incrementAndGet());
        accident.setType(ACCIDENT_TYPES.get(accident.getType().getId()));
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
                                .setAddress(accident.getAddress())
                                .setType(ACCIDENT_TYPES.get(accident.getType().getId()))) != null;
    }

    public Optional<Accident> findById(Integer id) {
        return Optional.ofNullable(ACCIDENTS.get(id));
    }
}
