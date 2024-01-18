package kz.job4j.accidents.service.impl.simple;

import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.repository.simple.AccidentTypeRepository;
import kz.job4j.accidents.service.AccidentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccidentTypeServiceImpl implements AccidentTypeService {
    @Qualifier("typeHibernate")
    private final AccidentTypeRepository accidentTypeMem;

    @Override
    public List<AccidentType> getTypes() {
        return accidentTypeMem.getAccidentTypes();
    }

    @Override
    public Optional<AccidentType> findById(Integer id) {
        return accidentTypeMem.findById(id);
    }
}
