package kz.job4j.accidents.repository;

import kz.job4j.accidents.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {
    private static final Map<Integer, Accident> ACCIDENTS = new ConcurrentHashMap<>();

    static {
        ACCIDENTS.put(0, new Accident()
                .setId(0)
                .setName("Авария 0")
                .setAddress("Абая / Момышулы")
                .setText("Авария на улице Абая / Момышулы в 19:05"));
        ACCIDENTS.put(1, new Accident()
                .setId(1)
                .setName("Авария 1")
                .setAddress("Толе би / Саина")
                .setText("Авария на улице Толе би / Саина в 18:36"));
        ACCIDENTS.put(2, new Accident()
                .setId(2)
                .setName("Авария 2")
                .setAddress("Сатпаева / Достык")
                .setText("Авария на улице Сатпаева / Достык в 09:23"));
    }

    public List<Accident> getAll() {
        return new ArrayList<>(ACCIDENTS.values());
    }
}
