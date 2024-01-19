package kz.job4j.accidents.service.impl.jpa;

import kz.job4j.accidents.model.Rule;
import kz.job4j.accidents.repository.jpa.RuleJpaRepository;
import kz.job4j.accidents.service.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class RuleServiceJpaImpl implements RuleService {
    private final RuleJpaRepository ruleRepository;

    @Override
    public List<Rule> getRules() {
        return ruleRepository.findAll();
    }

    @Override
    public Optional<Rule> findById(Integer id) {
        return ruleRepository.findById(id);
    }

    @Override
    public Set<Rule> getRulesByIds(String[] ids) {
        List<Integer> integerList = Arrays.stream(ids)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new HashSet<>(ruleRepository.findAllByIdIn(integerList));
    }

    @Override
    public Set<Rule> getRulesByIds(Set<Rule> rules) {
        List<Integer> integerList = rules.stream()
                .map(Rule::getId)
                .collect(Collectors.toList());
        return new HashSet<>(ruleRepository.findAllByIdIn(integerList));
    }
}
