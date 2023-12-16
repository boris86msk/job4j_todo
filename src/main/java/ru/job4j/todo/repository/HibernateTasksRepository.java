package ru.job4j.todo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class HibernateTasksRepository implements TasksRepository {
    private final SessionFactory sessionFactory;

    public HibernateTasksRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Task> save(Task task) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            return Optional.of(task);
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public List<Task> findAll() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<Task> fromUser = session.createQuery("from Task f JOIN FETCH f.priority ORDER BY f.id", Task.class).list();
            session.getTransaction().commit();
            return fromUser;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Optional<Task> task = session.createQuery("from Task where id = :fId", Task.class)
                    .setParameter("fId", id)
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

    @Override
    public List<Task> findByDone(boolean done) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<Task> tasks = session.createQuery("from Task where done = :done", Task.class)
                    .setParameter("done", done)
                    .list();
            session.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean deleteById(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE Task WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Task task) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("UPDATE Task SET title = :title, description = :des WHERE id = :fId")
                    .setParameter("fId", task.getId())
                    .setParameter("title", task.getTitle())
                    .setParameter("des", task.getDescription())
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean taskDone(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("UPDATE Task SET done = true WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
