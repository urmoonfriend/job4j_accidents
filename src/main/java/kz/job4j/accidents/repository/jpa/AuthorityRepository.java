package kz.job4j.accidents.repository.jpa;

import org.springframework.data.repository.CrudRepository;
import kz.job4j.accidents.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    Authority findByAuthority(String authority);
}