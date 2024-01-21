package kz.job4j.accidents.service.impl.jpa;

import kz.job4j.accidents.model.User;
import kz.job4j.accidents.repository.jpa.UserJpaRepository;
import kz.job4j.accidents.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceJpaImpl implements UserService {
    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @PostConstruct
    public void init() {
        userRepository.findByUsername("user").ifPresent(
                user -> {
                    user.setPassword(passwordEncoder.encode("123456"));
                    userRepository.save(user);
                }
        );
    }
}
