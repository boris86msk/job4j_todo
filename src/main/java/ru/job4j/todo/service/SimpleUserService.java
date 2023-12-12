package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.HibernateUserRepository;

import java.util.Optional;

@Service
public class SimpleUserService implements UserService {
    private final HibernateUserRepository hibernateUserRepository;

    public SimpleUserService(HibernateUserRepository hibernateUserRepository) {
        this.hibernateUserRepository = hibernateUserRepository;
    }

    @Override
    public Optional<User> saveUser(User user) {
        return hibernateUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String login, String pass) {
        return hibernateUserRepository.findByEmailAndPassword(login, pass);
    }
}
