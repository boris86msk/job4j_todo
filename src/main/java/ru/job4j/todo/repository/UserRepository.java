package ru.job4j.todo.repository;

import ru.job4j.todo.model.UserStore;

import java.util.Optional;

public interface UserRepository {
    Optional<UserStore> save(UserStore user);
    Optional<UserStore> findByEmailAndPassword(String login, String pass);
}
