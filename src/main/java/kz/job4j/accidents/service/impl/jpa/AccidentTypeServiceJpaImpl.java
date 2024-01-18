package kz.job4j.accidents.service.impl.jpa;

import kz.job4j.accidents.model.AccidentType;
import kz.job4j.accidents.repository.jpa.AccidentTypeJpaRepository;
import kz.job4j.accidents.service.AccidentTypeService;
import liquibase.repackaged.org.apache.commons.collections4.IterableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class AccidentTypeServiceJpaImpl implements AccidentTypeService {
    private final AccidentTypeJpaRepository accidentTypeJpaRepository;

    @Override
    public List<AccidentType> getTypes() {
        return IterableUtils.toList(accidentTypeJpaRepository.findAll());
    }

    @Override
    public Optional<AccidentType> findById(Integer id) {
        return accidentTypeJpaRepository.findById(id);
    }
}
