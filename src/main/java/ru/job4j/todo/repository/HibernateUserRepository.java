package ru.job4j.todo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.UserStore;

import java.util.Optional;

@Repository
public class HibernateUserRepository implements UserRepository {
    private final SessionFactory sessionFactory;

    public HibernateUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<UserStore> save(UserStore user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserStore> findByEmailAndPassword(String login, String pass) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Optional<UserStore> task = session.createQuery("from UserStore where login = :fLogin"
                            + " and password = :fPass", UserStore.class)
                    .setParameter("fLogin", login)
                    .setParameter("fPass", pass)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            return task;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }


}
