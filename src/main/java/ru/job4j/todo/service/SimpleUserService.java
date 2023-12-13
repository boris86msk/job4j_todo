package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.CrudUserRepository;

import java.util.Optional;

@Service
public class SimpleUserService implements UserService {
    private final CrudUserRepository crudUserRepository;

    public SimpleUserService(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Optional<User> saveUser(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String login, String pass) {
        return crudUserRepository.findByLoginAndPassword(login, pass);
    }
}
