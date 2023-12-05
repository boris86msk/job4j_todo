package ru.job4j.todo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Tasks;

import java.util.List;

@Repository
public class HibernateTasksRepository implements TasksRepository {
    private final SessionFactory sessionFactory;

    public HibernateTasksRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Tasks tasks) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(tasks);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Tasks> findAll() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<Tasks> fromUser = session.createQuery("from Tasks ORDER BY id", Tasks.class).list();
            session.getTransaction().commit();
            return fromUser;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Tasks findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Tasks task = session.createQuery("from Tasks where id = :fId", Tasks.class)
                    .setParameter("fId", id)
                    .uniqueResult();
            session.getTransaction().commit();
            return task;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Tasks> findNew() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<Tasks> tasks = session.createQuery("from Tasks where done = false", Tasks.class)
                    .list();
            session.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Tasks> findFinished() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            List<Tasks> tasks = session.createQuery("from Tasks where done = true", Tasks.class)
                    .list();
            session.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE Tasks WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Tasks tasks) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createQuery("UPDATE Tasks SET title = :title, description = :des, done = :done WHERE id = :fId")
                    .setParameter("fId", tasks.getId())
                    .setParameter("title", tasks.getTitle())
                    .setParameter("des", tasks.getDescription())
                    .setParameter("done", tasks.getDone())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
