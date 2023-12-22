package ru.job4j.todo.service;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.repository.CrudUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class SimpleUserService implements UserService {
    private final CrudUserRepository crudUserRepository;

    public SimpleUserService(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Optional<User> saveUser(User user) {
        if (user.getTimezone() == null) {
            user.setTimezone(TimeZone.getDefault().getID());
        }
        return crudUserRepository.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String login, String pass) {
        return crudUserRepository.findByLoginAndPassword(login, pass);
    }

    @Override
    public List<String> getAllTimeZone() {
        var zones = new ArrayList<TimeZone>();
        for (String timeId : TimeZone.getAvailableIDs()) {
            zones.add(TimeZone.getTimeZone(timeId));
        }
        return zones.stream()
                .map(TimeZone::getID)
                .toList();
    }
}
