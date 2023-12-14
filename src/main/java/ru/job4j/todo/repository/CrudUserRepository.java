package ru.job4j.todo.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Map;
import java.util.Optional;

@Repository
public class CrudUserRepository {
    private final CrudRepository crudRepository;

    public CrudUserRepository(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public Optional<User> save(User user) {
        try {
            crudRepository.run(session -> session.persist(user));
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @param pass  password.
     * @return Optional or user.
     */
    public Optional<User> findByLoginAndPassword(String login, String pass) {
        return crudRepository.optional(
                "from User where login = :fLogin and password = :fPass", User.class,
                Map.of("fLogin", login, "fPass", pass)
        );
    }
}
