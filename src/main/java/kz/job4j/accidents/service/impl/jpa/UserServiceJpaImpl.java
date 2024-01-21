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
    public void create(User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.findById(user.getId()).ifPresent(
                userToUpdate -> {
                    user.setUsername(user.getUsername());
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    user.setEnabled(true);
                    user.setAuthority(authorityRepository.findByAuthority(user.getAuthority().getAuthority()));
                    userRepository.save(userToUpdate);
                }
        );
    }
}
