package ru.job4j.todo.service;

import ru.job4j.todo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> saveUser(User user);
    Optional<User> findByEmailAndPassword(String login, String pass);
    List<String> getAllTimeZone();
}
