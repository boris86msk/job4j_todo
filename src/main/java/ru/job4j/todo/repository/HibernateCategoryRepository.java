package ru.job4j.todo.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class HibernateCategoryRepository implements CategoryRepository {
    private final SessionFactory sessionFactory;

    public HibernateCategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Set<Category> findAllCategory() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Set<Category> fromUser = session.createQuery("from Category ORDER BY id", Category.class)
                    .stream()
                    .collect(Collectors.toSet());
            session.getTransaction().commit();
            return fromUser;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Collections.emptySet();
    }

    @Override
    public Set<Category> findCategoryById(List<Integer> listInt) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Set<Category> fromUser = session.createQuery("from Category WHERE id IN :listInt", Category.class)
                    .setParameter("listInt", listInt)
                    .stream()
                    .collect(Collectors.toSet());
            session.getTransaction().commit();
            return fromUser;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Collections.emptySet();
    }
}
