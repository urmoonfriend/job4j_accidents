package kz.job4j.accidents.service.impl.jpa;

import kz.job4j.accidents.model.User;
import kz.job4j.accidents.repository.jpa.AuthorityRepository;
import kz.job4j.accidents.repository.jpa.UserJpaRepository;
import kz.job4j.accidents.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceJpaImpl implements UserService {
    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Optional<User> create(User user) {
        Optional<User> result = Optional.empty();
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
        try {
            result = Optional.of(userRepository.save(user));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Optional<User> update(User user) {
        Optional<User> result = Optional.empty();
        try {
            var userOpt = userRepository.findById(user.getId());
            if (userOpt.isPresent()) {
                User userToUpdate = userOpt.get();
                userToUpdate.setUsername(user.getUsername());
                userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
                userToUpdate.setEnabled(true);
                userToUpdate.setAuthority(authorityRepository.findByAuthority(user.getAuthority().getAuthority()));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
