package kz.job4j.accidents.service;

import kz.job4j.accidents.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> create(User user);

    Optional<User> update(User user);
}
