package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.UserStore;

import java.util.Optional;

@Service
public class SimpleUserService implements UserService {
    @Override
    public Optional<UserStore> saveUser(UserStore user) {

        return false;
    }
}
