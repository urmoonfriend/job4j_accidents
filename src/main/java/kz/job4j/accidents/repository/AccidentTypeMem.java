package kz.job4j.accidents.repository;

import kz.job4j.accidents.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final AtomicInteger nextId = new AtomicInteger(0);

    private AccidentTypeMem() {
        AccidentType type1 = new AccidentType()
                .setId(nextId.incrementAndGet())
                .setName("Две машины");
        AccidentType type2 = new AccidentType()
                .setId(nextId.incrementAndGet())
                .setName("Машина и человек");
        AccidentType type3 = new AccidentType()
                .setId(nextId.incrementAndGet())
                .setName("Машина и велосипед");
        types.put(type1.getId(), type1);
        types.put(type2.getId(), type2);
        types.put(type3.getId(), type3);
    }

    public List<AccidentType> getAccidentTypes() {
        return new ArrayList<>(types.values());
    }

    public AccidentType save(AccidentType type) {
        type.setId(nextId.incrementAndGet());
        types.put(type.getId(), type);
        return type;
    }

    public boolean update(AccidentType type) {
        return types.computeIfPresent(
                type.getId(), (id, typeToUpdate) ->
                        new AccidentType()
                                .setId(typeToUpdate.getId())
                                .setName(type.getName())) != null;
    }

    public Optional<AccidentType> findById(Integer id) {
        return Optional.ofNullable(types.get(id));
    }
}
