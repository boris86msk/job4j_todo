package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.UserStore;
import ru.job4j.todo.repository.HibernateUserRepository;

import java.util.Optional;

@Service
public class SimpleUserService implements UserService {
    private final HibernateUserRepository hibernateUserRepository;

    public SimpleUserService(HibernateUserRepository hibernateUserRepository) {
        this.hibernateUserRepository = hibernateUserRepository;
    }

    @Override
    public Optional<UserStore> saveUser(UserStore user) {
        return hibernateUserRepository.save(user);
    }

    @Override
    public Optional<UserStore> findByEmailAndPassword(String login, String pass) {
        return hibernateUserRepository.findByEmailAndPassword(login, pass);
    }
}
