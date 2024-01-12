package kz.job4j.accidents.service;

import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.RuleMem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RuleService {
    private final RuleMem ruleMem;

    public List<Rule> getRules() {
        return ruleMem.getRules();
    }

    public Optional<Rule> findById(Integer id) {
        return ruleMem.findById(id);
    }
}
