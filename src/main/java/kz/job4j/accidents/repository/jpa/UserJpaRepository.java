package kz.job4j.accidents.repository.jpa;

import kz.job4j.accidents.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    
    Optional<User> findByUsernameAndPassword(String username, String password);
}
